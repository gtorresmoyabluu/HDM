function getOptionsDonutTemplate(data_) {
    var data = data_.pieSeries;
    var options = {
        title: {
            show: true,
            text: '',
            x: 'center',
            y: '45%',
            textStyle: {
                color: data.color,
                fontSize: 25,
                fontStyle: 'normal',
                fontWeight: 'bold'
            }
        },
        tooltip: {backgroundColor: '#3e464c',
            trigger: 'item',
            formatter: "{d}%"
        },
        series: [
            {
                type: 'pie',
                center: ['50%', '50%'],
                radius: ['50%', '70%'],
                label: {
                    normal: {
                        position: 'center',
                        formatter: function (params) {
                            if (params.name == "other")
                                return "";
                            return "";
                        },
                        textStyle: {
                            fontStyle: 'normal',
                            fontWeight: 'bold',
                            fontSize: 25
                        }
                    }
                },
                data: [{
                        name: 'other',
                        value: data.hismax - data.value,
                        itemStyle: {
                            normal: {
                                color: '#ccc'}
                        }
                    }, {
                        name: data.name,
                        value: data.value,
                        prevalue: data.prevalue,
                        itemStyle: {
                            normal: {
                                color: data.color
                            }
                        }
                    }],
                markPoint: {
                    data: [{
                            symbol: 'triangle',
                            symbolSize: 15,
                            symbolRotate: 0,
                            itemStyle: {
                                normal: {
                                    color: 'black'
                                }
                            },
                            name: data.name,
                            value: 0 + '%',
                            x: '46%',
                            y: '60%',
                            label: {
                                normal: {
                                    show: true,
                                    position: 'right',
                                    formatter: function (params) {
                                        return params.value;
                                    },
                                    textStyle: {
                                        color: 'black',
                                        fontWeight: 'bold',
                                        fontSize: 12 
                                    }
                                }
                            }
                        }
                    ]
                }
            }
        ]
    }
    return options;
}

