var testDate=new Array();
var map={};
var data=[] ;
var data3=[] ;
$(document).ready(function(){
    //alert('文档加载完毕');
    $.ajax({
        url:"/wx/getUserInfo",
        success:function(result){
            //$("#weui-textarea").val(result)
            $.toast("欢迎 "+result, "text");
            console.log("/wx/getUserInfo "+result)
        },
        error:function(e){
            alert("错误！！");

        }
    });
    $.ajax({
        url:"/wx/getScoreByDate",
        dataType:"json",
        success:function(result){
            console.log(result.ranking.total);

            $("#total").html(result.ranking.total);
            $("#ranking").html(result.ranking.ranking);
            map=result.map;
            data=result.data;
            data3=result.data3;
            //console.log("data  "+JSON.stringify(data));
            //scoreX1(map,data);
            console.log(data3)
            scoreX4(data3);
            scoreX2(data);
        }
    });

    $.ajax({
        url:"/wx/getTestDate",
        success:function(result){
            //console.log("/wx/getTestDate "+result)
           for (var i=0;i<result.length;i++){
               testDate.push(result[i])
           }
        }
    });
});

$("#score").picker({
    title: "请选择考试日期:",
    cols: [
        {
            textAlign: 'center',
            values: testDate
        }
    ],
    onChange: function(p, v, dv) {
        //console.log("onChange p=>"+p+" v=>"+v+" dv=>"+dv);
    },
    onClose: function(p) {
        //console.log("onClose"+p["value"][0]);
        $.ajax({
            url:"/wx/getScoreByDate",
            data: {testTime:p["value"][0]},
            success:function(result){
                console.log(result);
                map=result.map;
                data=result.data;
                data3=result.data3;
                //scoreX1(map,data);
                scoreX4(data3);
                scoreX2(data);
                $("#total").html(result.ranking.total);
                $("#ranking").html(result.ranking.ranking);
                $.toptip('成功', 'success');
            }
        });
    }

});

function scoreX2(data){
    var chart = new F2.Chart({
        id: 'mountNode2',
        pixelRatio: window.devicePixelRatio
    });
    chart.clear(); // 清除
    chart.source(data, {
        percent: {
            formatter: function formatter(val) {
                return val * 100;
            }
        }
    });
    chart.legend({
        position: 'right',
        marker: 'square'
    });
    chart.tooltip(false);
    chart.coord('polar', {
        transposed: true,
        radius: 0.85,
        innerRadius: 0.618
    });
    chart.axis(false);
    chart.interval().position('a*percent').color('name', ['#1890FF', '#13C2C2', '#2FC25B', '#FACC14', '#F04864', '#8543E0']).adjust('stack').style({
        lineWidth: 1,
        stroke: '#fff',
        lineJoin: 'round',
        lineCap: 'round'
    });

    chart.guide().html({
        position: ['50%', '50%'],
        html: '<div style="text-align: center;width: 100px;height: 72px;vertical-align: middle;">' + '<p id="number" style="font-size: 28px;margin: 10px 10px 5px;font-weight: bold;"></p>' + '<p id="name" style="font-size: 12px;margin: 0;"></p>' + '</div>'
    });
    chart.render();
    chart.interaction('pie-select', {
        animate: {
            duration: 300,
            easing: 'backOut'
        },
        defaultSelected: {
            name: data[0]["name"],
            percent: data[0]["percent"],
            a: '1'
        },
        onEnd: function onEnd(ev) {
            var shape = ev.shape,
                data = ev.data,
                shapeInfo = ev.shapeInfo,
                selected = ev.selected;

            if (shape) {
                if (selected) {
                    $('#number').css('color', shapeInfo.color);
                    $('#number').text(data.percent * 100 );
                    $('#name').text(data.name);
                } else {
                    $('#number').text('');
                    $('#name').text('');
                }
            }
        }
    });

}
function scoreX3(data) {

    var chart = new F2.Chart({
        id: 'mountNode',
        pixelRatio: window.devicePixelRatio
    });
    chart.clear(); // 清除
    chart.source(data);
    chart.coord('polar', {
        transposed: true,
        radius: 0.61
    });
    chart.legend(false);
    chart.axis(false);
    chart.tooltip(false);

    // 添加饼图文本
    chart.pieLabel({
        sidePadding: 40,
        label1: function label1(data, color) {
            return {
                text: data.name,
                fill: color
            };
        },
        label2: function label2(data) {
            return {
                text: String(Math.floor(data.y * 100) / 100).replace(/\B(?=(\d{3})+(?!\d))/g, ','),
                fill: '#808080',
                fontWeight: 'bold'
            };
        }
    });

    chart.interval().position('const*y').color('name',['#1890FF', '#13C2C2', '#2FC25B', '#FACC14', '#F04864', '#8543E0']).adjust('stack');
    chart.render();


}
function scoreX4(data) {
    var chart = new F2.Chart({
        id: 'mountNode',
        pixelRatio: window.devicePixelRatio
    });

    chart.source(data, {
        sales: {
            tickCount: 5
        }
    });
    chart.tooltip(false);
    chart.interval().position('year*sales');
    chart.render();

    // 绘制柱状图文本
    var offset = -5;
    var canvas = chart.get('canvas');
    var group = canvas.addGroup();
    var shapes = {};
    data.map(function(obj) {
        var point = chart.getPosition(obj);
        var text = group.addShape('text', {
            attrs: {
                x: point.x,
                y: point.y + offset,
                text: obj.sales,
                textAlign: 'center',
                textBaseline: 'bottom',
                fill: '#808080'
            }
        });

        shapes[obj.year] = text; // 缓存该 shape, 便于后续查找
    });

    var lastTextShape = void 0; // 上一个被选中的 text
    // 配置柱状图点击交互
    chart.interaction('interval-select', {
        selectAxisStyle: {
            fill: '#000',
            fontWeight: 'bold'
        },
        mode: 'range',
       /* defaultSelected: {
            year: '1962 年',
            sales: 38
        },*/
        onEnd: function onEnd(ev) {
            var data = ev.data,
                selected = ev.selected;

            lastTextShape && lastTextShape.attr({
                fill: '#808080',
                fontWeight: 'normal'
            });
            if (selected) {
                var textShape = shapes[data.year];
                textShape.attr({
                    fill: '#000',
                    fontWeight: 'bold'
                });
                lastTextShape = textShape;
            }
            canvas.draw();
        }
    });
}



