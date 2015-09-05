<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
	xmlns:s="/struts-tags" xmlns:sj="/struts-jquery-tags">
	<jsp:directive.page language="java"
		contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" />
	<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Categorías</title>
<![CDATA[
	<script type="text/javascript" charset="utf8" src="${pageContext.request.contextPath}/resources/scripts/constructores.js"></script>
	<script type="text/javascript" charset="utf8" src="${pageContext.request.contextPath}/resources/scripts/validaciones.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/jquery.caret.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/jquery.atwho.js"></script>
	<script type="text/javascript" charset="utf8" src="${pageContext.request.contextPath}/content/administrador/categorias/js/index-edit.js"></script>	
]]>

</head>
<body>

	<h1>Modificar Categoría</h1>

	<s:actionmessage theme="jquery" />
	<s:actionerror theme="jquery" />
	<br />

	<p class="instrucciones">Ingrese la información solicitada.</p>
	<s:form autocomplete="off" id="frmCategoria" theme="simple"
		action="%{#pageContext.request.contextPath}/categorias/%{idSel}" method="post" onsubmit="return prepararEnvio()">
		<s:hidden name="_method" value="put" />
		<div class="formulario">
			<div class="tituloFormulario">Información general de la Categoría</div>
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
			<div class="tituloFormulario">Atributos</div>
			<div class="seccion">
				<table id="tablaAtributoBD" class="tablaGestion" cellspacing="0" width="100%">
					<thead>
						<tr>
							<th style="width: 40%;"><s:text name="colNombre"/></th>
							<th style="width: 40%;"><s:text name="colTipoDato"/></th>
							<th style="width: 20%;"><s:text name="colAcciones"/></th>
						</tr>
					</thead>
				</table>
			</div>
			<br/>
			<div class="seccion">
				<table id="tablaAtributoModel" class="tablaGestion" cellspacing="0" width="100%">
					<thead>
						<tr>
							<th style="width: 40%;"><s:text name="colNombre"/></th>
							<th style="width: 40%;"><s:text name="colTipoDato"/></th>
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
				value="%{#pageContext.request.contextPath}/atributos">
			</s:url>
			<input class="boton" type="button"
				onclick="location.href='${urlGestion}'"
				value="Cancelar" />
		</div>
		<s:hidden id="jsonAtributosBDTabla" name="jsonAtributosBDTabla"
			value="%{jsonAtributosBDTabla}" />
		<s:hidden id="jsonAtributosModelTabla" name="jsonAtributosModelTabla"
			value="%{jsonAtributosModelTabla}" />
	</s:form>
</body>
	</html>
</jsp:root>