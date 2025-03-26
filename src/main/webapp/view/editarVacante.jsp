<%--
  Created by IntelliJ IDEA.
  User: Maria
  Date: 26/03/2025
  Time: 10:02 a. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>DevConnect | Editar Vacante</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/welcome.css"> <!-- Mismo CSS -->
  </head>

  <body>

    <header>
      <nav class="navbar">
        <div class="logo">DevConnect</div>
        <ul class="nav-links">
          <li>¡Hola, ${sessionScope.user}!</li>
          <li><a href="${pageContext.request.contextPath}/listaVacantes">Tus vacantes</a></li>
          <li><a href="home.jsp">Salir</a></li>

        </ul>
      </nav>
    </header>

    <main class="container">
      <h1>Editar Vacante</h1>
      <p>Modifica los campos necesarios</p>

      <form action="${pageContext.request.contextPath}/actualizarVacante" method="post">
        <input type="hidden" name="id" value="${vacante.id}">

        <!-- Sección 1: Información General -->
        <section class="form-section">
          <h2>Información General</h2>
          <div class="form-group">
            <label for="titulo">Título:</label>
            <input type="text" id="titulo" name="titulo" value="${vacante.titulo}" required>
          </div>
          <div class="form-group">
            <label for="empresa">Empresa:</label>
            <input type="text" id="empresa" name="empresa" value="${vacante.empresa}" required>
          </div>
          <div class="form-group">
            <label for="ubicacion">Ubicación:</label>
            <input type="text" id="ubicacion" name="ubicacion" value="${vacante.ubicacion}" required>
          </div>
          <div class="form-group">
            <label for="salario">Salario:</label>
            <input type="text" id="salario" name="salario" value="${vacante.salario}" required>
          </div>
          <div class="form-group">
            <label for="contrato">Tipo de Contrato:</label>
            <select id="contrato" name="contrato" required>
              <option value="Tiempo Completo" ${vacante.contrato == 'Tiempo Completo' ? 'selected' : ''}>Tiempo Completo</option>
              <option value="Medio Tiempo" ${vacante.contrato == 'Medio Tiempo' ? 'selected' : ''}>Medio Tiempo</option>
              <option value="Freelance" ${vacante.contrato == 'Freelance' ? 'selected' : ''}>Freelance</option>
              <option value="Practicas" ${vacante.contrato == 'Practicas' ? 'selected' : ''}>Prácticas</option>
            </select>
          </div>
        </section>

        <!-- Sección 2: Descripción del Puesto -->
        <section class="form-section">
          <h2>Descripción del Puesto</h2>
          <div class="form-group">
            <label for="descripcion">Descripción:</label>
            <textarea id="descripcion" name="descripcion" rows="5" required>${vacante.descripcion}</textarea>
          </div>
        </section>

        <!-- Botón de envío -->
        <button type="submit" class="submit-button">Guardar Cambios</button>
      </form>
    </main>
  </body>
</html>