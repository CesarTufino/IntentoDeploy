<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Ejemplares Alquilados</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
</head>
<body>
<div class="container mt-5">
    <h1>Ejemplares Alquilados</h1>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">Película</th>
            <th scope="col">Fecha de Devolución</th>
            <th scope="col">Cliente</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${listaAlquileres}" var="alquiler">
            <c:forEach items="${alquiler.ejemplares}" var="ejemplar">
                <c:if test="${not ejemplar.estadoDisponibilidad}">
                    <tr>
                        <td>${ejemplar.pelicula.titulo}</td>
                        <td>${alquiler.strFechaDevolucion}</td>
                        <td>${alquiler.cliente.nombre} ${alquiler.cliente.apellido}</td>
                    </tr>
                </c:if>
            </c:forEach>

        </c:forEach>
        </tbody>
    </table>
    <a href="MenuController?ruta=inicioGerente" class="btn btn-secondary">Volver al menú</a>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm"
        crossorigin="anonymous"></script>
</body>
</html>
