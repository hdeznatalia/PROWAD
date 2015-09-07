$(document).ready(function() {
	$('#tablaProductoModel').DataTable();
	$('#tablaProductoBD').DataTable();
	contextPath = $("#rutaContexto").val();
	var json = $("#jsonProductosBDTabla").val();
	if (json !== "") {
		var parsedJson = JSON.parse(json);
		$
				.each(
						parsedJson,
						function(i, item) {
							if(item.cantidad > 0) {
								var producto = construirFilaBD(item.nombre, item.precio, item.productosAtributo);
								dataTableCDT.addRow("tablaProductoBD", producto);
							}
						});
	}
	json = $("#jsonProductosModelTabla").val();
	if (json !== "") {
		var parsedJson = JSON.parse(json);
		$
				.each(
						parsedJson,
						function(i, item) {
							var producto = construirFilaModel(item.nombre, item.tipoDato.nombre, item.cantidad);
							dataTableCDT.addRow("tablaProductoModel", producto);
						});
	}
} );

function construirFilaBD(nombre, precio, productosAtributo) {
	for(var i = 0; i < productosAtributo.length; i++) {
		var categoria
	}
	var row = [
				nombre,
				precio, 
				productosAtributoArray,
				"<center>"
						+ "<a button='true' onclick='mostrarProducto(this);'>"
						+ "<img class='icon'  id='icon' src='"
						+ window.contextPath
						+ "/resources/images/icons/ver.png' title='Detalle del Producto'/></a>"
						+ "&nbsp;"
						+ "<a button='true' onclick='agregarProducto(this);'>"
						+ "<img class='icon'  id='icon' src='"
						+ window.contextPath
						+ "/resources/images/icons/mas.png' title='Agregar Producto'/></a>"
						+ "</center>" ];
	return row;
}

function construirFilaModel(nombre, precio, cantidad) {
	var row = [
				nombre,
				cantidad,
				precio,
				"<center>"
						+ "<a button='true' onclick='mostrarProducto(this);'>"
						+ "<img class='icon'  id='icon' src='"
						+ window.contextPath
						+ "/resources/images/icons/ver.png' title='Detalle del Producto'/></a>"
						+ "&nbsp;"
						+ "<a onclick='dataTableCDT.deleteRow(tablaProductoModel, this);' button='true'>"
						+ "<img class='icon'  id='icon' src='"
						+ window.contextPath
						+ "/resources/images/icons/eliminar.png' title='Eliminar'/></a>"
						+ "</center>" ];
	return row;
}

function agregarProducto(productoSel) {
	var filaSel = $("#tablaProductoBD").DataTable().row($(productoSel).parents('tr'));
	var filaSelArray = filaSel.data();
	var nombre = filaSelArray[0];
	var nombreTipoDato = filaSelArray[1];
	var cantidad = prompt("Cantidad", "1");;
	var filaProductoNvo = construirFilaModel(nombre, nombreTipoDato, cantidad);
	if(!dataTableCDT.exist("tablaProductoModel", nombre, 0)) {
		dataTableCDT.addRow("tablaProductoModel", filaProductoNvo);
	} else {
		alert("Este producto ya está asociado al Carrito");
	}
	
}

function prepararEnvio() {
	try {
		tablaToJson("tablaProductoModel");
		return true;
	} catch (err) {
		alert("Ocurrió un error.");
	}
}

function tablaToJson(idTable) {
	var table = $("#" + idTable).dataTable();
	var arregloProductos = [];

	for (var i = 0; i < table.fnSettings().fnRecordsTotal(); i++) {
		var nombre = table.fnGetData(i, 0);
		var tipoDato = table.fnGetData(i, 1);

		arregloProductos.push(new Producto(nombre, tipoDato));
	}
	var jsonProductos = JSON.stringify(arregloProductos);
	document.getElementById("jsonProductosModelTabla").value = jsonProductos;
}

function mostrarProducto(registro) {
	var row = $("#tablaAtributo").DataTable().row($(registro).parents('tr'));
	
	document.getElementById("filaAtributo").value = row.index();
	
	var cells = row.data();
	console.log("cells: " + cells);
	
	document.getElementById("producto.nombre").value = cells[3];
	document.getElementById("producto.precio").value = cells[0];
	
	mostrarCategorias();
	seleccionarCategoriasModel();
	mostrarValoresAtributos();
		
	$('#produtoDialog').dialog('open');
}

function cerrarEmergente() {
	$('#produtoDialog').dialog('close');
}

function mostrarCategorias(categorias) {
	var json = $("#jsonCategoriasBD").val();
	if (json !== "" && json !== "[]") {
		var parsedJson = JSON.parse(json);

		$
				.each(
						parsedJson,
						function(i, item) {
							agregarFila(item.nombre);
							///
							selector = "checkbox-" + categoriaSE;
							document.getElementById(selector).checked = true;
							mostrarAtributosCategoriaDesdeArreglo(categoriaSE, item.categoriasAtributo);
							///
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
	var cell2 = row.insertCell(1);

	//Se crea el checkbox 
	var checkbox = document.createElement('input');
	checkbox.type = "checkbox";
	checkbox.name = "name";
	checkbox.value = "value";
	checkbox.id = "checkbox-" + nombreCategoria;
	checkbox.setAttribute("onchange", "verificarAtributosCategoriaSel(this);");
	
	//Se asignan valores a las celdas
	cell1.appendChild(checkbox);
	cell2.innerHTML = nombreCategoria;

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