<%--
  Created by IntelliJ IDEA.
  User: Maria
  Date: 26/03/2025
  Time: 10:46 a. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>DevConnect | Sobre Nosotros</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/sobreNosotros.css"> <!-- Usa tu CSS global -->
    </head>
    <body>
        <header>
            <nav class="navbar">
                <div class="logo">DevConnect</div>
                <ul class="nav-links">
                    <li><a href="${pageContext.request.contextPath}/view/sobreNosotros.jsp">Sobre Nosotros</a></li>
                    <li class="auth"><a href="login.jsp">Iniciar Sesión</a></li>
                    <li class="auth"><a href="register.jsp">Registrarse</a></li>
                </ul>
            </nav>
        </header>

        <main class="about-container">
            <h1 class="about-title">Conectando Talentos Tech desde 2024</h1>

            <p>En DevConnect, creemos que cada desarrollador merece <strong>oportunidades que impulsen su crecimiento</strong>. Nacimos como respuesta a la creciente necesidad de un espacio donde la comunidad tech pueda encontrar no solo empleo, sino proyectos inspiradores y equipos afines.</p>

            <p>Actualmente operamos en 5 países de Latinoamérica, conectando a más de 10,000 desarrolladores con startups y empresas establecidas que valoran el talento técnico. Nuestro algoritmo de matching analiza no solo habilidades técnicas, sino también valores culturales para crear conexiones duraderas.</p>

            <div class="about-mission">

                <h2 class="titulo">Nuestra Misión</h2>

               <p><i>"En DevConnect, nos apasiona democratizar las oportunidades tech. Vamos más allá de conectar talento
                   con empresas; construimos un ecosistema donde los desarrolladores encuentran proyectos que inspiran,
                   empresas acceden a perfiles verificados con inteligencia artificial, y todos crecen mediante nuestra
                   comunidad activa con eventos exclusivos. Eliminamos los procesos obsoletos para priorizar lo esencial:
                   matching inteligente, transparencia radical en salarios, y relaciones profesionales que transforman
                   carreras."</i></p>
            </div>

            <h2 class="titulo2">¿Por qué elegir DevConnect?</h2>
            <ul class="about-values">
                <li><strong>Enfoque comunitario</strong>: Creamos una red de apoyo para desarrolladores.</li>
                <li><strong>Transparencia</strong>: Salarios y requisitos claros desde el primer momento.</li>
                <li><strong>Tecnología intuitiva</strong>: Plataforma diseñada para seres humanos.</li>
            </ul>
        </main>
    </body>
</html>
