function createOptionsPie1(data) {
	var options = getOptionsPieTemplate(data);

	options.legend.show = true;
	options.series[0].label.normal.show = false;
	options.series[0].labelLine.normal.show = false;

	return options;
}

function createOptionsDonut1(data) {
	var options = getOptionsPieTemplate(data);

	options.legend.show = true;
	options.legend.type = 'scroll';
	options.legend.orient = 'horizontal', options.legend.x = 'right';
	options.series[0].label.normal.show = false;
	options.series[0].labelLine.normal.show = false;
	options.series[0].radius = [ '50%', '70%' ];
	options.animation = false;

	return options;
}

function createOptionsDonut2(data) {
	var options = getOptionsPieTemplate(data);

	options.legend.show = true;
	options.legend.x = 'right';
	options.series[0].label.normal.show = false;
	options.series[0].labelLine.normal.show = false;
	options.series[0].radius = [ '50%', '70%' ];
	options.animation = false;

	return options;
}

function createOptionsVBars1(data) {
	var options = getOptionsVerticalBarTemplate(data);

	options.legend.show = false;
	options.yAxis[0].show = true;
	options.yAxis[0].axisTick.show = true;
	options.xAxis[0].axisLabel.interval = (data.yAxis.length / 5 | 0);
	options.grid.top = '15%';

	for (var i = 0; i < data.barSeries.length; i++) {
		options.series[i].animation = false;
	}

	options.tooltip.trigger = 'item';
	options.tooltip.formatter = function(data) {
		var _str = "";
		if (options.series.length > 1) {
			_str += data.seriesName + "<br>";
			_str += '<span style="display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:'
					+ data.color + '"></span>';
			_str += data.name + ": ";
			_str += data.value;
		} else {
			_str += data.name + "<br>";
			_str += data.value;
		}
		return _str;
	};

	return options;
}

function createOptionsVBars2(data, optionsData) {
	var options = getOptionsVerticalBarTemplate(data);

	options.legend.show = optionsData.legendShow;
	options.legend.orient = optionsData.legendOrient;
	options.legend.type = optionsData.legendType;

	optionsData.legendAlign;

	switch (optionsData.legendAlign) {
	case 'top_right':
		options.legend.top = 0;
		options.legend.right = 0;
		break;
	case 'top_left':
		options.legend.top = 0;
		options.legend.left = 0;
		break;
	case 'top_center':
		options.legend.top = 0;
		break;
	case 'bottom_right':
		options.legend.bottom = 0;
		options.legend.right = 0;
		break;
	case 'bottom_left':
		options.legend.bottom = 0;
		options.legend.left = 0;
		break;
	case 'bottom_center':
		options.legend.bottom = 0;
		break;
	case 'middle_right':
		options.legend.top = 'center';
		options.legend.right = 0;
		break;
	case 'middle_left':
		options.legend.top = 'center';
		options.legend.left = 0;
		break;
	case 'middle_center':
		options.legend.top = 'center';
		break;
	}

	options.yAxis[0].show = optionsData.yAxisShow;
	options.yAxis[0].axisTick.show = optionsData.yAxisTickShow;
	options.yAxis[0].splitLine.show = optionsData.yAxisSplitLineShow;
	options.yAxis[0].boundaryGap.show = optionsData.yAxisBoundaryGapShow;
	options.yAxis[0].inverse = optionsData.yAxisInverse;
	options.yAxis[0].axisLabel.rotate = optionsData.yAxisLabelRotate;
	if (optionsData.fullPercent) {
		options.yAxis[0].max = 100;
	}
	options.xAxis[0].show = optionsData.xAxisShow;
	options.xAxis[0].axisTick.show = optionsData.xAxisTickShow;
	options.xAxis[0].splitLine.show = optionsData.xAxisSplitLineShow;
	options.xAxis[0].boundaryGap.show = optionsData.xAxisBoundaryGapShow;
	options.xAxis[0].inverse = optionsData.xAxisInverse;
	options.xAxis[0].axisLine.onZero = optionsData.xAxisOnZero;
	options.xAxis[0].axisLabel.rotate = optionsData.xAxisLabelRotate;
	options.xAxis[0].axisLabel.interval = (data.yAxis.length / 5 | 0);
	options.grid.top = optionsData.gridTop + '%';
	options.grid.left = optionsData.gridLeft + '%';
	options.grid.right = optionsData.gridRight + '%';
	options.grid.bottom = optionsData.gridBottom + '%';
	
	options.tooltip.show = optionsData.tooltipShow;

	for (var i = 0; i < data.barSeries.length; i++) {
		options.series[i].animation = optionsData.animation;
		options.series[i].stack = optionsData.stack;
		options.series[i].barWidth = optionsData.barWidth;
		options.series[i].itemStyle.normal.barBorderRadius = optionsData.borderRadius;
		options.series[i].itemStyle.normal.label.show = optionsData.labelShow;
		options.series[i].itemStyle.normal.label.position = optionsData.labelPosition;

		if (optionsData.colors != null) {
			options.series[i].itemStyle.normal.color = function(params) {
				return optionsData.colors[(data.barSeries.length > 1 ? params.seriesIndex
						: params.dataIndex)]
			};
		}

		if (optionsData.fullPercent) {
			options.series[i].trueData = JSON.parse(JSON
					.stringify(options.series[i].data));
		}
	}

	if (optionsData.fullPercent) {
		// Recalculo los datos en base a porcentajes
		for (var j = 0; j < data.barSeries[0].data.length; j++) {
			var total = 0;
			// 1ª iteración para obtener el total de la serie
			for (var i = 0; i < data.barSeries.length; i++) {
				total += options.series[i].data[j];
			}
			// 2ª iteración para obener los porcentajes
			for (var i = 0; i < data.barSeries.length; i++) {
				options.series[i].data[j] = (options.series[i].data[j] * 100)
						/ total;
			}
		}

		// Modifico el tooltip para mostrar los valores reales
		options.tooltip.trigger = 'item';
		options.tooltip.formatter = function(data) {
			var _str = "";
			_str += data.seriesName + "<br>";
			_str += '<span style="display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:'
					+ data.color + '"></span>';
			_str += data.name + ": ";
			_str += (options.series[data.seriesIndex].trueData[data.dataIndex]);

			return _str;
		};
	} else {
		options.tooltip.trigger = 'item';
		options.tooltip.formatter = function(data) {
			var _str = "";
			if (options.series.length > 1) {
				_str += data.seriesName + "<br>";
				_str += '<span style="display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:'
						+ data.color + '"></span>';
				_str += data.name + ": ";
				_str += data.value;
			} else {
				_str += data.name + "<br>";
				_str += data.value;
			}
			return _str;
		};
	}
	
	

	return options;
}

