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
	<div class="container">
		<header class="top">
			<img alt="Ferretería" src="images/ferreteria.jpg" />
		</header>
		<h1>Listado de productos</h1>
		<table>
		<thead>
			<tr>
				<th>Codigo</th>
				<th>Descripcion</th>
				<th>Precio</th>
				<th>Stock</th>
			</tr>
		</thead>
		<tbody>
		<!-- PAra utilizar estas etiquetas tienen que estar dadas de alta en la parte de arriba
			de la pagina y tener las librerias importadas en el proyecto (maven) -->
			<c:forEach items="${listarProductos}" var="producto">
				<tr>
					<td>${producto.codigo}</td>
					<td class="descripcion">${producto.descripcion}</td>
					<td>${producto.precio}</td>
					<td>${producto.stock}</td>
				</tr>
			</c:forEach>
		</tbody>
		</table>
		<a class="boton" href="index.html">Volver</a>
	</div>
</body>
</html>