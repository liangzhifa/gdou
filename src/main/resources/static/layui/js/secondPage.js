$(document).ready(function(){

    $.getJSON('/wx/getScoreAll', function(res) {
        //console.log(res)
        var data=res.data1;
        //console.log(data)
        var chart = new F2.Chart({
            id: 'getScoreAll',
            pixelRatio: window.devicePixelRatio
        });
        chart.source(data);
        chart.scale('date', {
            type: 'timeCat',
            tickCount: 3
        });
        chart.scale('value', {
            tickCount: 3
        });
        chart.tooltip({
            custom: true, // 自定义 tooltip 内容框
            onChange: function onChange(obj) {
                var legend = chart.get('legendController').legends.top[0];
                var tooltipItems = obj.items;
                var legendItems = legend.items;
                var map = {};
                legendItems.map(function(item) {
                    map[item.name] = _.clone(item);
                });
                tooltipItems.map(function(item) {
                    var name = item.name;
                    var value = item.value;
                    if (map[name]) {
                        map[name].value = value;
                    }
                });
                legend.setItems(_.values(map));
            },
            onHide: function onHide() {
                var legend = chart.get('legendController').legends.top[0];
                legend.setItems(chart.getLegendItems().country);
            }
        });
        chart.line().position('date*value').color('type');
        chart.render();

        /**
         * data下方
         * @type {F2.Chart}
         */
        var data=res.data2;
        var chart = new F2.Chart({
            id: 'getScoreAll2',
            pixelRatio: window.devicePixelRatio
        });

        chart.coord('polar');
        chart.source(data, {
            score: {
                min: 0,
                max: 120,
                nice: false,
                tickCount: 4
            }
        });
        chart.tooltip({
            custom: true, // 自定义 tooltip 内容框
            onChange: function onChange(obj) {
                var legend = chart.get('legendController').legends.top[0];
                var tooltipItems = obj.items;
                var legendItems = legend.items;
                var map = {};
                legendItems.map(function(item) {
                    map[item.name] = _.clone(item);
                });
                tooltipItems.map(function(item) {
                    var name = item.name;
                    var value = item.value;
                    if (map[name]) {
                        map[name].value = value;
                    }
                });
                legend.setItems(_.values(map));
            },
            onHide: function onHide() {
                var legend = chart.get('legendController').legends.top[0];
                legend.setItems(chart.getLegendItems().country);
            }
        });
        chart.axis('score', {
            label: function label(text, index, total) {
                if (index === total - 1) {
                    return null;
                }
                return {
                    top: true
                };
            },
            grid: {
                lineDash: null,
                type: 'arc' // 弧线网格
            }
        });
        chart.axis('item', {
            grid: {
                lineDash: null
            }
        });
        chart.line().position('item*score').color('user');
        chart.point().position('item*score').color('user').style({
            stroke: '#fff',
            lineWidth: 1
        });
        chart.render();



    });

});