function createOptionsHBars1(data) {

	var usedColors = [ '#3e464c', '#dfa242', '#607d8b', '#00bcd4', '#ffc107',
			'#009688', '#001f3f', '#0074D9', '#7FDBFF', '#39CCCC', '#3D9970',
			'#2ECC40', '#01FF70', '#FFDC00', '#FF851B', '#FF4136', '#85144b',
			'#F012BE' ];

	// Antes de procesar los datos, los quiero ordenar de mayor a menor
	var arr = data.barSeries[0].data;
	var arr2 = data.yAxis;
	for (var i = 0; i < arr.length; i++) {
		for (var j = i + 1; j < arr.length; j++) {
			if (arr[i] > arr[j]) {

				var swap = arr[i];
				arr[i] = arr[j];
				arr[j] = swap;

				var swap2 = arr2[i];
				arr2[i] = arr2[j];
				arr2[j] = swap2;

			}
		}
	}

	var options = getOptionsHorizontalBarTemplate(data);

	options.xAxis[0].axisLabel.show = true;
	options.xAxis[0].axisTick.show = true;
	options.xAxis[0].axisLine.show = true;

	for (var i = 0; i < data.barSeries.length; i++) {
		options.series[i].itemStyle.normal.color = function(params) {
			return usedColors[(data.barSeries.length > 1 ? params.seriesIndex
					: params.dataIndex)]
		};
		options.series[i].barWidth = 20;
		options.series[i].itemStyle.normal.label.show = false;
		options.series[i].animation = false;
	}

	return options;
}

