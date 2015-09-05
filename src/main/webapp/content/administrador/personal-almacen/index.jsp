<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
	xmlns:s="/struts-tags" xmlns:sj="/struts-jquery-tags">
	<jsp:directive.page language="java"
		contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" />
	<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Atributos</title>
<![CDATA[
	<script type="text/javascript" charset="utf8" src="${pageContext.request.contextPath}/content/administrador/personal-almacen/js/index.js"></script>
]]>
</head>

<body>
	<h1>Gestionar Almacenistas</h1>
	<s:actionmessage theme="jquery"/>
	<s:actionerror theme="jquery"/>
	
	<br/>
	<s:form autocomplete="off" theme="simple" onsubmit="return false;">
	<div class="form">
		<table id="gestion" class="tablaGestion" cellspacing="0" width="100%">
			<thead>
				<th style="width: 40%;"><s:text name="colCURP"/></th>
				<th style="width: 40%;"><s:text name="colNombre"/></th>
				<th style="width: 20%;"><s:text name="colAcciones"/></th>
			</thead>
			<tbody>
			<s:iterator value="personalAlmacen" var="persona">
				<tr>
					<td><s:property value="%{#persona.curp}"/></td>		
					<td><s:property value="%{#persona.nombre + ' ' + #persona.primerApellido + ' ' + #persona.segundoApellido}"/></td>
					<td align="center">
						<s:url var="urlEditar" value="%{#pageContext.request.contextPath}/personal-almacen/%{#persona.curp}/edit"/>			
						<s:a href="%{urlEditar}">
							<img id="" class="button" title="Modificar"
									src="${pageContext.request.contextPath}/resources/images/icons/editar.png" />
						</s:a>
						${blanks}
						<s:a onclick="return mostrarMensajeEliminacion('%{#persona.curp}');">
						<img id="" class="button" title="Eliminar"
								src="${pageContext.request.contextPath}/resources/images/icons/eliminar.png" /></s:a>		
					</td>
				</tr>
			</s:iterator>
			</tbody>
		</table>
		
	</div>
	<br />
	<br />
	<div align="center">
		<button class="boton" formmethod="post"
			onclick="location.href='${pageContext.request.contextPath}/personal-almacen/new'">
			<s:text name="Registrar"></s:text>
		</button>
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

