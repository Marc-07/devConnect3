<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Login - DevConnect</title>
    <link rel="stylesheet" type="text/css" href="../css/login.css">
    <script>
        // Espera 5 segundos y oculta el mensaje
        setTimeout(function() {
            let mensaje = document.getElementById("mensaje-error");
            if (mensaje) {
                mensaje.style.display = "none";
            }
        }, 5000); // 5000 milisegundos = 5 segundos
    </script>
</head>
<body>
<div class="login-container">
    <h2>Iniciar Sesión</h2>

    <!-- Mensaje de error si los datos son incorrectos -->
    <% if (request.getParameter("error") != null) { %>
    <p id="mensaje-error" style="color: red; text-align: center;">
        <% if ("invalid_credentials".equals(request.getParameter("error"))) { %>
        ❌ Usuario o contraseña incorrectos. Inténtalo de nuevo.
        <% } else if ("not_registered".equals(request.getParameter("error"))) { %>
        ⚠️ Aún no estás registrado. Por favor, crea una cuenta.
        <% } %>
    </p>
    <% } %>

    <form action="<%= request.getContextPath() %>/LoginServlet" method="post">
        <div class="input-group">
            <label for="username">Usuario:</label>
            <input type="text" id="username" name="username" required>
        </div>
        <div class="input-group">
            <label for="password">Contraseña:</label>
            <input type="password" id="password" name="password" required>
        </div>
        <button type="submit" class="btn-login">Ingresar</button>
    </form>

    <p>¿No tienes cuenta? <a href="register.jsp">Regístrate</a></p>
    <a href="home.jsp" class="back-home">← Volver al inicio</a>
</div>
</body>
</html>
