<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
    <head>
      <meta charset="UTF-8">
      <title>Registro | DevConnect</title>
      <link rel="stylesheet" type="text/css" href="../css/register.css">
    </head>
    <body>

        <div class="register-container">
          <div class="register-image">
            <!-- Aquí puedes cambiar la imagen por la que prefieras -->
            <img src="${pageContext.request.contextPath}/img/empleo.jpg" alt="Imagen de registro">
          </div>

          <div class="register-form">
            <h2>Regístrate</h2>
            <% if (request.getParameter("error") != null) { %>
            <p class="error-message">Error en el registro. Inténtalo de nuevo.</p>
            <% } %>

            <form action="<%= request.getContextPath() %>/register" method="post">
              <div class="input-group">
                <label>Nombre:</label>
                <input type="text" name="nombre" required>
              </div>
              <div class="input-group">
                <label>Apellido:</label>
                <input type="text" name="apellido" required>
              </div>
              <div class="input-group">
                <label>Correo:</label>
                <input type="email" name="correo" required>
              </div>
              <div class="input-group">
                <label>Usuario:</label>
                <input type="text" name="username" required>
              </div>
              <div class="input-group">
                <label>Contraseña:</label>
                <input type="password" name="password" required>
              </div>
              <button type="submit" class="btn-register">Registrar</button>
            </form>
            <p>¿Ya tienes una cuenta? <a href="login.jsp">Inicia sesión</a></p>
          </div>
        </div>
    </body>
</html>




