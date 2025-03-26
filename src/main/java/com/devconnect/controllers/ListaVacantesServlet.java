package com.devconnect.controllers;

import com.devconnect.dao.VacanteDAO;
import com.devconnect.model.Vacante;
import com.devconnect.model.Usuario;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.util.List;


@WebServlet("/listaVacantes")
public class ListaVacantesServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1. Obtener el ID del usuario desde la sesión
        HttpSession session = request.getSession();
        Usuario usuarioLogueado = (Usuario) session.getAttribute("usuario");

        if (usuarioLogueado == null) {
            response.sendRedirect("login.jsp"); // Redirigir si no hay sesión
            return;
        }

        // 2. Obtener solo las vacantes del usuario
        VacanteDAO vacanteDAO = new VacanteDAO();
        List<Vacante> vacantes = vacanteDAO.obtenerVacantesPorUsuario(usuarioLogueado.getId());

        // 3. Enviar datos al JSP
        request.setAttribute("vacantes", vacantes);
        request.getRequestDispatcher("/view/listaVacantes.jsp").forward(request, response);
    }
}
