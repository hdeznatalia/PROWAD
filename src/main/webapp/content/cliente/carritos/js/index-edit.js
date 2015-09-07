$(document).ready(function() {
	$('#tablaProductoModel').DataTable();
	contextPath = $("#rutaContexto").val();
	var json = $("#jsonProductosCarritoModelTabla").val();
	if (json !== "") {
		var parsedJson = JSON.parse(json);
		$
				.each(
						parsedJson,
						function(i, item) {
							var producto = construirFilaModel(item.id.producto.nombre, item.cantidad, "$ " + item.id.producto.precio, item.id.producto.id, item.id.producto.cantidad);
							console.log("producto: " + producto);
							dataTableCDT.addRow("tablaProductoModel", producto);
						});
	}
	ocultarColumnas("tablaProductoModel");
} );

function construirFilaModel(nombre, cantidad, precio, id, cantidadBodega) {
	var row = [
				nombre,
				cantidad,
				precio,
				id,
				"<center>"
						+ "<a button='true' onclick='mostrarMensajeAgregarCarrito(" + id + ", " + cantidadBodega + ", " + cantidad + ");'>"
						+ "<img class='icon'  id='icon' src='"
						+ window.contextPath
						+ "/resources/images/icons/editar.png' title='Cambiar cantidad'/></a>"
						+ "<a button='true' onclick='mostrarMensajeEliminacion(" + id + ");'>"
						+ "<img class='icon'  id='icon' src='"
						+ window.contextPath
						+ "/resources/images/icons/eliminar.png' title='Eliminar del Carrito'/></a>"
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
	document.getElementById("jsonProductosCarritoModelTabla").value = jsonProductos;
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


function confirmarAgregarCarrito(urlAgregar) {
	var cantidad = document.getElementById("cantidadProducto").value;
	console.log("cantidad: " + cantidad);
	$('#confirmarAgregarDialog').dialog('close');
	window.location.href = urlAgregar + "&cantidadProducto=" + cantidad;
}

function cancelarConfirmarAgregar() {
	$('#confirmarAgregarDialog').dialog('close');
}

function mostrarMensajeAgregarCarrito(id, cantidadBodega, cantidadProductoAnterior) {
	
	var urlComprar = contextPath + "/productos-compra!agregarAlCarrito?idSel=" + id + "&cantidadProductoAnterior=" + cantidadProductoAnterior;	
	document.getElementById("btnConfirmarAgregar").onclick = function(){ confirmarAgregarCarrito(urlComprar);};

	$("#cantidadBodega").empty();
	document.getElementById("cantidadBodega").appendChild(document.createTextNode(cantidadBodega));
	$('#confirmarAgregarDialog').dialog('open');
	return false;
}

function ocultarColumnas(tabla) {
	var dataTable = $("#" + tabla).dataTable();
	dataTable.api().column(3).visible(false);
	
}

function confirmarComprarCarrito(urlComprar) {
	$('#confirmarComprarDialog').dialog('close');
	window.location.href = urlComprar;
}

function cancelarConfirmarComprar() {
	$('#confirmarComprarDialog').dialog('close');
}

function mostrarMensajeComprarCarrito(id) {
	
	var urlComprar = contextPath + "/carritos!comprarCarrito?idSel=" + id;	
	document.getElementById("btnConfirmarComprar").onclick = function(){ confirmarComprarCarrito(urlComprar);};
	$('#confirmarComprarDialog').dialog('open');
	return false;
}

function confirmarEliminacion(urlEliminar) {
	$('#confirmarEliminacionDialog').dialog('close');
	window.location.href = urlEliminar;
}

function cancelarConfirmarEliminacion() {
	$('#confirmarEliminacionDialog').dialog('close');
}

function mostrarMensajeEliminacion(id) {
	
	var urlEliminar = contextPath + "/carritos/" +id+ "?_method=delete";	
	document.getElementById("btnConfirmarEliminacion").onclick = function(){ confirmarEliminacion(urlEliminar);};
	$('#confirmarEliminacionDialog').dialog('open');
	return false;
}
function cerrarMensajeReferencias() {
	$('#mensajeReferenciasDialog').dialog('close');
}