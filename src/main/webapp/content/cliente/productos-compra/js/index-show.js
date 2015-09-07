$(document)
		.ready(
				function() {
					contextPath = $("#rutaContexto").val();
					
					mostrarCategoriasBD();
					seleccionarCategoriasModel();
					mostrarValoresAtributos();
					$("formularioDes").children().disabled = true;
					console.log("hola");
					
});

function seleccionarCategoriasModel() {
	var json = $("#jsonCategoriasModel").val();
	if (json !== "" && json !== "[]") {
		var parsedJson = JSON.parse(json);

		$
				.each(
						parsedJson,
						function(i, item) {
							categoriaSE = quitarEspacios(item.nombre);
							mostrarAtributosCategoriaDesdeArreglo(categoriaSE, item.categoriasAtributo);
						});
	}
}

function mostrarValoresAtributos() {
	var json = $("#jsonProductoAtributo").val();
	if (json !== "" && json !== "[]") {
		var parsedJson = JSON.parse(json);

		$
				.each(
						parsedJson,
						function(i, item) {
							tipoDato = item.id.atributo.tipoDato.nombre;
							console.log("tipoDato: " + tipoDato);

							nombreAtrSE = quitarEspacios(item.id.atributo.nombre);
							
							if(tipoDato == "Cadena") {
								valor = item.valorCadena;
							} else if(tipoDato == "Entero") {
								valor = item.valorEntero;
							} else if(tipoDato == "Flotante") {
								valor = item.valorFlotante;
							} else  if(tipoDato == "Fecha") {
								valor = item.valorFecha;
							}
							console.log("nombreAtrSE: " + nombreAtrSE);
							var inputSimilares = $("[id$='" + nombreAtrSE + "']");
							console.log("inputSimilares: " + inputSimilares.length);
							for(var i = 0; i < inputSimilares.length; i++) {
								inputSimilares[i].value = valor;
							}
						});
	}
}

function mostrarCategoriasBD() {
	var json = $("#jsonCategoriasModel").val();
	if (json !== "" && json !== "[]") {
		var parsedJson = JSON.parse(json);

		$
				.each(
						parsedJson,
						function(i, item) {
							agregarFila(item.nombre);
						});
	}
}

function agregarFila(nombreCategoria) {
	var tabla = document.getElementById("tablaCategorias");
	// Se obtiene el total de filas
	var totalFilas = tabla.rows.length;

	// Se crea un <tr> vacío y se agrega en la última posición
	var row = tabla.insertRow(totalFilas);

	// Se agrega una celda
	var cell1 = row.insertCell(0);
	//Se asignan valores a las celdas
	cell1.innerHTML = nombreCategoria;

}

function verificarAtributosCategoriaSel(checkboxCategoria) {
	fila = checkboxCategoria.parentNode.parentNode;
	filaArray = fila.cells;
	var nombre =  filaArray[1].innerHTML;
	
	nombreSE = quitarEspacios(nombre);
	var checkboxCategoria = document.getElementById("checkbox-" + nombreSE);
	if(checkboxCategoria.checked) {
		verificarAtributosCategoria(nombre);
	} else {
		$("#divFormulario-" + nombreSE).remove();
	}
}

function verificarAtributosCategoria(nombreCategoria) {
	rutaVerificarAtributos = contextPath + '/productos!verificarAtributosCategoria';
	$.ajax({
		dataType : 'json',
		url : rutaVerificarAtributos,
		type: "POST",
		data : {
			nombreCategoria : nombreCategoria
		},
		success : function(data) {
			mostrarAtributosCategoria(nombreCategoria, data);
		},
		error : function(err) {
			alert("AJAX error in request: " + JSON.stringify(err, null, 2));
		}
	});
}

function mostrarAtributosCategoria(nombreCategoria, json) {
	console.log("json: " +json);
	if(json != null && json.length > 0) {
		nombreCategoriaSE = quitarEspacios(nombreCategoria);
		$("#seccionAtributos").append("<div class='formulario' id='divFormulario-" + nombreCategoriaSE  + "'> " 
				+ "<div class='tituloFormulario'>" + nombreCategoriaSE + "</div>"
				+ "<div class='seccion'>" 
						+ "<table id='tabla-" + nombreCategoriaSE + "'>"
						+ "</table>"
				+ "</div>"
			+ "</div>");
		$
				.each(
						json,
						function(i, item) {
							console.log("nombre " + item.nombre);
							console.log("tipoDato " + item.tipoDato);
							agregarFilaFormulario(item.nombre, item.tipoDato.nombre, nombreCategoria);
							
						});

	}
}

