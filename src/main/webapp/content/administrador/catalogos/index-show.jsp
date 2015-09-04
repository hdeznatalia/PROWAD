<?xml version="1.0" encoding="UTF-8" ?>

<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
	xmlns:s="/struts-tags" xmlns:sj="/struts-jquery-tags">
	<jsp:directive.page language="java"
		contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" />
	<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Actor</title>
</head>
<body>
	<h1>Consultar Término del Glosario</h1>
	<h3>
		<s:property value="model.nombre" />
	</h3>

	<s:actionmessage theme="jquery" />
	<s:actionerror theme="jquery" />
	<br />

	<div class="formulario">
		<div class="tituloFormulario">${blanks}</div>
		<div class="seccion">
			<h4>
				<s:property value="model.nombre" />
			</h4>
			<p class="instrucciones">
				<s:property value="model.descripcion" />
			</p>
		</div>
	</div>

	<br />
	<div align="center">
		<input class="boton" type="button"
			onclick="location.href='${urlPrev}'" value="Aceptar" />
	</div>
</body>
	</html>
</jsp:root>