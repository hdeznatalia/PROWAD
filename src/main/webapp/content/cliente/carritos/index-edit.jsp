<?xml version="1.0" encoding="UTF-8" ?>

<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
	xmlns:s="/struts-tags" xmlns:sj="/struts-jquery-tags">
	<jsp:directive.page language="java"
		contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" />
	<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Carritos</title>
<![CDATA[
	<script type="text/javascript" charset="utf8" src="${pageContext.request.contextPath}/resources/scripts/constructores.js"></script>
	<script type="text/javascript" charset="utf8" src="${pageContext.request.contextPath}/resources/scripts/validaciones.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/jquery.caret.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/jquery.atwho.js"></script>
	<script type="text/javascript" charset="utf8" src="${pageContext.request.contextPath}/content/cliente/carritos/js/index-edit.js"></script>	
]]>

</head>
<body>

	<h1>Carrito</h1>

	<s:actionmessage theme="jquery" />
	<s:actionerror theme="jquery" />
	<br />

	<p class="instrucciones">Ingrese la información solicitada.</p>
	<s:form autocomplete="off" id="frmCategoria" theme="simple"
		action="%{#pageContext.request.contextPath}/productos" method="post" onsubmit="return false;">
		
		<div class="formulario">
			<div class="tituloFormulario">Productos</div>
			<div class="seccion">
				<p class="instrucciones">A continuación se muestran los productos asociados al Carrito.</p>
				<table id="tablaProductoModel" class="tablaGestion" cellspacing="0" width="100%">
					<thead>
						<tr>
							<th style="width: 40%;"><s:text name="colNombre"/></th>
							<th style="width: 20%;"><s:text name="colCantidad"/></th>
							<th style="width: 20%;"><s:text name="colPrecio"/></th>
							<th style="width: 20%;"><!-- id --></th>
							<th style="width: 20%;"><s:text name="colAcciones"/></th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
		
		<br />
		<div align="center">
			<input class="boton" type="button"
			onclick="mostrarMensajeComprarCarrito(${idSel});" value="Comprar" />
		</div>
		<s:hidden id="jsonProductosCarritoModelTabla" name="jsonProductosCarritoModelTabla"
			value="%{jsonProductosCarritoModelTabla}" />
	</s:form>
	
	<!-- EMERGENTE CONFIRMAR -->
	<sj:dialog id="confirmarAgregarDialog" title="Confirmación" autoOpen="false"
		minHeight="100" minWidth="400" modal="true" draggable="true">
		<s:form autocomplete="off" id="frmConfirmarEliminacion" name="frmConfirmarEliminacionName" theme="simple">
				<div class="seccion">
				<span>Agregue la nueva cantidad, disponibles: <span id="cantidadBodega"></span></span>
				<table>
					<tr>
						<td class="label obligatorio"><s:text name="labelCantidad" /></td>
						<td><s:textfield name="cantidadProducto" maxlength="255" id="cantidadProducto" value="0"
								cssErrorClass="input-error" cssClass="ui-widget" />
							<s:fielderror fieldName ="cantidadProducto" cssClass="error"
								theme="jquery" /></td>
					</tr>
				</table>
				</div>
			<br />
			<div align="center">
				<input id = "btnConfirmarAgregar" type="button" onclick="" value="Aceptar"/> <input
					type="button" onclick="cancelarConfirmarAgregar();" value="Cancelar" />
			</div>
		</s:form>
	</sj:dialog>
	
	<!-- EMERGENTE CONFIRMAR -->
	<sj:dialog id="confirmarComprarDialog" title="Confirmación" autoOpen="false"
		minHeight="100" minWidth="400" modal="true" draggable="true">
		<s:form autocomplete="off" id="frmConfirmarEliminacion" name="frmConfirmarEliminacionName" theme="simple">
				<div class="seccion">
				<span>¿Desea finalizar la compra?</span>
				</div>
			<br />
			<div align="center">
				<input id = "btnConfirmarComprar" type="button" onclick="" value="Aceptar"/> <input
					type="button" onclick="cancelarConfirmarComprar();" value="Cancelar" />
			</div>
		</s:form>
	</sj:dialog>
	
	<!-- EMERGENTE CONFIRMAR ELIMINACIÓN -->
	<sj:dialog id="confirmarEliminacionDialog" title="Confirmación" autoOpen="false"
		minHeight="100" minWidth="400" modal="true" draggable="true">
		<s:form autocomplete="off" id="frmConfirmarEliminacion" name="frmConfirmarEliminacionName" theme="simple">
				<div class="seccion">
				¿Desea eliminar este producto del Carrito?
				</div>
			<br />
			<div align="center">
				<input id = "btnConfirmarEliminacion" type="button" onclick="" value="Aceptar"/> <input
					type="button" onclick="cancelarConfirmarEliminacion();" value="Cancelar" />
			</div>
		</s:form>
	</sj:dialog>		
</body>
	</html>
</jsp:root>