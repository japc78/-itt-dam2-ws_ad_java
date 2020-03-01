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

		<ul class="menu">
			<li><a class="boton" href="index.html">Inicio</a></li>
			<li><a class="boton" href="alta.jsp">Alta articulo</a></li>
			<li><a class="boton" href="ServletProducts">Ver Listado</a></li>
		</ul>

		<div class="msg ${msgType}">
			<span>${msg}</span>
		</div>
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
			<%-- Utilizo una condicion para añadir una clase al tr en el caso que de añadir un producto, este aparece con color verde de fondo en el listado. --%>
			<c:forEach items="${listarProductos}" var="producto">
				<tr <c:if test="${producto.codigo == pAdd}">class="pOk"</c:if>>
					<td>${producto.codigo}</td>
					<td class="descripcion">${producto.descripcion}</td>
					<td>${producto.precio} €</td>
					<td>${producto.stock}</td>
				</tr>
			</c:forEach>
		</tbody>
		</table>
		<a class="boton" href="index.html">Volver inicio</a>
	</div>
</body>
</html>