//DRAW CHARTS

var divWidth;

/**
 * Crea un gráfico con las opciones por defecto
 * 
 * @param id
 *            identificador del div donde se pintará el gráfico
 * @param data
 *            conjunto de valores usados para rellenar el gráfico
 * @param optionsFunction
 *            nombre de la función que se encarga de pintar el gráfico
 * @returns {undefined}
 */
function createChart(id, data, optionsFunction) {
	divWidth = document.getElementById(id).offsetWidth;
	var _data = parseJson(data);
	var chart = document.getElementById(id);
	var myChart = echarts.init(chart);
	try {
		var option = window[optionsFunction](_data);
	} catch (err) {		
		var option = {
			    title: {
			        text: 'ERROR',
			        subtext: 'No se ha podido cargar la gráfica',
			        x: 'center',
			        y: 'center',
			        textStyle: {
			            fontWeight: 'normal',
			            fontSize: 22
			        }
			    }
			};		
		console.log(err);
	} finally {
		myChart.setOption(option);
	}

}

function createChart2(id, data, optionsFunction, optionsData) {
	divWidth = document.getElementById(id).offsetWidth;
	var _data = parseJson(data);
	var chart = document.getElementById(id);
	var myChart = echarts.init(chart);
	try {
		var option = window[optionsFunction](_data, parseJson(optionsData));
	} catch (err) {		
		var option = {
			    title: {
			        text: 'ERROR',
			        subtext: 'No se ha podido cargar la gráfica',
			        x: 'center',
			        y: 'center',
			        textStyle: {
			            fontWeight: 'normal',
			            fontSize: 22
			        }
			    }
			};		
		console.log(err);
	} finally {
		myChart.setOption(option);
	}

}

/**
 * Crea un gráfico que responde al evento onclick
 * 
 * @param id
 *            identificador del div donde se pintará el gráfico
 * @param data
 *            conjunto de valores usados para rellenar el gráfico
 * @param optionsFunction
 *            nombre de la función que se encarga de pintar el gráfico
 * @param clickFunction
 *            nombre de la función que se invocará mediante el evento onclick
 * @returns {undefined}
 */
function createClickableChart(id, data, optionsFunction, clickFunction) {
	divWidth = document.getElementById(id).offsetWidth;
	var _data = parseJson(data);
	var chart = document.getElementById(id);
	var myChart = echarts.init(chart);

	if (clickFunction != null && clickFunction == 'rcLoadIntegracion') {
		var colorList = [ '#3e464c', '#dfa242', '#607d8b', '#00bcd4',
				'#ffc107', '#009688' ];
		myChart.on('click', function(params) {
			window[clickFunction]([ {
				name : 'diagName',
				value : params.name
			}, {
				name : 'diagValue',
				value : params.value
			}, {
				name : 'diagIndex',
				value : params.dataIndex
			}, {
				name : 'diagColor',
				value : colorList[params.dataIndex]
			} ])
		});
	}

	var option = window[optionsFunction](_data);
	myChart.setOption(option);
}

/**
 * Crea un gráfico cuyos colores son especifcados desde el servidor y no desde
 * la función optionsFunction
 * 
 * @param id
 *            identificador del div donde se pintará el gráfico
 * @param data
 *            conjunto de valores usados para rellenar el gráfico
 * @param optionsFunction
 *            nombre de la función que se encarga de pintar el gráfico
 * @param colors
 *            conjunto de colores que se usarán para pintar el grafico
 * @returns {undefined}
 */
function createColoredChart(id, data, optionsFunction, colors) {
	var _data = parseJson(data);
	var chart = document.getElementById(id);
	var myChart = echarts.init(chart);
	var option = window[optionsFunction](_data, colors);
	myChart.setOption(option);
}

// PRIVATE FUNCTIONS
function parseJson(t) {
	if (t === undefined) {
		return '';
	}
	if (t == null) {
		return '';
	}
	if (t == '') {
		return '';
	}
	return $.parseJSON(t);
}