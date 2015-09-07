<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
	xmlns:s="/struts-tags" xmlns:sj="/struts-jquery-tags">
	<jsp:directive.page language="java"
		contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" />
	<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Carritos</title>
<![CDATA[
	<script type="text/javascript" charset="utf8" src="${pageContext.request.contextPath}/content/cliente/carritos/js/index.js"></script>
]]>
</head>

<body>
	<h1>Historial de Compras</h1>
	<s:actionmessage theme="jquery"/>
	<s:actionerror theme="jquery"/>
	
	<br/>
	<s:form autocomplete="off" theme="simple" onsubmit="return false;">
	<div class="form">
		<table id="gestion" class="tablaGestion" cellspacing="0" width="100%">
			<thead>
				<th style="width: 0%;"><s:text name="colFechaCompra"/></th>
				<th style="width: 40%;"><s:text name="colFechaCompra"/></th>
				<th style="width: 20%;"><s:text name="colAcciones"/></th>
			</thead>
			<tbody>
			<s:iterator value="carritos" var="carrito">
				<s:if test="#carrito.estado.nombre == 'Pagado'">
				<tr>
					<td><s:property value="%{#carrito.fechaCompra.time}"/></td>
					<td><s:property value="%{#carrito.fechaCompra}"/></td>
					<td align="center">
						<s:url var="urlConsultar" value="%{#pageContext.request.contextPath}/carritos/%{#carrito.id}"/>			
						<s:a href="%{urlConsultar}">
							<img id="" class="button" title="Consultar"
									src="${pageContext.request.contextPath}/resources/images/icons/ver.png" />
						</s:a>		
					</td>
				</tr>
				</s:if> 
			</s:iterator>
			</tbody>
		</table>
		
	</div>
	
	</s:form>
	<!-- EMERGENTE CONFIRMAR ELIMINACIÓN -->
	<sj:dialog id="confirmarEliminacionDialog" title="Confirmación" autoOpen="false"
		minHeight="100" minWidth="400" modal="true" draggable="true">
		<s:form autocomplete="off" id="frmConfirmarEliminacion" name="frmConfirmarEliminacionName" theme="simple">
				<div class="seccion">
				<s:text name="MSG11"></s:text>
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

