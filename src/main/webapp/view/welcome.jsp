<%--
  Created by IntelliJ IDEA.
  User: Maria
  Date: 21/03/2025
  Time: 6:13 p. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>DevConnect | Bienvenido</title>
    <link rel="stylesheet" type="text/css" href="../css/welcome.css">
  </head>
  <body>

    <header>
      <nav class="navbar">
        <div class="logo">DevConnect</div>
        <ul class="nav-links">
          <li>¡Hola, ${sessionScope.user}!</li> <!-- Cambiado a EL expression -->
          <li><a href="${pageContext.request.contextPath}/listaVacantes">Tus vacantes</a></li>
          <li><a href="home.jsp">Salir</a></li>
        </ul>
      </nav>
    </header>
    <main class="container">
      <h1>Nueva Vacante</h1>
      <p>Llena el formulario y publica tus vacantes</p>

      <form action="/devConnect3_war/publicarVacante" method="post">
        <!-- Sección 1: Información General -->
        <section class="form-section">
          <h2>Información General</h2>
          <div class="form-group">
            <label for="titulo">Título:</label>
            <input type="text" id="titulo" name="titulo" required>
          </div>
          <div class="form-group">
            <label for="empresa">Empresa:</label>
            <input type="text" id="empresa" name="empresa" required>
          </div>
          <div class="form-group">
            <label for="ubicacion">Ubicación:</label>
            <input type="text" id="ubicacion" name="ubicacion" required>
          </div>
          <div class="form-group">
            <label for="salario">Salario:</label>
            <input type="text" id="salario" name="salario" required>
          </div>
          <div class="form-group">
            <label for="contrato">Tipo de Contrato:</label>
            <select id="contrato" name="contrato" required> <!-- Añadido required -->
              <option value="">Seleccione un tipo</option>
              <option value="Tiempo Completo">Tiempo Completo</option>
              <option value="Medio Tiempo">Medio Tiempo</option>
              <option value="Freelance">Freelance</option>
              <option value="Practicas">Prácticas</option>
            </select>
          </div>
        </section>

        <!-- Sección 2: Descripción del Puesto -->
        <section class="form-section">
          <h2>Descripción del Puesto</h2>
          <div class="form-group">
            <label for="descripcion">Descripción:</label>
            <textarea id="descripcion" name="descripcion" rows="5" required></textarea>
          </div>
        </section>

        <!-- Botón de envío -->
        <button type="submit" class="submit-button">Publicar Vacante</button>
      </form>
    </main>
  </body>
</html>
