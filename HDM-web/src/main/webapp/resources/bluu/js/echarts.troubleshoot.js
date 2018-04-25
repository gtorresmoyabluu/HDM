function createOptionsVerticalBarTrb(data) {
	var options = getOptionsVerticalBarTemplate(data);
	options.tooltip.show = false;
	options.grid.top = '15%';
	options.grid.bottom = '15%';
	options.grid.right = '5%';
	options.grid.left = '6%';
	for (var i = 0; i < data.barSeries.length; i++) {
		options.series[i].animation = false;
		options.yAxis[0].show = true;
		options.yAxis[0].name = data.yAxisName;
		options.yAxis[0].nameLocation = 'middle';
		options.yAxis[0].nameGap = 40;
		options.yAxis[0].axisTick.show = false;
		options.yAxis[0].splitLine.show = true;

		if (data.yAxisMin != null) {
			options.yAxis[0].min = data.yAxisMin;
		}

		if (data.yAxisMax) {
			options.yAxis[0].max = data.yAxisMax;
		}


		options.xAxis[0].name = data.xAxisName;
		options.xAxis[0].nameLocation = 'middle';
		options.xAxis[0].nameGap = 30;
		options.xAxis[0].axisLine.onZero = false;
	}
	return options;
}

function createOptionsHeatMapTrb(data) {
	var options = getOptionsHeatMapTemplate(data);
	options.tooltip.show = false;
	options.visualMap.show = false;
	
	if (data.yAxisMin != null) {
		options.visualMap.min = data.yAxisMin;
	}

	if (data.yAxisMax != null) {
		options.visualMap.max = data.yAxisMax;
	}
	
	if (data.heatMapSerie != null && data.heatMapSerie.colorIni!=null) {
		options.visualMap.inRange.color[0] = data.heatMapSerie.colorIni;
	}
	
	if (data.heatMapSerie != null && data.heatMapSerie.colorEnd!=null) {
		options.visualMap.inRange.color[1] = data.heatMapSerie.colorEnd;
	}

	options.grid.height = null;
	options.grid.y = null;
	options.grid.x = null;
	options.grid.width = null;

	options.grid.top = '15%';
	options.grid.bottom = '25%';
	options.grid.right = '5%';
	options.grid.left = '17%';

	options.yAxis.name = data.yAxisName;
	options.yAxis.nameLocation = 'middle';
	options.yAxis.nameGap = 90;

	options.xAxis.name = data.xAxisName;
	options.xAxis.nameLocation = 'middle';
	options.xAxis.nameGap = 30;

	return options;
}
