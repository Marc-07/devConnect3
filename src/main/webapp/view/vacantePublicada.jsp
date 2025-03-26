<%--
  Created by IntelliJ IDEA.
  User: Maria
  Date: 25/03/2025
  Time: 12:55 p. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>DevConnect | Vacante Publicada</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/vacantePubli.css">
    </head>
    <body>

        <main class="vacante-container">
          <div class="vacante-header">
            <h1 class="vacante-title">¡Vacante Publicada Exitosamente!</h1>
            <p>Tu vacante ha sido registrada en nuestro sistema.</p>
          </div>

          <div class="vacante-section">
            <h2>${vacantePublicada.titulo}</h2>
            <div class="vacante-meta">
              <span><strong>Empresa:</strong> ${vacantePublicada.empresa}</span>
              <span><strong>Ubicación:</strong> ${vacantePublicada.ubicacion}</span>
              <span><strong>Salario:</strong> $${vacantePublicada.salario}</span>
              <span><strong>Contrato:</strong> ${vacantePublicada.contrato}</span>
            </div>
          </div>

          <div class="vacante-section">
            <h3>Descripción del Puesto</h3>
            <p>${vacantePublicada.descripcion}</p>
          </div>

          <a href="${pageContext.request.contextPath}/listaVacantes" class="btn-vacantePublicada">Ver todas las vacantes</a>
        </main>
    </body>
</html>
