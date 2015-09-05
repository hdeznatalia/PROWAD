<?xml version="1.0" encoding="UTF-8" ?>

<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
	xmlns:s="/struts-tags" xmlns:sj="/struts-jquery-tags">
	<jsp:directive.page language="java"
		contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" />
	<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Almacenistas</title>
<![CDATA[
	<script type="text/javascript" charset="utf8" src="${pageContext.request.contextPath}/resources/scripts/constructores.js"></script>
	<script type="text/javascript" charset="utf8" src="${pageContext.request.contextPath}/resources/scripts/validaciones.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/jquery.caret.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/jquery.atwho.js"></script>
	<script type="text/javascript" charset="utf8" src="${pageContext.request.contextPath}/content/administrador/personal-almacen/js/index-editNew.js"></script>	
]]>

</head>
<body>

	<h1>Registrar Almacenista</h1>

	<s:actionmessage theme="jquery" />
	<s:actionerror theme="jquery" />
	<br />

	<p class="instrucciones">Ingrese la información solicitada.</p>
	<s:form autocomplete="off" id="frmAlmacenista" theme="simple"
		action="%{#pageContext.request.contextPath}/personal-almacen" method="post">
		<div class="formulario">
			<div class="tituloFormulario">Información general del Almacenista</div>
			<table class="seccion">
				<tr>
						<td class="label obligatorio"><s:text name="labelCURP" /></td>
						<td><s:textfield name="model.curp" maxlength="18"
								cssErrorClass="input-error" cssClass="inputFormulario ui-widget" />
							<s:fielderror fieldName ="model.curp" cssClass="error"
								theme="jquery" /></td>
					</tr>
					<tr>
						<td class="label obligatorio"><s:text name="labelNombre" /></td>
						<td><s:textfield name="model.nombre" maxlength="255"
								cssErrorClass="input-error" cssClass="inputFormulario ui-widget" />
							<s:fielderror fieldName ="model.nombre" cssClass="error"
								theme="jquery" /></td>
					</tr>
					
					<tr>
						<td class="label obligatorio"><s:text name="labelPrimerApellido" /></td>
						<td><s:textfield name="model.primerApellido" maxlength="255"
								cssErrorClass="input-error" cssClass="inputFormulario ui-widget" />
							<s:fielderror fieldName ="model.primerApellido" cssClass="error"
								theme="jquery" /></td>
					</tr>
					
					<tr>
						<td class="label"><s:text name="labelSegundoApellido" /></td>
						<td><s:textfield name="model.segundoApellido" maxlength="255"
								cssErrorClass="input-error" cssClass="inputFormulario ui-widget" />
							<s:fielderror fieldName ="model.segundoApellido" cssClass="error"
								theme="jquery" /></td>
					</tr>
					
					<tr>
						<td class="label obligatorio"><s:text name="labelCorreo" /></td>
						<td><s:textfield name="model.correoElectronico" maxlength="255"
								cssErrorClass="input-error" cssClass="inputFormulario ui-widget" />
							<s:fielderror fieldName ="model.correoElectronico" cssClass="error"
								theme="jquery" /></td>
					</tr>
					
					<tr>
						<td class="label obligatorio"><s:text name="labelContrasenia" /></td>
						<td><s:textfield name="model.contrasena" maxlength="20"
								cssErrorClass="input-error" cssClass="inputFormulario ui-widget" />
							<s:fielderror fieldName ="model.contrasena" cssClass="error"
								theme="jquery" /></td>
					</tr>
			</table>
		</div>
		
		<br />
		<div align="center">
			<s:submit class="boton" value="Aceptar" />

			<s:url var="urlGestion"
				value="%{#pageContext.request.contextPath}/personal-almacen">
			</s:url>
			<input class="boton" type="button"
				onclick="location.href='${urlGestion}'"
				value="Cancelar" />
		</div>
	</s:form>
</body>
	</html>
</jsp:root>