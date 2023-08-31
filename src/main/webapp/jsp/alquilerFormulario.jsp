<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Alquiler</title>
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ"
            crossorigin="anonymous">
</head>
<body>
<div class="container">
    <h1>Alquiler</h1>
    <form action="AlquilerController?ruta=registrar" method="post"
          class="form-group needs-validation"
          onsubmit="return validarCedula(); validarCodigo();"
          name="buscarDatosParaAlquiler" novalidate>
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
                id="nombreCliente" value="${cliente.nombre}" disabled="disabled">
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
            <div class="mb-3">
                <label for="tipoAlquiler" class="form-label">Tipo de
                    Alquiler</label>
                <select class="form-select" name="tipoAlquiler" id="tipoAlquiler">
                    <option value="normal">Alquiler Normal</option>
                    <option value="fidelidad">Alquiler por Fidelidad</option>
                </select>
            </div>

            <div class="mb-3">
                <label for="codigoEjemplar0" class="form-label"
                       style="display: none;">Código del Ejemplar</label>
                <input
                        type="text" class="form-control" name="codigoEjemplar0"
                        id="codigoEjemplar0" placeholder="Código" required>
                <div class="valid-feedback">Completo</div>
                <div class="invalid-feedback">Campo Vacio o Erroneo, ingresa
                    nuevamente
                </div>
            </div>
            <div id="camposContainer"></div>
            <button type="button" class="btn btn-warning" id="agregarCampos">Agregar Ejemplar</button>
            <div class="mb-3">
                <label for="numeroDias" class="form-label">
                    Número de días de alquiler
                </label>
                <input type="number" class="form-control" name="numeroDias" id="numeroDias"
                       placeholder="Días" required min="1" max="14">
                <div class="valid-feedback">Completo</div>
                <div class="invalid-feedback">Campo Vacio o Erroneo, ingresa
                    nuevamente
                </div>
            </div>
        </div>

        <button type="submit" class="btn btn-primary">Alquilar</button>
        <a href="MenuController?ruta=inicioCajero" type="button" class="btn btn-secondary">
            Volver
        </a>
    </form>
</div>
<script>

    // Define cedulaInput en un alcance más amplio
    const cedulaInput = document.querySelector('#cedulaCliente');

    // Obtener lista de clientes
    var clientes = [
        <c:forEach items="${clientes}" var="cliente" varStatus="status">
        {
            cedula: '<c:out value="${cliente.cedula}" />',
            nombre: '<c:out value="${cliente.nombre}" />',
            apellido: '<c:out value="${cliente.apellido}" />'
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

    function validarCodigo(input) {
        const codigoRegex = /^\d{4}$/;
        if (!codigoRegex.test(input.value)) {
            input.setCustomValidity("Ingrese un código válido (por ejemplo, E001)");
        } else {
            input.setCustomValidity("");
        }
    }

    function mostrarSeccionCombo() {
        const seccionCombo = document.getElementById('seccionCombo');
        const nombre = document.querySelector('#nombreCliente');
        const apellido = document.querySelector('#apellidoCliente');

        for (const cliente of clientes) {
            if (cedulaInput.value.match(cliente.cedula)) {
                nombre.setAttribute("value", cliente.nombre);
                apellido.setAttribute("value", cliente.apellido);
                seccionCombo.style.display = 'block';
                const codigoEjemplarLabel = document.querySelector('label[for="codigoEjemplar"]');
                const codigoEjemplarInput = document.getElementById("codigoEjemplar");
                const numeroDiasLabel = document.querySelector('label[for="numeroDias"]');
                const numeroDiasInput = document.getElementById("numeroDias");
                codigoEjemplarLabel.style.display = 'block';
                codigoEjemplarInput.style.display = 'block';
                numeroDiasLabel.style.display = 'block';
                numeroDiasInput.style.display = 'block';
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
    document.addEventListener('DOMContentLoaded', function () {
        const agregarCamposButton = document.getElementById('agregarCampos');
        const camposContainer = document.getElementById('camposContainer');

        let contadorCampos = 1;

        agregarCamposButton.addEventListener('click', () => {
            const nuevoCampoDiv = document.createElement('div');
            nuevoCampoDiv.className = 'mb-3';

            const codigoEjemplarLabel = document.createElement('label');
            codigoEjemplarLabel.className = 'form-label';
            codigoEjemplarLabel.textContent = `Código del Ejemplar`;
            nuevoCampoDiv.appendChild(codigoEjemplarLabel);

            const codigoEjemplarInput = document.createElement('input');
            codigoEjemplarInput.type = 'text';
            codigoEjemplarInput.className = 'form-control';
            codigoEjemplarInput.id = `codigoEjemplar`+contadorCampos;
            codigoEjemplarInput.name = `codigoEjemplar`+contadorCampos;
            codigoEjemplarInput.placeholder = 'Código';
            codigoEjemplarInput.required = true;
            nuevoCampoDiv.appendChild(codigoEjemplarInput);

            camposContainer.appendChild(nuevoCampoDiv);

            contadorCampos++;
        });
    });

    function verificarEjemplaresPendientes() {
        const cedula = cedulaInput.value; // Obtén la cédula del cliente ingresada
        const clientePendiente = clientesConEjemplaresPendientes.find(cliente => cliente.cedula === cedula);

        if (clientePendiente) {
            const mensajeError = document.getElementById('mensajeError');
            mensajeError.textContent = 'Este cliente tiene ejemplares pendientes de devolución. No puede realizar un nuevo alquiler.';
            mensajeError.style.display = 'block';
            return false;
        }

        return true;
    }

    // Usa el evento submit del formulario para llamar a la función de verificación
    document.querySelector('.needs-validation').addEventListener('submit', function(event) {
        // Llama a las funciones de validación existentes
        validarCedula(cedulaInput);
        validarCodigo(codigoEjemplarInput);

        // Verifica si el cliente tiene ejemplares pendientes antes de enviar el formulario
        if (!verificarEjemplaresPendientes()) {
            event.preventDefault();
        }
        form.classList.add('was-validated');
    });

</script>

<script>
    function volverAIndex() {
        window.location.href = "menuCajero.jsp";
    }

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
