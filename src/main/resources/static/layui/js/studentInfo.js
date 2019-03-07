$(document).ready(function(){
    //alert('文档加载完毕');
    $.ajax({
        url:"/wx/getUserInfo",
        success:function(result){
            //$("#weui-textarea").val(result)
            $.toast("欢迎 "+result, "text");
            console.log(result)
        },
        error:function(e){
            alert("错误！！");

        }
    });
});

$("#score").picker({
    title: "请选择成绩展示类型:",
    cols: [
        {
            textAlign: 'center',
            values: ['饼图', '柱状图', 'iPhone 5', 'iPhone 5S', 'iPhone 6', 'iPhone 6 Plus', 'iPad 2', 'iPad Retina', 'iPad Air', 'iPad mini', 'iPad mini 2', 'iPad mini 3']
        }
    ],
    onChange: function(p, v, dv) {
        console.log("p=>"+p+" v=>"+v+" dv=>"+dv);
    },
    onClose: function(p) {

        console.log(p["value"][0]);
    }

});

var map = {
    '芳华': '80%',
    '妖猫传': '20%',
    '机器之血': '18%',
    '心理罪': '15%',
    '寻梦环游记': '5%',
    '其他': '2%',
    'h哈':'1%'
};
var data = [{
    name: '芳华',
    percent: 0.4,
    a: '1'
}, {
    name: '妖猫传',
    percent: 0.2,
    a: '1'
}, {
    name: '机器之血',
    percent: 0.18,
    a: '1'
}, {
    name: '心理罪',
    percent: 0.15,
    a: '1'
}, {
    name: '寻梦环游记',
    percent: 0.05,
    a: '1'
}, {
    name: '其他',
    percent: 0.02,
    a: '1'
}, {
    name: 'h哈',
    percent: 0.01,
    a: '1'
}];
var chart = new F2.Chart({
    id: 'mountNode',
    pixelRatio: window.devicePixelRatio
});
chart.source(data, {
    percent: {
        formatter: function formatter(val) {
            return val * 100 + '%';
        }
    }
});
chart.legend({
    position: 'right',
    itemFormatter: function itemFormatter(val) {
        return val + '  ' + map[val];
    }
});
chart.tooltip(false);
chart.coord('polar', {
    transposed: true,
    radius: 0.85
});
chart.axis(false);
chart.interval().position('a*percent').color('name', ['#1890FF', '#13C2C2', '#2FC25B', '#FACC14', '#F04864', '#8543E0', '#13C2C2']).adjust('stack').style({
    lineWidth: 1,
    stroke: '#fff',
    lineJoin: 'round',
    lineCap: 'round'
}).animate({
    appear: {
        duration: 1200,
        easing: 'bounceOut'
    }
});

chart.render();

