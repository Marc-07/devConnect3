<%--
  Created by IntelliJ IDEA.
  User: Maria
  Date: 25/03/2025
  Time: 6:52 p. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>DevConnect | Tus Vacantes</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/listaVacantes.css">
    </head>
    <body>

        <header>
            <nav class="navbar">
                <div class="logo">DevConnect</div>
                <ul class="nav-links">
                    <li>¡Hola, ${sessionScope.user}!</li> <!-- Cambiado a EL expression -->
                    <li><a href="${pageContext.request.contextPath}/listaVacantes">Tus vacantes</a></li>
                    <li><a href="${pageContext.request.contextPath}/view/home.jsp">Salir</a></li>
                </ul>
            </nav>
        </header>
        <table border="1">
          <thead>
          <tr>
            <th>Título</th>
            <th>Empresa</th>
            <th>Ubicación</th>
            <th>Salario</th>
            <th>Contrato</th>
            <th>Descripción</th>
            <th>Acciones</th>
          </tr>
          </thead>
          <tbody>
          <%-- Iteramos sobre la lista de vacantes --%>
          <c:forEach var="vacante" items="${vacantes}">
            <tr>
              <td>${vacante.titulo}</td>
              <td>${vacante.empresa}</td>
              <td>${vacante.ubicacion}</td>
              <td>${vacante.salario}</td>
              <td>${vacante.contrato}</td>
              <td>${vacante.descripcion}</td>
              <td>
                <a href="${pageContext.request.contextPath}/editarVacante?id=${vacante.id}" class="btn-editar">✏️</a>


                <form action="${pageContext.request.contextPath}/eliminarVacante" method="post"
                      onsubmit="return confirm('¿Estás seguro de eliminar esta vacante?');">
                  <input type="hidden" name="id" value="${vacante.id}">
                  <button type="submit" class="btn-eliminar">🗑️</button>
                </form>

              </td>
            </tr>
          </c:forEach>
          </tbody>
        </table>
    </body>
</html>
