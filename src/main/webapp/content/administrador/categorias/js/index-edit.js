$(document).ready(function() {
	$('#tablaAtributoModel').DataTable();
	$('#tablaAtributoBD').DataTable();
	contextPath = $("#rutaContexto").val();
	var json = $("#jsonAtributosBDTabla").val();
	if (json !== "") {
		var parsedJson = JSON.parse(json);
		$
				.each(
						parsedJson,
						function(i, item) {
							if(item.unidadTamanio != null) {
								abreviatura = item.unidadTamanio.abreviatura;
							} else {
								abreviatura = null;
							}
							
							var atributo = construirFilaBD(item.nombre, item.tipoDato.nombre);
							dataTableCDT.addRow("tablaAtributoBD", atributo);
						});
	}
	json = $("#jsonAtributosModelTabla").val();
	if (json !== "") {
		var parsedJson = JSON.parse(json);
		$
				.each(
						parsedJson,
						function(i, item) {
							if(item.unidadTamanio != null) {
								abreviatura = item.unidadTamanio.abreviatura;
							} else {
								abreviatura = null;
							}
							
							var atributo = construirFilaModel(item.nombre, item.tipoDato.nombre);
							dataTableCDT.addRow("tablaAtributoModel", atributo);
						});
	}
} );

function construirFilaBD(nombre, tipoDato) {
	var row = [
				nombre,
				tipoDato,
				"<center>"
						+ "<a button='true' onclick='agregarAtributo(this);'>"
						+ "<img class='icon'  id='icon' src='"
						+ window.contextPath
						+ "/resources/images/icons/mas.png' title='Agregar Atributo'/></a>"
						+ "</center>" ];
	return row;
}

function construirFilaModel(nombre, tipoDato) {
	var row = [
				nombre,
				tipoDato,
				"<center>"
						+ "<a onclick='dataTableCDT.deleteRow(tablaAtributoModel, this);' button='true'>"
						+ "<img class='icon'  id='icon' src='"
						+ window.contextPath
						+ "/resources/images/icons/eliminar.png' title='Eliminar'/></a>"
						+ "</center>" ];
	return row;
}

function agregarAtributo(atributoSel) {
	var filaSel = $("#tablaAtributoBD").DataTable().row($(atributoSel).parents('tr'));
	var filaSelArray = filaSel.data();
	var nombre = filaSelArray[0];
	var nombreTipoDato = filaSelArray[1];
	var filaAtributoNvo = construirFilaModel(nombre, nombreTipoDato);
	if(!dataTableCDT.exist("tablaAtributoModel", nombre, 0)) {
		dataTableCDT.addRow("tablaAtributoModel", filaAtributoNvo);
	} else {
		alert("Este atributo ya está asociado a la Categoría");
	}
	
}

function prepararEnvio() {
	try {
		tablaToJson("tablaAtributoModel");
		return true;
	} catch (err) {
		alert("Ocurrió un error.");
	}
}

function tablaToJson(idTable) {
	var table = $("#" + idTable).dataTable();
	var arregloAtributos = [];

	for (var i = 0; i < table.fnSettings().fnRecordsTotal(); i++) {
		var nombre = table.fnGetData(i, 0);
		var tipoDato = table.fnGetData(i, 1);

		arregloAtributos.push(new Atributo(nombre, tipoDato));
	}
	var jsonAtributos = JSON.stringify(arregloAtributos);
	document.getElementById("jsonAtributosModelTabla").value = jsonAtributos;
}