function createOptionsHBars2(data, optionsData) {
	var options = getOptionsHorizontalBarTemplate(data);

	options.legend.show = optionsData.legendShow;
	options.legend.orient = optionsData.legendOrient;
	options.legend.type = optionsData.legendType;

	optionsData.legendAlign;

	switch (optionsData.legendAlign) {
	case 'top_right':
		options.legend.top = 0;
		options.legend.right = 0;
		break;
	case 'top_left':
		options.legend.top = 0;
		options.legend.left = 0;
		break;
	case 'top_center':
		options.legend.top = 0;
		break;
	case 'bottom_right':
		options.legend.bottom = 0;
		options.legend.right = 0;
		break;
	case 'bottom_left':
		options.legend.bottom = 0;
		options.legend.left = 0;
		break;
	case 'bottom_center':
		options.legend.bottom = 0;
		break;
	case 'middle_right':
		options.legend.top = 'center';
		options.legend.right = 0;
		break;
	case 'middle_left':
		options.legend.top = 'center';
		options.legend.left = 0;
		break;
	case 'middle_center':
		options.legend.top = 'center';
		break;
	}

	options.yAxis[0].show = optionsData.yAxisShow;
	options.yAxis[0].axisTick.show = optionsData.yAxisTickShow;
	options.yAxis[0].splitLine.show = optionsData.yAxisSplitLineShow;
	options.yAxis[0].boundaryGap.show = optionsData.yAxisBoundaryGapShow;
	options.yAxis[0].inverse = optionsData.yAxisInverse;
	options.yAxis[0].axisLabel.rotate = optionsData.yAxisLabelRotate;
	
	options.xAxis[0].show = optionsData.xAxisShow;
	options.xAxis[0].axisTick.show = optionsData.xAxisTickShow;
	options.xAxis[0].splitLine.show = optionsData.xAxisSplitLineShow;
	options.xAxis[0].boundaryGap.show = optionsData.xAxisBoundaryGapShow;
	options.xAxis[0].inverse = optionsData.xAxisInverse;
	options.xAxis[0].axisLine.onZero = optionsData.xAxisOnZero;
	options.xAxis[0].axisLabel.rotate = optionsData.xAxisLabelRotate;
	options.xAxis[0].axisLabel.interval = (data.yAxis.length / 5 | 0);
	
	if (optionsData.fullPercent) {
		options.xAxis[0].max = 100;
	}
	
	options.grid.top = optionsData.gridTop + '%';
	options.grid.left = optionsData.gridLeft + '%';
	options.grid.right = optionsData.gridRight + '%';
	options.grid.bottom = optionsData.gridBottom + '%';
	
	options.tooltip.show = optionsData.tooltipShow;

	for (var i = 0; i < data.barSeries.length; i++) {
		options.series[i].animation = optionsData.animation;
		options.series[i].stack = optionsData.stack;
		options.series[i].barWidth = optionsData.barWidth;
		options.series[i].itemStyle.normal.barBorderRadius = optionsData.borderRadius;
		options.series[i].itemStyle.normal.label.show = optionsData.labelShow;
		options.series[i].itemStyle.normal.label.position = optionsData.labelPosition;

		if (optionsData.colors != null) {
			options.series[i].itemStyle.normal.color = function(params) {
				return optionsData.colors[(data.barSeries.length > 1 ? params.seriesIndex
						: params.dataIndex)]
			};
		}

		if (optionsData.fullPercent) {
			options.series[i].trueData = JSON.parse(JSON
					.stringify(options.series[i].data));
		}
	}

	if (optionsData.fullPercent) {
		// Recalculo los datos en base a porcentajes
		for (var j = 0; j < data.barSeries[0].data.length; j++) {
			var total = 0;
			// 1ª iteración para obtener el total de la serie
			for (var i = 0; i < data.barSeries.length; i++) {
				total += options.series[i].data[j];
			}
			// 2ª iteración para obener los porcentajes
			for (var i = 0; i < data.barSeries.length; i++) {
				options.series[i].data[j] = (options.series[i].data[j] * 100)
						/ total;
			}
		}

		// Modifico el tooltip para mostrar los valores reales
		options.tooltip.trigger = 'item';
		options.tooltip.formatter = function(data) {
			var _str = "";
			_str += data.seriesName + "<br>";
			_str += '<span style="display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:'
					+ data.color + '"></span>';
			_str += data.name + ": ";
			_str += (options.series[data.seriesIndex].trueData[data.dataIndex]);

			return _str;
		};
	} else {
		options.tooltip.trigger = 'item';
		options.tooltip.formatter = function(data) {
			var _str = "";
			if (options.series.length > 1) {
				_str += data.seriesName + "<br>";
				_str += '<span style="display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:'
						+ data.color + '"></span>';
				_str += data.name + ": ";
				_str += data.value;
			} else {
				_str += data.name + "<br>";
				_str += data.value;
			}
			return _str;
		};
	}
	
	

	return options;
}

function createOptionsHStackBars1(data) {
	var options = getOptionsHorizontalBarTemplate(data);

	options.legend.show = true;
	options.tooltip.formatter = null;

	options.grid.top = '15%';
	for (var i = 0; i < data.barSeries.length; i++) {
		options.series[i].stack = 'X';
		options.series[i].animation = false;
	}

	return options;
}

