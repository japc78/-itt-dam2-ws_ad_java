<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
		<h1>Ferretería</h1>
		<ul class="menu">
			<li><a class="boton" href="index.html">Inicio</a></li>
			<li><a class="boton" href="alta.jsp">Alta articulo</a></li>
			<li><a class="boton" href="ServletProducts">Ver Listado</a></li>
		</ul>
		<div class="msg ${msgType}">
			<span>${msg}</span>
		</div>
		<form class="formulario" action="ServletProducts" method="POST">
			<div>
				<label for="codigo">Código</label>
				<input class="box" type="text" name="codigo" placeholder="Del tipo XXX1" value="${producto.codigo}"/>
			</div>

			<%-- He utilizado ternario en JSP para en el caso que haya algun error a la hora de introducir un producto, no se borren los datos ya introducidos y se realize la correcion más facilmente sin necesidad de volver a introducir todos los datos. En el caso que sea un alta desde 0, el valor será 0 a mostrar en dicho input. --%>

			<div>
				<label for="precio">Precio</label>
				<input class="box" type="number" step="0.01" name="precio" value="${not empty producto.precio ? producto.precio : 0}"/>
			</div>

			<div>
				<label for="stock">Stock</label>
				<input class="box" type="number" step="0.01" name="stock" value="${not empty producto.stock ? producto.stock : 0}"/>
			</div>

			<div>
				<label for="minimo">Stock Minimo</label>
				<input class="box" type="number" step="0.01" name="minimo" value="${not empty producto.minimo ? producto.minimo : 0}"/>
			</div>

			<div class="colspan">
				<label for="descripcion">Descripcion</label>
				<input class="box" type="text" name="descripcion" value="${producto.descripcion}"/>
			</div>

			<div class="colspan">
				<input class="boton" type="submit" value="Add Producto"/>
			</div>
		</form>
	</div>
</body>
</html>