function mostrarAtributosCategoriaDesdeArreglo(nombreCategoria, arreglo) {
	if(arreglo != null && arreglo.length > 0) {
		nombreCategoriaSE = quitarEspacios(nombreCategoria);
		$("#seccionAtributos").append("<div class='formulario' id='divFormulario-" + nombreCategoriaSE  + "'> " 
				+ "<div class='tituloFormulario'>" + nombreCategoriaSE + "</div>"
				+ "<div class='seccion'>" 
						+ "<table id='tabla-" + nombreCategoriaSE + "'>"
						+ "</table>"
				+ "</div>"
			+ "</div>");
		for(var i = 0; i < arreglo.length; i ++) {
			item = arreglo[i];
			tipoDato = item.id.atributo.tipoDato.nombre;
			if(tipoDato == "Cadena") {
				valor = item.valorCadena;
			} else if(tipoDato == "Entero") {
				valor = item.valorEntero;
			} else if(tipoDato == "Flotante") {
				valor = item.valorFlotante;
			} else  if(tipoDato == "Fecha") {
				valor = item.valorFecha;
			}
			
			agregarFilaFormulario(item.id.atributo.nombre, tipoDato, nombreCategoria);
		}
	}
}

function agregarFilaFormulario(nombreAtr, tipoDato, nombreCategoria) {
	nombreAtrSE = quitarEspacios(nombreAtr);
	nombreCategoriaSE = quitarEspacios(nombreCategoria);
	var input = "<input type='text' id='input-TD" + tipoDato + "-CAT" + nombreCategoriaSE + "-ATR" + nombreAtrSE + "' " +
			"oninput='cambiarAtributosSimilares(this)' onchange='cambiarAtributosSimilares(this)' disabled='true'>";
	$("#tabla-" + nombreCategoriaSE).append("<tr>" +
			"<td class='label'>" + nombreAtr + "</td>" +
			"<td>" + input + "</td>" +
		"</tr>");
	if(tipoDato == "Fecha") {
		selector = "#input-TD" + tipoDato + "-CAT" + nombreCategoriaSE + "-ATR" + nombreAtrSE;
		$(selector).datepicker({
			  dateFormat: "dd/mm/yy"
		});
	}
}

function cambiarAtributosSimilares(input) {
	idInput = input.id
	index = idInput.indexOf("-ATR") + 4;
	nombreAtributo = idInput.substring(index);
	var inputSimilares = $("[id$='" + nombreAtributo + "']");
	for(var i=0; i < inputSimilares.length; i++) {
		inputSimilares[i].value = input.value;
	}
}

function prepararEnvio() {
	try {
		tablaCategoriaToJson();
		tablaToJson();
		return true;
	} catch (err) {
		alert("Ocurrió un error: " + err);
		return false;
	}	
}

function tablaCategoriaToJson() {
	var inputs = $("[id^='checkbox-']");
	var arregloCategorias = [];
	for(var i = 0; i < inputs.length; i ++) {
		input = inputs[i];
		idInput = input.id;
		indexCategoria = idInput.indexOf("checkbox-") + 9;
		categoriaSE = idInput.substring(indexCategoria);
		categoria = agregarEspacios(categoriaSE);
		if(input.checked) {
			arregloCategorias.push(new Categoria(categoria));
		}
	}
	var jsonCategoriasModel = JSON.stringify(arregloCategorias);
	document.getElementById("jsonCategoriasModel").value = jsonCategoriasModel;
}

function tablaToJson() {
	var inputs = $("[id^='input-TD']");
	var arregloProductoAtributo = [];
	for(var i = 0; i < inputs.length; i ++) {
		input = inputs[i];
		idInput = input.id;
		indexTipoDato = idInput.indexOf("-TD");
		indexCategoria = idInput.indexOf("-CAT");
		indexAtributo = idInput.indexOf("-ATR");
		
		tipoDato = idInput.substring(indexTipoDato  + 3, indexCategoria);
		categoriaSE = idInput.substring(indexCategoria  + 4, indexAtributo);
		atributoSE = idInput.substring(indexAtributo  + 4);
		
		categoria = agregarEspacios(categoriaSE);
		atributo = agregarEspacios(atributoSE);
		
		valorCadena = null;
		valorEntero = null;
		valorFlotante = null;
		valorFecha = null;
		if(tipoDato == "Cadena") {
			valorCadena = input.value;
		} else if(tipoDato == "Entero") {
			valorEntero = input.value;
		} else if(tipoDato == "Flotante") {
			valorFlotante = input.value;
		} else  if(tipoDato == "Fecha") {
			valorFecha = input.value;
		} 
		
		arregloProductoAtributo.push(new ProductoAtributo(atributo, valorCadena, valorEntero, valorFlotante, valorFecha));
	}
	
	var jsonAtributosModel = JSON.stringify(arregloProductoAtributo);
	document.getElementById("jsonProductoAtributo").value = jsonAtributosModel;
}

function quitarEspacios(cadenaConEsp) {
	return cadenaConEsp.replace(/\s/g, "_");
}

function agregarEspacios(cadenaSinEsp) {
	return cadenaSinEsp.replace(/_/g, " ");
}