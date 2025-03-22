<%--
  Created by IntelliJ IDEA.
  User: Maria
  Date: 20/03/2025
  Time: 4:46 p. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>DevConnect | Home</title>
        <link rel="stylesheet" type="text/css" href="../css/home.css">
    </head>
    <body>

        <header>
            <nav class="navbar">
                <div class="logo">DevConnect</div>
                <ul class="nav-links">
                    <li><a href="#">Inicio</a></li>
                    <li><a href="#">Ofertas de Empleo</a></li>
                    <li><a href="#">Empresas</a></li>
                    <li><a href="#">Contacto</a></li>
                    <li class="auth"><a href="login.jsp">Iniciar Sesión</a></li>
                    <li class="auth"><a href="register.jsp">Registrarse</a></li>
                </ul>
            </nav>
        </header>

        <main class="hero">
            <h1>Encuentra el trabajo ideal para ti</h1>
            <p>Explora miles de oportunidades laborales en tecnología y conecta con empresas innovadoras.</p>
            <a href="register.jsp" class="btn">Regístrate ahora</a>
        </main>
    </body>
</html>

