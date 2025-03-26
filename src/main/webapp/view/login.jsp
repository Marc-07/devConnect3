<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Login - DevConnect</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login.css">
        </style>
    </head>
    <body>
    <div class="login-container">
        <h2>Iniciar Sesión</h2>

        <!-- Mensaje de error mejorado -->
        <% if (request.getParameter("error") != null) { %>
        <div id="mensaje-error" class="mensaje-error">
            <% if ("invalid_credentials".equals(request.getParameter("error"))) { %>
            ❌ Usuario o contraseña incorrectos
            <% } else if ("not_registered".equals(request.getParameter("error"))) { %>
            ⚠️ Aún no estás registrado. <a href="${pageContext.request.contextPath}/view/register.jsp">Crear cuenta</a>
            <% } else if ("no_autenticado".equals(request.getParameter("error"))) { %>
            🔐 Debes iniciar sesión para continuar
            <% } %>
        </div>

        <script>
            setTimeout(function () {
                var mensajeError = document.getElementById("mensaje-error");
                if (mensajeError) {
                    mensajeError.style.transition = "opacity 0.5s";
                    mensajeError.style.opacity = "0";
                    setTimeout(() => mensajeError.style.display = "none", 500);
                }
            }, 4000);
        </script>
        <% } %>

        <form action="${pageContext.request.contextPath}/login" method="post">
            <div class="input-group">
                <label for="username">Usuario:</label>
                <input type="text" id="username" name="username" required autofocus>
            </div>
            <div class="input-group">
                <label for="password">Contraseña:</label>
                <input type="password" id="password" name="password" required>
            </div>
            <button type="submit" class="btn-login">Ingresar</button>
        </form>

        <div class="links-container">
            <p>¿No tienes cuenta? <a href="${pageContext.request.contextPath}/view/register.jsp">Regístrate</a></p>
            <a href="${pageContext.request.contextPath}/view/home.jsp" class="back-home">← Volver al inicio</a>
        </div>
    </div>

        <script>
            // Validación básica del formulario
            document.querySelector('form').addEventListener('submit', function(e) {
                const username = document.getElementById('username').value.trim();
                const password = document.getElementById('password').value.trim();

                if(username === '' || password === '') {
                    e.preventDefault();
                    alert('Por favor complete todos los campos');
                }
            });
        </script>
    </body>
</html>
