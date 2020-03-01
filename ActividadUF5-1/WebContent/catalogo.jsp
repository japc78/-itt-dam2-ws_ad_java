<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Document</title>
	<link rel="stylesheet" href="css/styles.css">
</head>
<body>
	<h1></h1>
	<table>
	<tr>
		<th>Codigo</th>
		<th>Descripcion</th>
		<th>Precio</th>
		<th>Stock</th>
	</tr>
	<!-- PAra utilizar estas etiquetas tienen que estar dadas de alta en la parte de arriba
	de la pagina y tener las librerias importadas en el proyecto (maven) -->
	<c:forEach items="${listarProductos}" var="producto">
		<tr>
			<td>${producto.codigo}</td>
			<td>${producto.descripcion}</td>
			<td>${producto.precio}</td>
			<td>${producto.stock}</td>
		</tr>
	</c:forEach>
	</table>
</body>
</html>