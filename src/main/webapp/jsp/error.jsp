<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Error</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
  <style>
    body {
      background-color: #f8f9fa;
    }
    .error-container {
      max-width: 400px;
      margin: auto;
      padding: 20px;
      border: 1px solid #d6d6d6;
      border-radius: 5px;
      background-color: #fff;
      box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
      text-align: center;
    }
    .error-title {
      font-size: 24px;
      margin-bottom: 10px;
    }
    .error-message {
      font-size: 16px;
      margin-bottom: 20px;
    }
  </style>
</head>
<body>
<div class="container mt-5">
  <div class="error-container alert alert-danger">
    <h1 class="error-title">Error</h1>
    <p class="error-message">${errorMensaje}</p>
    <a href="MenuController?ruta=inicio" class="btn btn-secondary">Volver</a>
  </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
</body>
</html>
