<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Sistema de Multipelis</title>
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ"
            crossorigin="anonymous">
    <style>
        ul {
            list-style: none;
            padding-left: 0;
            margin-left: 0;
        }

        ul li {
            margin-top: 20px; /* Agrega un espacio entre los elementos de la lista */
        }

        ul *{
            display: grid;
            justify-content: center;
        }

        body {
            display: grid;
            place-content: center;
            margin: 50px 0 0;
        }
    </style>
</head>
<body>
<header>
    <h1>Sistema de Multipelis</h1>
</header>
<nav>
    <ul>
        <li>
            <a href="MenuController?ruta=alquilar" type="button"
               class="btn btn-primary">Alquiler</a>
        </li>
        <li><a href="MenuController?ruta=devolver" type="button"
               class="btn btn-primary">Devolución</a>
        </li>
    </ul>
</nav>

<script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
        crossorigin="anonymous"></script>
<script
        src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.7/dist/umd/popper.min.js"
        integrity="sha384-zYPOMqeu1DAVkHiLqWBUTcbYfZ8osu1Nd6Z89ify25QV9guujx43ITvfi12/QExE"
        crossorigin="anonymous"></script>
<script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.min.js"
        integrity="sha384-Y4oOpwW3duJdCWv5ly8SCFYWqFDsfob/3GkgExXKV4idmbt98QcxXYs9UoXAB7BZ"
        crossorigin="anonymous"></script>
</body>
</html>