function createOptionsHStackBarsFull1(data) {

	var usedColors = [ '#ee948e', '#b2d1a7', '#3e464c', '#dfa242', '#607d8b',
			'#00bcd4', '#ffc107', '#009688', '#f44336', '#ff9800', '#4caf50',
			'#ff5722', '#673ab7', '#3f51b5', '#e91e63' ];

	var options = getOptionsHorizontalBarTemplate(data);

	options.legend.show = true;
	options.legend.type = 'scroll';
	options.xAxis[0].show = true;
	options.xAxis[0].axisTick.show = true;
	options.xAxis[0].axisLine.show = true;
	options.xAxis[0].axisLabel.show = true;
	options.xAxis[0].axisLabel.formatter = '{value} %';
	options.xAxis[0].axisLabel.interval = (data.yAxis.length / 5 | 0);
	options.grid.top = '5%';

	for (var i = 0; i < data.barSeries.length; i++) {
		options.series[i].stack = 'X';
		options.series[i].animation = false;
		options.series[i].trueData = JSON.parse(JSON
				.stringify(options.series[i].data));
		options.series[i].itemStyle.normal.color = function(params) {
			return usedColors[(data.barSeries.length > 1 ? params.seriesIndex
					: params.dataIndex)]
		};
	}

	// Recalculo los datos en base a porcentajes
	for (var j = 0; j < data.barSeries[0].data.length; j++) {
		var total = 0;
		// 1ª iteración para obtener el total de la serie
		for (var i = 0; i < data.barSeries.length; i++) {
			total += options.series[i].data[j];
		}
		// 2ª iteración para obener los porcentajes
		for (var i = 0; i < data.barSeries.length; i++) {
			options.series[i].data[j] = (options.series[i].data[j] * 100)
					/ total;
		}
	}

	options.tooltip.trigger = 'item';
	options.tooltip.formatter = function(data) {
		var _str = "";
		_str += data.seriesName + "<br>";
		_str += '<span style="display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:'
				+ data.color + '"></span>';
		_str += data.name + ": ";
		_str += (options.series[data.seriesIndex].trueData[data.dataIndex]);

		return _str;
	};

	return options;
}

function createOptionsLines1(data) {

	var formatter = function(params) {
		return params[0].name + '<br />' + params[0].value;
	};

	var options = getOptionsLinesTemplate(data, formatter);

	options.grid.bottom = '17%';
	options.dataZoom[0].zoomLock = false;
	options.dataZoom[1].show = true;
	options.xAxis.axisLabel.show = true;
	options.xAxis.axisTick.show = true;
	options.yAxis[0].axisLabel.show = true;
	options.yAxis[0].axisTick.show = true;
	options.animation = false;

	return options;
}

function createOptionsLines2(data) {
	var options = getOptionsLinesTemplate(data, null);

	options.grid.bottom = '17%';
	options.dataZoom[0].zoomLock = false;
	options.dataZoom[1].show = true;
	options.xAxis.axisLabel.show = true;
	options.xAxis.axisTick.show = true;
	options.yAxis[0].axisLabel.show = true;
	options.yAxis[0].axisTick.show = true;
	options.animation = false;

	return options;
}

function createOptionsVStackBars1(data) {
	var options = getOptionsVerticalBarTemplate(data);

	options.legend.show = true;
	options.legend.type = 'scroll';
	options.yAxis[0].show = true;
	options.yAxis[0].axisTick.show = true;
	options.xAxis[0].axisLabel.interval = (data.yAxis.length / 5 | 0);
	options.grid.top = '15%';
	for (var i = 0; i < data.barSeries.length; i++) {
		options.series[i].stack = 'X';
		options.series[i].animation = false;
	}

	return options;
}

function createOptionsVStackBarsFull1(data) {

	var options = getOptionsVerticalBarTemplate(data);

	options.legend.show = true;
	options.legend.type = 'scroll';
	options.yAxis[0].show = true;
	options.yAxis[0].max = 100;
	options.yAxis[0].axisTick.show = true;
	options.yAxis[0].axisLabel.formatter = '{value} %';
	options.xAxis[0].axisLabel.interval = (data.yAxis.length / 5 | 0);
	;
	options.grid.top = '15%';

	for (var i = 0; i < data.barSeries.length; i++) {
		options.series[i].stack = 'X';
		options.series[i].animation = false;

		options.series[i].trueData = JSON.parse(JSON
				.stringify(options.series[i].data));
	}

	// Recalculo los datos en base a porcentajes
	for (var j = 0; j < data.barSeries[0].data.length; j++) {
		var total = 0;
		// 1ª iteración para obtener el total de la serie
		for (var i = 0; i < data.barSeries.length; i++) {
			total += options.series[i].data[j];
		}
		// 2ª iteración para obener los porcentajes
		for (var i = 0; i < data.barSeries.length; i++) {
			options.series[i].data[j] = (options.series[i].data[j] * 100)
					/ total;
		}
	}

	// Modifico el tooltip para mostrar los valores reales
	options.tooltip.trigger = 'item';
	options.tooltip.formatter = function(data) {
		var _str = "";
		_str += data.seriesName + "<br>";
		_str += '<span style="display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:'
				+ data.color + '"></span>';
		_str += data.name + ": ";
		_str += (options.series[data.seriesIndex].trueData[data.dataIndex]);

		return _str;
	};

	return options;
}

function createOptionsHeatMap1(data) {
	var options = getOptionsHeatMapTemplate(data);
	options.tooltip.show = false;
	return options;
}

function createOptionsHeatMap2(data) {
	var options = getOptionsHeatMapTemplate(data);
	options.tooltip.show = false;
	return options;
}