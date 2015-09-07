<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
	xmlns:s="/struts-tags" xmlns:sj="/struts-jquery-tags">
	<jsp:directive.page language="java"
		contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" />
	<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Productos</title>
<![CDATA[
	<script type="text/javascript" charset="utf8" src="${pageContext.request.contextPath}/content/cliente/productos-compra/js/index.js"></script>
]]>
</head>

<body>
	<div style="text-align: right;">
		<s:url var="urlCarrito" value="%{#pageContext.request.contextPath}/carritos/%{idCarritoActual}/edit"/>
		<s:a href="%{urlCarrito}">
			<img id="" class="button" title="Ver Carrito"
					src="${pageContext.request.contextPath}/resources/images/icons/carrito.png" />
		</s:a>
	</div>
	<h1>Catálogo de Productos</h1>
	<s:actionmessage theme="jquery"/>
	<s:actionerror theme="jquery"/>
	
	<br/>
	<s:form autocomplete="off" theme="simple" onsubmit="return false;">
	<div class="formuario">
		<div class="seccion">
			<p class="instrucciones">Seleccione las Categorías para filtar los productos.</p>
			<div class="seccion">
			<table id="tablaCategorias">
				<tbody>
				
				</tbody>
			</table>
			</div>
		</div>
	</div>
	<br/>
	<div class="formuario">
	<div class="seccion">
		<table id="gestion" class="tablaGestion" cellspacing="0" width="100%">
			<thead>
				<th style="width: 40%;"><s:text name="colNombre"/></th>
				<th style="width: 20%;"><s:text name="colPrecio"/></th>
				<th style="width: 20%;"><s:text name="colCantidad"/> en bodega</th>
				<th style="width: 0%;"><!-- categorias --></th>
				<th style="width: 20%;"><s:text name="colAcciones"/></th>
			</thead>
			<tbody>
			<s:iterator value="productos" var="producto">
				<tr>
					<td><s:property value="%{#producto.nombre}"/></td>
					<td>$ <s:property value="%{#producto.precio}"/></td>
					<td><s:property value="%{#producto.cantidad}"/></td>
					<td>
						<s:iterator value="%{#producto.productosCategoria}" var="proCat">
							${proCat.id.categoria.nombre}
						</s:iterator>
					</td>
					<td align="center">
						<s:url var="urlEditar" value="%{#pageContext.request.contextPath}/productos-compra/%{#producto.id}"/>			
						<s:a href="%{urlEditar}">
							<img id="" class="button" title="Ver detalle"
									src="${pageContext.request.contextPath}/resources/images/icons/ver.png" />
						</s:a>
						${blanks}
						<s:a onclick="return mostrarMensajeAgregarCarrito(%{#producto.id});">
							<img id="" class="button" title="Agregar al Carrito"
									src="${pageContext.request.contextPath}/resources/images/icons/masCarrito.png" />
						</s:a>		
					</td>
				</tr>
			</s:iterator>
			</tbody>
		</table>
	</div>	
	
	</div>
	<br />
	<s:hidden id="jsonCategoriasBD" name="jsonCategoriasBD"
			value="%{jsonCategoriasBD}" />
	</s:form>
	<!-- EMERGENTE CONFIRMAR AGREGAR AL CARRITO -->
	<sj:dialog id="confirmarAgregarDialog" title="Confirmación" autoOpen="false"
		minHeight="100" minWidth="400" modal="true" draggable="true">
		<s:form autocomplete="off" id="frmConfirmarEliminacion" name="frmConfirmarEliminacionName" theme="simple">
				<div class="seccion">
				<s:text name="MSG15"></s:text>
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
</body>
</html>
</jsp:root>

