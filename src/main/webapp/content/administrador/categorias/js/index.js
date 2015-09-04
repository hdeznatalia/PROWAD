$(document).ready(function() {
	$('#gestion').DataTable();
	contextPath = $("#rutaContexto").val();

} );

function confirmarEliminacion(urlEliminar) {
	$('#confirmarEliminacionDialog').dialog('close');
	window.location.href = urlEliminar;
}

function cancelarConfirmarEliminacion() {
	$('#confirmarEliminacionDialog').dialog('close');
}

function mostrarMensajeEliminacion(id) {
	
	var urlEliminar = contextPath + "/atributos/" +id+ "?_method=delete";	
	document.getElementById("btnConfirmarEliminacion").onclick = function(){ confirmarEliminacion(urlEliminar);};
	$('#confirmarEliminacionDialog').dialog('open');
	return false;
}
function cerrarMensajeReferencias() {
	$('#mensajeReferenciasDialog').dialog('close');
}