$(document).ready(function() {
	$('#gestion').DataTable();
	contextPath = $("#rutaContexto").val();
	mostrarCategoriasBD();
	ocultarColumnas("gestion");
	
	var table = $('#gestion').DataTable();
	 
} );

function confirmarAgregarCarrito(urlAgregar) {
	var cantidad = document.getElementById("cantidadProducto").value;
	$('#confirmarAgregarDialog').dialog('close');
	window.location.href = urlAgregar + "&cantidadProducto=" + cantidad;
}

function cancelarConfirmarAgregar() {
	$('#confirmarAgregarDialog').dialog('close');
}

function mostrarMensajeAgregarCarrito(id) {
	
	var urlComprar = contextPath + "/productos-compra!agregarAlCarrito?idSel=" + id;	
	document.getElementById("btnConfirmarAgregar").onclick = function(){ confirmarAgregarCarrito(urlComprar);};
	$('#confirmarAgregarDialog').dialog('open');
	return false;
}
function cerrarMensajeReferencias() {
	$('#mensajeReferenciasDialog').dialog('close');
}

function mostrarCategoriasBD() {
	var json = $("#jsonCategoriasBD").val();
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
	var cell2 = row.insertCell(1);

	//Se crea el checkbox 
	var checkbox = document.createElement('input');
	checkbox.type = "checkbox";
	checkbox.name = "name";
	checkbox.value = "value";
	checkbox.id = "checkbox-" + nombreCategoria;
	checkbox.setAttribute("onchange", "filtrarBusqueda();");
	
	//Se asignan valores a las celdas
	cell1.appendChild(checkbox);
	cell2.innerHTML = nombreCategoria;

}

function ocultarColumnas(tabla) {
	var dataTable = $("#" + tabla).dataTable();
	dataTable.api().column(3).visible(false);
	
}

function filtrarBusqueda() {
	checkboxes = $("[id^='checkbox-']");
	var cadena = "";
	for(var i = 0; i < checkboxes.length ; i ++) {
		checkbox = checkboxes[i];
		if(checkbox.checked) {
			idCheckBox = checkbox.id;
			indexCatgoria = idCheckBox.indexOf("checkbox-") + 9;
			categoria = idCheckBox.substring(indexCatgoria);
			cadena = cadena + " " + categoria;
		}
	}
	console.log("cadena: " + cadena);
	var table = $('#gestion').DataTable();
	table.columns(3).search( cadena, false, true ).draw();
}