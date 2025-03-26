package com.devconnect.controllers;

import com.devconnect.dao.VacanteDAO;
import com.devconnect.model.Vacante;
import com.devconnect.model.Usuario; // ✔️ Import clave que faltaba
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/editarVacante")
public class EditarVacanteServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        if (usuario == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        int idVacante = Integer.parseInt(request.getParameter("id"));
        VacanteDAO vacanteDAO = new VacanteDAO();
        Vacante vacante = vacanteDAO.obtenerVacantePorId(idVacante);

        if (vacante != null && vacante.getUsuario().getId() == usuario.getId()) {
            request.setAttribute("vacante", vacante);
            request.getRequestDispatcher("/view/editarVacante.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/listaVacantes?error=no_autorizado");
        }
    }
}