var testCourse=new Array();
$(document).ready(function(){

    /*获取该学生所有的课程*/
    $.getJSON('/wx/getCourseAll', function(res) {
        for (var i=0;i<res.length;i++){
            testCourse.push(res[i])
        }
    });
    /*查询该学生历年的单科成绩*/
    $.getJSON('/wx/getScoreByCourse', function(result) {
        console.log(JSON.stringify(result))
        getScoresByCourse(result.data,result.ticks)
    });
    /*页面 4 的 留言查询*/
    $.ajax({
        url:"/wx/getLeavingMsg",
        success:function(result){
                var tab4 = $("#tab4");
                if (result!=null){
                    console.log(result)
                    for (var res in result) {

                        res=result[res]
                        var temp=
                            '<div class="weui-form-preview">'+
                                '<div class="weui-form-preview__bd">'+
                                    '<label class="weui-form-preview__label">留言类型</label>'+
                                    '<span class="weui-form-preview__value">'+res.type+'</span>'+
                                '</div>'+
                                '<div class="weui-form-preview__bd">'+
                                    '<div class="weui-form-preview__item">'+
                                        '<label class="weui-form-preview__label">内容</label>'+
                                        ' <span class="weui-form-preview__value">'+res.content+'</span>'+
                                    '</div>'+
                                '</div>'+
                                '<div class="weui-form-preview__bd">'+
                                    '<div class="weui-form-preview__item">'+
                                        '<label class="weui-form-preview__label">留言时间</label>'+
                                        ' <span class="weui-form-preview__value">'+res.creatTime+'</span>'+
                                    '</div>'+
                                '</div>'+
                            '</div>';
                        $("#tab4").append(temp)
                        $("#tab4").append(" </br>");
                    }
                }

        }
    });

});
$("#tap3Course").picker({
    title: "请选择考试科目:",
    cols: [
        {
            textAlign: 'center',
            values: testCourse
        }
    ],
    onChange: function(p, v, dv) {
        //console.log("onChange p=>"+p+" v=>"+v+" dv=>"+dv);
    },
    onClose: function(p) {
        $.ajax({
            url:"/wx/getScoreByCourse",
            data: {course:p["value"][0]},
            success:function(result){

                console.log(JSON.stringify(result.data.length))
                getScoresByCourse(result.data,result.ticks);

                $.toptip('成功', 'success');
            }
        });
    }

});
function getScoresByCourse(data,ticks) {
    var chart = new F2.Chart({
        id: 'tab3Id1',
        pixelRatio: window.devicePixelRatio,
        padding: 35
    });
    chart.clear(); // 清除
    chart.source(data, {
        date: {
            ticks: ticks,
            textAlign: 'center'
        }
    });
    chart.axis('date', {
        label: {
            textAlign: 'start',
            fill: 'rgba(255, 255, 255, 0.5)'
        },
        tickLine: {
            length: 5,
            stroke: 'rgba(255, 255, 255, 0.5)'
        },
        line: {
            stroke: 'rgba(255, 255, 255, 0.5)'
        }
    });
    chart.axis('score', {
        label: {
            fill: 'rgba(255, 255, 255, 0.5)'
        },
        grid: {
            lineDash: null,
            stroke: 'rgba(255, 255, 255, 0.1)'
            // strokeOpacity: 0.4
        }
    });
    chart.line().position('date*score').color('#FFEE31');
    // 标域，过低
    chart.guide().regionFilter({
        start: ['min', 'min'],
        end: ['max', 60],
        color: '#4BC1C2'
    });
    // 标域，过高
    chart.guide().regionFilter({
        start: ['min', 100],
        end: ['max', 'max'],
        color: '#F95727'
    });
    chart.render();

}
