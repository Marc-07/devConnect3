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
    <link rel="stylesheet" type="text/css" href="../css/home.css">
  </head>
  <body>

  <header>
    <nav class="navbar">
      <div class="logo">DevConnect</div>
      <ul class="nav-links">
        <li><a href="home.jsp">Inicio</a></li>
        <li><a href="#">Ofertas de Empleo</a></li>
        <li><a href="#">Empresas</a></li>
        <li><a href="#">Contacto</a></li>
      </ul>
    </nav>
  </header>

  <main class="hero">
    <h2 class="welcome-text">
      ¡Bienvenido/a, <%= session.getAttribute("user") %>!
    </h2>
  </main>
  </body>
</html>