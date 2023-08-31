<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Registrarse</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
  <style>
    body {
      background-color: #f8f9fa;
    }
    .register-container {
      max-width: 400px;
      margin: auto;
      padding: 20px;
      border: 1px solid #d6d6d6;
      border-radius: 5px;
      background-color: #fff;
      box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
      text-align: center;
      margin-top: 100px;
    }
    .register-title {
      font-size: 24px;
      margin-bottom: 20px;
    }
    .form-group {
      margin-bottom: 20px;
    }
  </style>
</head>
<body>
<div class="container">
  <div class="register-container">
    <h1 class="register-title">Registrar</h1>
    <form action="ClienteController?ruta=registrar" method="post" onsubmit="return validarCedula();">
      <div class="form-group">
        <label for="cedula">Cédula:</label>
        <input type="text" id="cedula" name="cedula" class="form-control" required>
        <div class="invalid-feedback">Campo Vacio o Erroneo, ingresa
          nuevamente
        </div>
      </div>
      <div class="form-group">
        <label for="nombre">Nombre:</label>
        <input type="text" id="nombre" name="nombre" class="form-control" required>
      </div>
      <div class="form-group">
        <label for="apellido">Apellido:</label>
        <input type="text" id="apellido" name="apellido" class="form-control" required>
      </div>
      <button type="submit" class="btn btn-primary">Registrar Cliente</button>
      <a href="MenuController?ruta=inicioCajero" class="btn btn-secondary">Volver al menú</a>
    </form>
  </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
<script>

  // Define cedulaInput en un alcance más amplio
  const cedulaInput = document.querySelector('#cedula');

  function validarCedula(input) {
    const cedulaRegex = /^\d{10}$/;
    if (!cedulaRegex.test(input.value)) {
      input.setCustomValidity("Ingrese una cédula válida (10 dígitos)");
    } else {
      input.setCustomValidity("");
    }
  }
  const nombreInput = document.querySelector('#nombre');
  const apellidoInput = document.querySelector('#apellido');

  function validarTextoConTildes(input) {
    const textoRegex = /^[A-Za-zÁÉÍÓÚáéíóúñÑ\s]+$/;
    if (!textoRegex.test(input.value)) {
      input.classList.add('is-invalid');
      input.setCustomValidity("invalid");
    } else {
      input.classList.remove('is-invalid');
      input.setCustomValidity("");
    }
  }

  nombreInput.addEventListener('input', function () {
    validarTextoConTildes(nombreInput);
  });

  apellidoInput.addEventListener('input', function () {
    validarTextoConTildes(apellidoInput);
  });


  // Usa el evento input de la cédula para llamar a la función mostrarSeccionCombo()
  cedulaInput.addEventListener('input', function () {
    validarCedula(cedulaInput);
  });

</script>

</body>
</html>
