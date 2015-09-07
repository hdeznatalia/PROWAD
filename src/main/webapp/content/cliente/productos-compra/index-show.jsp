<?xml version="1.0" encoding="UTF-8" ?>

<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
	xmlns:s="/struts-tags" xmlns:sj="/struts-jquery-tags">
	<jsp:directive.page language="java"
		contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" />
	<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Productos</title>
<![CDATA[
	<script src="${pageContext.request.contextPath}/resources/template/themes/smoothness-prisma/jquery-ui.js"></script>
	<script type="text/javascript" charset="utf8" src="${pageContext.request.contextPath}/resources/scripts/constructores.js"></script>
	<script type="text/javascript" charset="utf8" src="${pageContext.request.contextPath}/resources/scripts/validaciones.js"></script>
	<script type="text/javascript" charset="utf8" src="${pageContext.request.contextPath}/content/cliente/productos-compra/js/index-show.js"></script>	
]]>

</head>
<body>

	<h1>Detalle del Producto</h1>

	<s:actionmessage theme="jquery" />
	<s:actionerror theme="jquery" />
	<br />

	<s:form autocomplete="off" id="frmProducto" theme="simple"
		action="%{#pageContext.request.contextPath}/productos-compra/" method="post" onsubmit="return false;">
		<div id="formularioDes">
		<div class="formulario">
			<div class="tituloFormulario">Información general del Producto</div>
			<table class="seccion">
				<tr>
					<td class="label"><s:text name="labelNombre" /></td>
					<td class="inputFormulario ui-widget"><s:text name="model.nombre"/></td>
				</tr>
				<tr>
					<td class="label"><s:text name="labelCantidad" /> en bodega</td>
					<td class="inputFormulario ui-widget"><s:text name="model.cantidad"/></td>
				</tr>
				<tr>
					<td class="label"><s:text name="labelPrecio" /></td>
					<td class="inputFormulario ui-widget">$ <s:text name="model.precio"/></td>
				</tr>
			</table>
		</div>
		<div class="formulario">
			<div class="tituloFormulario">Categorías</div>
			<div class="seccion">
				<div class="seccion">
					<table id="tablaCategorias">
						<tbody>
						
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<div id="seccionAtributos"><!--  --></div>
		</div>
		<br />
		<div align="center">
			<input class="boton" type="button"
			onclick="location.href='${urlPrev}'" value="Aceptar" />
		</div>
		<s:hidden id="jsonCategoriasBD" name="jsonCategoriasBD"
			value="%{jsonCategoriasBD}" />
		<s:hidden id="jsonCategoriasModel" name="jsonCategoriasModel"
			value="%{jsonCategoriasModel}" />
		<s:hidden id="jsonProductoAtributo" name="jsonProductoAtributo"
			value="%{jsonProductoAtributo}" />
		
	</s:form>
</body>
	</html>
</jsp:root>