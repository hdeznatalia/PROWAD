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
	<script type="text/javascript" charset="utf8" src="${pageContext.request.contextPath}/content/cliente/carritos/js/index-editNew.js"></script>	
]]>

</head>
<body>

	<h1>Registrar Carrito</h1>

	<s:actionmessage theme="jquery" />
	<s:actionerror theme="jquery" />
	<br />

	<p class="instrucciones">Ingrese la información solicitada.</p>
	<s:form autocomplete="off" id="frmCategoria" theme="simple"
		action="%{#pageContext.request.contextPath}/productos" method="post" onsubmit="return prepararEnvio()">
		
		<div class="formulario">
			<div class="tituloFormulario">Productos</div>
			<div class="seccion">
				<p class="instrucciones">A continuación se muestran los productos disponibles.</p>
				<table id="tablaProductoBD" class="tablaGestion" cellspacing="0" width="100%">
					<thead>
						<tr>
							<th style="width: 40%;"><s:text name="colNombre"/></th>
							<th style="width: 40%;"><s:text name="colPrecio"/></th>
							<th style="width: 0%;"><!-- jsonProductoAtributos --></th>
							<th style="width: 20%;"><s:text name="colAcciones"/></th>
						</tr>
					</thead>
				</table>
			</div>
			<br/>
			<div class="seccion">
				<p class="instrucciones">A continuación se muestran los productos asociados al Carrito.</p>
				<table id="tablaProductoModel" class="tablaGestion" cellspacing="0" width="100%">
					<thead>
						<tr>
							<th style="width: 40%;"><s:text name="colNombre"/></th>
							<th style="width: 20%;"><s:text name="colCantidad"/></th>
							<th style="width: 20%;"><s:text name="colPrecio"/></th>
							<th style="width: 20%;"><s:text name="colAcciones"/></th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
		
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
		<s:hidden id="jsonProductosBDTabla" name="jsonProductosBDTabla"
			value="%{jsonProductosBDTabla}" />
		<s:hidden id="jsonProductosModelTabla" name="jsonProductosModelTabla"
			value="%{jsonProductosModelTabla}" />
	</s:form>
	
	<!-- EMERGENTE REGISTRAR ATRIBUTO -->
	<sj:dialog id="produtoDialog" title="Consultar Producto" autoOpen="false"
		minHeight="300" minWidth="800" modal="true" draggable="true">
		<s:form autocomplete="off" id="frmAtributo" name="frmAtributoName" theme="simple">
			<s:hidden id="filaAtributo" />
			<s:hidden id="idAtributo" />
				<div class="tituloFormulario">Información del Producto</div>
				<table class="seccion">
					<tr>
						<td class="label"><s:text name="labelNombre"/></td>
						<td class="inputFormulario ui-widget"><span id="producto.nombre"/></td>
					</tr>
					<tr>
						<td class="label"><s:text name="labelPrecio"/></td>
						<td class="inputFormulario ui-widget"><span id="producto.precio"/></td>
					</tr>
				</table>
				<div class="seccion">
					<table id="tablaCategorias">
						<tbody>
						
						</tbody>
					</table>
				</div>
				<div id="seccionAtributos"><!--  --></div>
			<br />
			<div align="center">
				<input type="button" onclick="cerrarEmergente()" value="Aceptar" /> 
			</div>
		</s:form>
	</sj:dialog>
</body>
	</html>
</jsp:root>