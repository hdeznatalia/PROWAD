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
	<script type="text/javascript" charset="utf8" src="${pageContext.request.contextPath}/content/cliente/carritos/js/index-show.js"></script>	
]]>

</head>
<body>

	<h1>Carrito</h1>

	<s:actionmessage theme="jquery" />
	<s:actionerror theme="jquery" />
	<br />

	<s:form autocomplete="off" id="frmCategoria" theme="simple"
		action="%{#pageContext.request.contextPath}/productos" method="post" onsubmit="return false;">
		
		<div class="formulario">
			<div class="tituloFormulario">Información general del Carrito</div>
			<table class="seccion">
					<tr>
						<td class="label"><s:text name="colFechaCompra"/></td>
						<td class="inputFormulario ui-widget"><s:text name="model.fechaCompra"/></td>
					</tr>
			</table>
		</div>
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
						</tr>
					</thead>
				</table>
			</div>
		</div>
		
		<br />
		<div align="center">
			<input class="boton" type="button"
			onclick="location.href='${urlPrev}'" value="Aceptar" />
		</div>
		<s:hidden id="jsonProductosCarritoModelTabla" name="jsonProductosCarritoModelTabla"
			value="%{jsonProductosCarritoModelTabla}" />
	</s:form>
	
	
</body>
	</html>
</jsp:root>