package com.devconnect.controllers;

import com.devconnect.dao.VacanteDAO;
import com.devconnect.model.Vacante;
import com.devconnect.model.Usuario;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/actualizarVacante")
public class ActualizarVacanteServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1. Validar sesión del usuario
        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        if (usuario == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        // 2. Obtener parámetros del formulario
        int idVacante = Integer.parseInt(request.getParameter("id"));
        String titulo = request.getParameter("titulo");
        String empresa = request.getParameter("empresa");
        String ubicacion = request.getParameter("ubicacion");
        double salario = Double.parseDouble(request.getParameter("salario"));
        String contrato = request.getParameter("contrato");
        String descripcion = request.getParameter("descripcion");

        // 3. Validar que la vacante pertenezca al usuario
        VacanteDAO vacanteDAO = new VacanteDAO();
        Vacante vacante = vacanteDAO.obtenerVacantePorId(idVacante);

        if (vacante == null || vacante.getUsuario().getId() != usuario.getId()) {
            response.sendRedirect(request.getContextPath() + "/listaVacantes?error=no_autorizado");
            return;
        }

        // 4. Actualizar los datos de la vacante
        vacante.setTitulo(titulo);
        vacante.setEmpresa(empresa);
        vacante.setUbicacion(ubicacion);
        vacante.setSalario(salario);
        vacante.setContrato(contrato);
        vacante.setDescripcion(descripcion);

        // 5. Guardar cambios en la base de datos
        boolean actualizado = vacanteDAO.actualizarVacante(vacante);

        if (actualizado) {
            response.sendRedirect(request.getContextPath() + "/listaVacantes?success=actualizado");
        } else {
            response.sendRedirect(request.getContextPath() + "/listaVacantes?error=actualizacion_fallida");
        }
    }
}