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
	<script type="text/javascript" charset="utf8" src="${pageContext.request.contextPath}/content/almacen/productos/js/index-editNew.js"></script>	
]]>

</head>
<body>

	<h1>Registrar Producto</h1>

	<s:actionmessage theme="jquery" />
	<s:actionerror theme="jquery" />
	<br />

	<p class="instrucciones">Ingrese la información solicitada.</p>
	<s:form autocomplete="off" id="frmProducto" theme="simple"
		action="%{#pageContext.request.contextPath}/productos" method="post" onsubmit="return prepararEnvio();">
		<div class="formulario">
			<div class="tituloFormulario">Información general del Producto</div>
			<table class="seccion">
				<tr>
					<td class="label obligatorio"><s:text name="labelNombre" /></td>
					<td><s:textfield name="model.nombre" maxlength="255"
							cssErrorClass="input-error" cssClass="inputFormulario ui-widget" />
						<s:fielderror fieldName ="model.nombre" cssClass="error"
							theme="jquery" /></td>
				</tr>
			</table>
		</div>
		<div class="formulario">
			<div class="tituloFormulario">Categorías</div>
			<div class="seccion">
				<p class="instrucciones">Seleccione las Categorías a las que pertenece el Producto.</p>
				<div class="seccion">
					<table id="tablaCategorias">
						<tbody>
						
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<div id="seccionAtributos"><!--  --></div>
		
		<br />
		<div align="center">
			<s:submit class="boton" value="Aceptar" />

			<s:url var="urlGestion"
				value="%{#pageContext.request.contextPath}/productos">
			</s:url>
			<input class="boton" type="button"
				onclick="location.href='${urlGestion}'"
				value="Cancelar" />
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