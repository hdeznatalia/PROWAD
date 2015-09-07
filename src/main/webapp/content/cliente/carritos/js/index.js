$(document).ready(function() {
	$('#gestion').DataTable( {
        "order": [[ 0, "desc" ]]
    } );
	contextPath = $("#rutaContexto").val();
	ocultarColumnas("gestion");

} );

function confirmarEliminacion(urlEliminar) {
	$('#confirmarEliminacionDialog').dialog('close');
	window.location.href = urlEliminar;
}

function cancelarConfirmarEliminacion() {
	$('#confirmarEliminacionDialog').dialog('close');
}

function mostrarMensajeEliminacion(id) {
	
	var urlEliminar = contextPath + "/categorias/" +id+ "?_method=delete";	
	document.getElementById("btnConfirmarEliminacion").onclick = function(){ confirmarEliminacion(urlEliminar);};
	$('#confirmarEliminacionDialog').dialog('open');
	return false;
}
function cerrarMensajeReferencias() {
	$('#mensajeReferenciasDialog').dialog('close');
}

function ocultarColumnas(tabla) {
	var dataTable = $("#" + tabla).dataTable();
	dataTable.api().column(0).visible(false);
	
}