function getOptionsPieTemplate(data_) {
	var legend = data_.legend != null ? data_.legend : '';
    var data = data_.pieSeries;
    var options = {
    	legend: {
                show: false,
                data: legend,
                orient: 'vertical',
                x: 'left',
        },
    	title: {
            show: false,
            text: '',
            subtext: '',
            x: 'center',
            textStyle: {
                fontWeight: 'lighter'
            }
        },
        tooltip: {backgroundColor: '#3e464c',
            trigger: 'item',
            formatter: "{b}: {c}"
        },        
        series: [{
                name: 'PIE',
                type: 'pie',
                startAngle: 0,
                radius: '70%',
                center: ['50%', '50%'],
                label: {
                    normal: {
                        formatter: '{d} %',
                        textStyle: {
                            fontWeight: 'bold',
                            fontSize: 12
                        }
                    }
                },
                labelLine:{
                    normal:{
                        show:true
                    }
                },
                data: data.data,
                itemStyle: {
                    emphasis: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }]
    };
    return options;
}

function getOptionsHorizontalBarTemplate(data) {
    var legend = data.legend != null ? data.legend : '';
    var options = {
        legend: {
            type: 'plain',
        	show: false,
            data: legend,
            orient: 'horizontal',
            left: 'auto',
            top: 'auto',
            right: 'auto',
            bottom: 'auto'
        },
        tooltip: {
            show: true,
            backgroundColor: '#3e464c',
            trigger: 'item',
            formatter: "{c}",
            axisPointer: {
                type: 'shadow'
            }
        },
        grid: {
            left: '0%',
            right: '0%',
            top: '0%',
            bottom: '0%',
            containLabel: true
        },
        xAxis: [
        	{
                show: false,
                name: '',
            	type: 'value',
            	axisLine: {onZero: true},
                boundaryGap: false,
                axisTick: {
                    show: false
                },
                axisLabel: {
                    interval: null,
                    rotate: 0
                },
                data: data.yAxis,
                splitLine: {
                    show: false,
                    interval: 'auto'
                }
            }],
        yAxis: [
        	{
                type: 'category',
                name: '',
                boundaryGap: true,
                axisTick: {
                    show: false
                },
                axisLabel: {
                    interval: null,
                    rotate: 0
                },
                data: data.yAxis,
                splitLine: {
                    show: false,
                    interval: 'auto'
                }
            }],
        series: createSeriesHorizontalBar(data.barSeries)
    };
    return options;
}

function createSeriesHorizontalBar(data) {

    var result = [];
    for (var i = 0; i < data.length; i++) {

        result.push({
            animation: true,
            itemStyle: {
                normal: {
                    barBorderRadius: 0,
                    label: {
                        show: false,
                        position: 'top',
                        formatter: '{c}',
                        textStyle: {
                            color: '#fff',
                            fontWeight: 'bold',
                            fontSize: 12
                        }
                    }
                }
            },
            name: data[i].name,
            type: 'bar',
            barWidth: 0,
            data: data[i].data
        });
    }
    return result;
}

function getOptionsVerticalBarTemplate(data) {
	var legend = data.legend != null ? data.legend : '';
    var options = {
    		legend: {
                type: 'plain',
            	show: false,
                data: legend,
                orient: 'horizontal',
                left: 'auto',
                top: 'auto',
                right: 'auto',
                bottom: 'auto'
            },
            grid: {
                left: '0%',
                right: '0%',
                top: '0%',
                bottom: '0%',
                containLabel: true
            },
        tooltip: {
            show: true,
            backgroundColor: '#3e464c',
            trigger: 'item',
            formatter: "{c}",
            axisPointer: {
                type: 'shadow'
            }
        },
        
        yAxis: [{
            show: false,
            name: '',
        	type: 'value',
            boundaryGap: false,
            axisTick: {
                show: false
            },
            axisLabel: {
                interval: null,
                rotate: 0
            },
            data: data.yAxis,
            splitLine: {
                show: false,
                interval: 'auto'
            }
        }],
        xAxis: [{
                type: 'category',
                axisLine: {onZero: true},
                name: '',
                boundaryGap: true,
                axisTick: {
                    show: false
                },
                axisLabel: {
                    interval: null,
                    rotate: 0
                },
                data: data.yAxis,
                splitLine: {
                    show: false,
                    interval: 'auto'
                }
            }],
        series: createSeriesVerticalBar(data.barSeries)
    };
    return options;
}

function createSeriesVerticalBar(data) {

    var result = [];
    for (var i = 0; i < data.length; i++) {
        result.push({
            animation: true,
            itemStyle: {
                normal: {
                    barBorderRadius: 0,
                    label: {
                        show: data[i].labelPosition != null,
                        position: data[i].labelPosition,
                        formatter: '{c}',
                        textStyle: {
                            fontWeight: 'bold',
                            fontSize: 12
                        }
                    }
                }
            },
            name: data[i].name,
            type: 'bar',
            barWidth: 0,
            data: data[i].data            
        });
    }
    return result;
}

function getOptionsAngleTemplate(data_) {

    var data = data_.pieSeries;

    var startAngle = 180, endAngle = 0, alpha = startAngle - endAngle;
    var value = data.value;
    var total = data.hismax;
    var name = data.name;

    var options = {
        series: [{
                type: 'pie',
                startAngle: startAngle,
                center: ['50%', '50%'],
                radius: ['0', '70%'],
                label: {
                    normal: {
                        show: false
                    }
                },
                data: [{
                        value: alpha,
                        itemStyle: {
                            normal: {
                                color: '#cccccc'
                            }
                        }
                    }, {
                        value: 360 - alpha,
                        itemStyle: {
                            normal: {
                                color: 'transparent'
                            }
                        }
                    }]
            }, {
                type: 'pie',
                name: name,
                startAngle: value / total * alpha / 2 + (startAngle + endAngle) / 2,
                center: ['50%', '50%'],
                radius: ['0', '70%'],
                minAngle: 1,
                data: [{
                        value: value,
                        itemStyle: {
                            normal: {
                                shadowBlur: 0
                            }
                        },
                        label: {
                            normal: {
                                position: 'inside',
                                formatter: '{c}',
                                textStyle: {
                                    fontWeight: 'bold',
                                    fontSize: 14
                                }
                            }
                        }
                    }, {
                        value: total * (360 / alpha) - value,
                        itemStyle: {
                            normal: {
                                color: 'transparent'
                            }
                        },
                        label: {
                            normal: {
                                position: 'inside',
                                formatter: name,
                                textStyle: {
                                    fontWeight: 'bold',
                                    fontSize: 14
                                }
                            }
                        }
                    }]
            }]
    };
    return options;
}

function getOptionsMiddleAngleTemplate(data_) {

    var data = data_.pieSeries;

    var value = data.value;
    var total = data.hismax;

    var options = {
        title: {
            show: true,
            text: data.value,
            x: 'center',
            y: '30%',
            textStyle: {
                fontSize: 25,
                fontWeight: 'bold'
            },
            subtextStyle: {
                fontSize: 14,
                fontWeight: 'bold'
            }
        },
        tooltip: {
            backgroundColor: '#3e464c',
            trigger: 'item',
            formatter: (((data.value * 100) / data.hismax) * 360) / 1000 + " %"
        },
        series: [{
                type: 'pie',
                startAngle: 180,
                center: ['50%', '40%'],
                radius: ['55%', '70%'],
                label: {
                    normal: {
                        show: false
                    }
                },
                data: [{
                        value: 180,
                        itemStyle: {
                            normal: {
                                color: '#cccccc'
                            }
                        }
                    }, {
                        value: 180,
                        itemStyle: {
                            normal: {
                                color: 'transparent'
                            }
                        }
                    }]
            }, {
                type: 'pie',
                startAngle: 180,
                center: ['50%', '40%'],
                radius: ['55%', '70%'],
                minAngle: 0,
                data: [
                    {
                        value: value,
                        itemStyle: {
                            normal: {
                                shadowBlur: 0
                            }
                        },
                        label: {
                            normal: {
                                position: 'inside',
                                formatter: '',
                                textStyle: {
                                    fontWeight: 'bold',
                                    fontSize: 14
                                }
                            }
                        }
                    },
                    {
                        value: total * (360 / 180) - value,
                        itemStyle: {
                            normal: {
                                color: 'transparent'
                            }
                        }
                    }
                ]
            }]
    };
    return options;
}

function getOptionsSankeyTemplate(data) {
    var options = {
        tooltip: {
            backgroundColor: '#3e464c',
            trigger: 'item',
            formatter: "{c}%"
        },
        series: createSeriesSankey(data.sankeySeries)
    };
    return options;
}

function createSeriesSankey(data_) {
    var result = [];

    var data = [];
    for (var i = 0; i < data_.data.length; i++) {
        data.push({
            name: data_.data[i].name,
            value: data_.data[i].value,
            type: 'country',
            itemStyle:
                    {normal:
                                {
                                }
                    },
            label:
                    {normal:
                                {position: data_.data[i].position}
                    }});
    }

    var links = [];
    for (var i = 0; i < data_.links.length; i++) {
        links.push({
            source: data_.links[i].source,
            target: data_.links[i].target,
            value: data_.links[i].value,
            lineStyle:
                    {
                        normal:
                                {
                                    opacity: 0.05
                                }
                    }});
    }


    result.push({
        left: 150,
        right: 150,
        type: 'sankey',
        layout: 'none',
        nodeWidth: 50,
        data: data,
        links: links,
        label: {
            normal: {
                textStyle: {
                    fontWeight: 'bold',
                    fontSize: 12
                }
            }
        },
        itemStyle: {
            normal: {
                borderWidth: 0,
                borderColor: '#000'
            }
        },
        lineStyle: {
            normal: {
                curveness: 0.5
            }
        }
    });
    return result;
}

function getOptionsAreaTemplate(data) {
    var options = {
        title: {
            text: ''
        },
        tooltip: {
            backgroundColor: '#3e464c',
            trigger: 'axis',
            formatter: function (params) {
                var ret = '';
                for (var i = params.length - 1; i >= 0; i--) {
                    ret += params[i].seriesName + ': ' + params[i].value + '%<br />';
                }
                return ret;
            }
        },
        legend: {
            show: false,
            data: data.legend
        },
        grid: {
            left: '0%',
            right: '0%',
            top: '2%',
            bottom: '0%',
            containLabel: true
        },
        xAxis: [
            {
                type: 'category',
                boundaryGap: false,
                data: data.xAxis
            }
        ],
        yAxis: [
            {
                type: 'value'
            }
        ],
        dataZoom: [{
                type: 'inside',
                start: 0,
                end: 100
            }, {
                handleStyle: {
                    color: '#3e464c'
                },
                borderColor: "#fff",
                fillerColor: 'rgba(204,204,204,0.25)',
                dataBackground: {
                    lineStyle: {
                        color: '#dfa242'
                    },
                    areaStyle: {
                        color: '#dfa242'
                    }
                },
                show: true,
                type: 'slider',
                start: 0,
                end: 100
            }],
        series: createSeriesArea(data.linesSeries)
    };
    return options;
}

function createSeriesArea(data) {

    var result = [];
    for (var i = 0; i < data.length; i++) {

        for (var j = 0; j < data[i].data.length; j++) {
            var o1 = data[0].data[j];
            var o2 = data[1].data[j];
            var o3 = data[2].data[j];

            if (o1 + o2 + o3 > 100)
            {
                var dif = (o1 + o2 + o3) - 100;
                data[2].data[j] = o3 - dif;
            } else if (o1 + o2 + o3 < 100)
            {
                var dif = 100 - (o1 + o2 + o3);
                data[2].data[j] = o3 + dif;
            }
        }

        result.push({
            name: data[i].name,
            type: 'line',
            showSymbol: false,
            stack: 'X',
            areaStyle: {normal: {}},
            data: data[i].data
        });
    }
    return result;
}

function getOptionsLinesTemplate(data, formatter) {
    var options = {
        tooltip: {backgroundColor: '#3e464c',
            trigger: 'axis',
            axisPointer: {type: 'none'},
            formatter: formatter
        },
        legend: {
            show: false,
            data: data.legend,
            textStyle: {
                fontWeight: 'bold',
                fontSize: 12
            }
        },
        grid: {
            left: '0%',
            right: '0%',
            top: '5%',
            bottom: '0%',
            containLabel: true
        },
        xAxis: {
            type: 'category',
            splitLine: {
                show: false,
                interval: 'auto'
            },
            axisLabel: {
                show: true
            },
            axisTick: {
                show: true
            },
            boundaryGap: false,
            "axisLine": {
                lineStyle: {
                    color: '#90979c'
                }
            },
            data: data.xAxis
        },
        yAxis: [{
                type: 'value',
                splitLine: {
                    show: false,
                    interval: 'auto'
                },
                axisLabel: {
                    show: true,
                    formatter: '{value} '
                },
                axisTick: {
                    show: true
                }
            }],
        dataZoom: [
            {
                type: 'inside',
                start: 0,
                end: 100,
                zoomLock: false
            },
            {
                handleStyle: {
                    color: '#3e464c'
                },
                borderColor: "#fff",
                fillerColor: 'rgba(204,204,204,0.25)',
                dataBackground: {
                    lineStyle: {
                        color: '#dfa242'
                    },
                    areaStyle: {
                        color: '#dfa242'
                    }
                },
                show: true,
                type: 'slider',
                start: 0,
                end: 100
            }],
        series: createSeriesLines(data.linesSeries)
    };
    return options;
}

function createSeriesLines(data) {
    var result = [];
    for (var i = 0; i < data.length; i++) {
        result.push({
            name: data[i].name,
            type: 'line',
            step: false,
            showSymbol: false,
            lineStyle: {
                normal: {
                    width: 3
                }
            },
            data: data[i].data
        });
    }
    return result;
}


function getOptionsHeatMapTemplate(data) {
    var options = {
    	    tooltip: {
    	        position: {}
    	    },
    	    animation: false,
    	    grid: {
    	        height: '0%',
    	        y: '0%',
    	        x: '15%',
    	        width: '80%',    	        	
    	    },
    	    xAxis: {
    	        type: 'category',
    	        data: data.xAxis,
    	        splitArea: {
    	            show: true
    	        }
    	    },
    	    yAxis: {
    	        type: 'category',
    	        data: data.yAxis,
    	        splitArea: {
    	            show: true
    	        }
    	    },
    	    visualMap: {
    	        min: 0,
    	        max: data.heatMapSerie.max,
    	        calculable: true,
    	        orient: 'horizontal',
    	        left: 'center',
    	        bottom: '5%',
    	        inRange: {
    	            color: ['#fff', '#000'],
    	        }
    	    },
        series: createSeriesHeatMap(data.heatMapSerie)
    };
    return options;
}

function createSeriesHeatMap(data) {
    var result = [];

        result.push({
             type: 'heatmap',
             data: data.data.map(function (item) {
                     return [item[1], item[0], item[2] || '-'];
                 }),
             label: {
                 normal: {
                     show: true
                 }
             },
             itemStyle: {
                 emphasis: {
                     shadowBlur: 10,
                     shadowColor: 'rgba(0, 0, 0, 0.5)'
                 }
             }
        });
    
    return result;
}