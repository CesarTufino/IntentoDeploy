<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Devolución</title>
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ"
            crossorigin="anonymous">

</head>
<body>
<div class="container">
    <h1>Devolución</h1>
    <form action="DevolverController?ruta=registrar" method="post"
          onsubmit="return validarCedula();"
          class="form-group needs-validation" novalidate>
        <div class="mb-3">
            <label for="cedulaCliente" class="form-label">CI del Cliente</label>
            <input type="text" class="form-control" name="cedulaCliente"
                   id="cedulaCliente" placeholder="10 digitos" required>
            <div class="valid-feedback">Completo</div>
            <div class="invalid-feedback">Campo Vacio o Erroneo, ingresa
                nuevamente
            </div>
        </div>
        <div class="mb-3">
            <label for="nombreCliente" class="form-label">Nombre</label> <input
                type="text" class="form-control" name="nombreCliente"
                id="nombreCliente" disabled="disabled">
            <div class="valid-feedback">Completo</div>
            <div class="invalid-feedback">Campo Vacio o Erroneo, ingresa
                nuevamente
            </div>
        </div>
        <div class="mb-3">
            <label for="apellidoCliente" class="form-label">Apellido</label> <input
                type="text" class="form-control" name="apellidoCliente"
                id="apellidoCliente" disabled="disabled">
            <div class="valid-feedback">Completo</div>
            <div class="invalid-feedback">Campo Vacio o Erroneo, ingresa
                nuevamente
            </div>
        </div>
        <div id="seccionCombo" style="display: none;">
            <table id="tablaPeliculas" class="display" style="width: 100%">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Nombre</th>
                    <th>Devuelta</th>
                </tr>
                </thead>
                <tbody id="tablaPeliculasBody">

                </tbody>
            </table>
            <div class="mb-3">
                <label class="form-label">¿Algún ejemplar tiene percance?</label>
                <input type="checkbox" id="percance" name="percance">
            </div>
        </div>
        <button type="submit" class="btn btn-primary">Devolver</button>
        <a href="MenuController?ruta=inicio" type="button" class="btn btn-secondary">
            Volver
        </a>
    </form>
</div>
<script>
    // Define cedulaInput en un alcance más amplio
    const cedulaInput = document.querySelector('#cedulaCliente');

    // Obtener lista de clientes
    var alquileres = [
        <c:forEach items="${alquileres}" var="alquiler" varStatus="status">
        {
            cedula: '<c:out value="${alquiler.cliente.cedula}" />',
            ejemplares: [
                <c:forEach items="${alquiler.ejemplares}" var="ejemplar" varStatus="ejemplarStatus">
                {
                    codigo: "${ejemplar.codigoEjemplar}",
                    estado: "${ejemplar.estadoDisponibilidad}",
                    pelicula: "${ejemplar.pelicula.titulo}"
                }<c:if test="${not ejemplarStatus.last}">, </c:if>
                </c:forEach>
            ]
        }<c:if test="${not status.last}">, </c:if>
        </c:forEach>
    ];
    var clientes = [
        <c:forEach items="${clientes}" var="cliente" varStatus="status">
        {
            cedula: '<c:out value="${cliente.cedula}" />',
            nombre: '<c:out value="${cliente.nombre}" />',
            apellido: '<c:out value="${cliente.apellido}" />',
        }<c:if test="${not status.last}">, </c:if>
        </c:forEach>
    ];


    function validarCedula(input) {
        const cedulaRegex = /^\d{10}$/;
        if (!cedulaRegex.test(input.value)) {
            input.setCustomValidity("Ingrese una cédula válida (10 dígitos)");
        } else {
            input.setCustomValidity("");
        }
    }

    function mostrarSeccionCombo() {
        const seccionCombo = document.getElementById('seccionCombo');
        const tablaPeliculasBody = document.getElementById('tablaPeliculasBody');
        const nombre = document.querySelector('#nombreCliente');
        const apellido = document.querySelector('#apellidoCliente');

        tablaPeliculasBody.innerHTML = ''; // Limpiar contenido previo

        for (const cliente of clientes) {
            const alquilerCorrespondiente = alquileres.find(alquiler => alquiler.cedula === cliente.cedula);

            if (cedulaInput.value.match(cliente.cedula) && alquilerCorrespondiente) {
                nombre.setAttribute("value", cliente.nombre);
                apellido.setAttribute("value", cliente.apellido);
                seccionCombo.style.display = 'block';

                for (const ejemplar of alquilerCorrespondiente.ejemplares) {
                    const row = tablaPeliculasBody.insertRow();
                    const codigoCell = row.insertCell(0);
                    const peliculaCell = row.insertCell(1);
                    const devueltaCell = row.insertCell(2);

                    if (ejemplar.estado === "true"){
                        continue;
                    }
                    codigoCell.innerHTML = ejemplar.codigo;
                    peliculaCell.innerHTML = ejemplar.pelicula;
                    devueltaCell.innerHTML = `<input type="checkbox" id="`+ejemplar.codigo+`" name="`+ejemplar.codigo+`">`;
                }

                break;
            } else {
                nombre.setAttribute("value", "");
                apellido.setAttribute("value", "");
                seccionCombo.style.display = 'none';
            }
        }
    }

    // Usa el evento input de la cédula para llamar a la función mostrarSeccionCombo()
    cedulaInput.addEventListener('input', function () {
        validarCedula(cedulaInput);
        mostrarSeccionCombo();
    });
</script>
<script>
    // Example starter JavaScript for disabling form submissions if there are invalid fields
    (() => {
        'use strict'

        // Fetch all the forms we want to apply custom Bootstrap validation styles to
        const forms = document.querySelectorAll('.needs-validation')

        // Loop over them and prevent submission
        Array.from(forms).forEach(form => {
            form.addEventListener('submit', event => {
                if (!form.checkValidity()) {
                    event.preventDefault()
                    event.stopPropagation()
                }

                form.classList.add('was-validated')
            }, false)
        })
    })()
</script>

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
