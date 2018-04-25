function createOptionsDonut(data) {

    if (data.pieSeries.color == null) {
        data.pieSeries.color = '#3e464c';
    }

    var options = getOptionsDonutTemplate(data);

    options.title.text = data.pieSeries.value;
    options.title.textStyle.fontSize = divWidth > 200 ? 25 : (divWidth > 120 ? 20 : 10);

    var increase = (data.pieSeries.value==0 || data.pieSeries.prevalue==0 ? 0 : ((data.pieSeries.value / data.pieSeries.prevalue - 1) * 100));
    options.series[0].markPoint.data[0].symbolSize = increase == 0 ? 0 : 15;
    options.series[0].markPoint.data[0].symbolRotate = increase >= 0 ? 0 : 180;
    options.series[0].markPoint.data[0].itemStyle.normal.color = increase > 10 || increase < -10 ? 'red' : 'black';
    options.series[0].markPoint.data[0].value = increase.toFixed(2) + '%';
    options.series[0].markPoint.data[0].x = (divWidth > 500 ? '46%' : (divWidth > 400 ? '44%' : (divWidth > 200 ? '42%' : (divWidth > 100 ? '41%' : '40%'))));
    options.series[0].markPoint.data[0].y = (divWidth > 200 ? '60%' : (divWidth > 100 ? '80%' : '70%'));
    options.series[0].markPoint.data[0].label.normal.textStyle.color = increase > 10 || increase < -10 ? 'red' : 'black';

    return options;
}

function createOptionsDonutOperativa(data) {
    var options = createOptionsDonut(data);

    options.title.text += "%";

    return options;
}

function createOptionsPieSalud(data) {
    var options = getOptionsPieTemplate(data);

    options.tooltip.formatter = "{b}: {c} seg";
    options.series[0].color = ['#4caf50', '#f44336', '#dddddd'];

    return options;
}

function createOptionsPieOperativo(data) {
    var options = getOptionsPieTemplate(data);

    options.tooltip.formatter = "{b}: {c}";
    options.series[0].color = ['#e91e63', '#3f51b5', '#673ab7'];

    return options;
}

function createOptionsHorizontalBar(data) {
    usedColors = ['#3e464c', '#dfa242', '#607d8b', '#00bcd4', '#ffc107', '#009688'];
    var options = getOptionsHorizontalBarTemplate(data);
    options.tooltip.formatter = "{c} seg";

    for (var i = 0; i < data.barSeries.length; i++) {
        options.series[i].itemStyle.normal.barBorderRadius = 20;
        options.series[i].itemStyle.normal.color = function (params) {
            return usedColors[(data.barSeries.length > 1 ? params.seriesIndex : params.dataIndex)]
        };
        options.series[i].barWidth = 30;
        options.series[i].itemStyle.normal.label.show = true;
        options.series[i].itemStyle.normal.label.position = "insideRight";
    }
    return options;
}

function createOptionsHorizontalBarStack(data) {
    usedColors = ['#4caf50', '#f44336', '#dddddd'];
    var options = getOptionsHorizontalBarTemplate(data);
    options.legend.show = true;
    options.tooltip.formatter = "{c}%";
    for (var i = 0; i < data.barSeries.length; i++) {
        options.series[i].itemStyle.normal.color = function (params) {
            return usedColors[(data.barSeries.length > 1 ? params.seriesIndex : params.dataIndex)]
        };
        options.series[i].stack = 'X';
        options.series[i].barWidth = 30;
        options.series[i].itemStyle.normal.label.show = true;
        options.series[i].itemStyle.normal.label.position = "insideLeft";
    }
    return options;
}

function createOptionsVerticalBar(data) {
    usedColors = ['#4caf50', '#f44336', '#dddddd'];
    var options = getOptionsVerticalBarTemplate(data);
    options.tooltip.formatter = "{c} seg";
    for (var i = 0; i < data.barSeries.length; i++) {
        options.series[i].itemStyle.normal.color = function (params) {
            return usedColors[params.dataIndex]
        };
        options.series[i].barWidth = 50;
        options.series[i].itemStyle.normal.label.show = true;
        options.series[i].itemStyle.normal.label.position = "top";
    }
    return options;
}

function createOptionsAngle(data) {
    usedColors = ['#3e464c', '#dfa242', '#607d8b', '#00bcd4', '#ffc107', '#009688', '#f44336', '#ff9800', '#4caf50', '#ff5722', '#673ab7', '#3f51b5', '#e91e63'];
    var color = usedColors[Math.floor(Math.random() * usedColors.length)];
    var options = getOptionsAngleTemplate(data);
    options.color = usedColors;
    options.series[1].data[0].itemStyle.normal.color = color;
    options.series[1].data[0].itemStyle.normal.shadowColor = color;
    options.series[1].data[1].label.normal.textStyle.color = color;
    return options;
}

function createOptionsMiddleAngle(data) {
    usedColors = ['#3e464c', '#dfa242', '#607d8b', '#00bcd4', '#ffc107', '#009688', '#f44336', '#ff9800', '#4caf50', '#ff5722', '#673ab7', '#3f51b5', '#e91e63'];
    var color = usedColors[Math.floor(Math.random() * usedColors.length)];
    var options = getOptionsMiddleAngleTemplate(data);
    options.color = usedColors;
    options.title.textStyle.color = color;
    options.title.subtextStyle.color = color;
    options.series[1].data[0].itemStyle.normal.color = color;
    options.series[1].data[0].itemStyle.normal.shadowColor = color;

    return options;
}

function createOptionsSankey(data) {
    usedColors = ['#3e464c', '#dfa242', '#607d8b'];
    var options = getOptionsSankeyTemplate(data);

    var colorPos = 0;
    for (var i = 0; i < options.series[0].data.length; i++) {
        options.series[0].data[i].itemStyle.normal = {color: usedColors[colorPos]};


        if (colorPos >= usedColors.length - 1)
            colorPos = 0;
        else
            colorPos++;
    }

    for (var i = 0; i < options.series[0].links.length; i++) {
        options.series[0].links[i].lineStyle.normal = {color: usedColors[i]};
    }

    return options;
}

function createOptionsArea(data) {

    var options = getOptionsAreaTemplate(data);
    options.color = ['#e91e63', '#3f51b5', '#673ab7'];
    options.grid.bottom = '12%';
    return options;
}

function createOptionsLines(data, colors) {
    var options = getOptionsLinesTemplate(data, null);

    options.color = colors;
    options.grid.bottom = '12%';
    options.dataZoom[0].zoomLock = false;
    options.dataZoom[1].show = true;
    options.xAxis.axisLabel.show = true;
    options.xAxis.axisTick.show = true;
    options.yAxis[0].axisLabel.show = true;
    options.yAxis[0].axisTick.show = true;

    return options;
}