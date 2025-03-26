package com.devconnect.controllers;

import com.devconnect.dao.VacanteDAO;
import com.devconnect.model.Vacante;
import com.devconnect.model.Usuario;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/eliminarVacante")
public class EliminarVacanteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1. Validar sesión
        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        // 2. Obtener ID de la vacante a eliminar
        int idVacante = Integer.parseInt(request.getParameter("id"));

        // 3. Validar propiedad antes de eliminar
        VacanteDAO vacanteDAO = new VacanteDAO();
        Vacante vacante = vacanteDAO.obtenerVacantePorId(idVacante);

        if (vacante != null && vacante.getUsuario().getId() == usuario.getId()) {
            boolean eliminado = vacanteDAO.eliminarVacante(idVacante);
            if (eliminado) {
                response.sendRedirect(request.getContextPath() + "/listaVacantes?success=eliminado");
            } else {
                response.sendRedirect(request.getContextPath() + "/listaVacantes?error=eliminacion_fallida");
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/listaVacantes?error=no_autorizado");
        }
    }
}

