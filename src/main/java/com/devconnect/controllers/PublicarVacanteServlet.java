package com.devconnect.controllers;

import com.devconnect.dao.VacanteDAO;
import com.devconnect.model.Vacante;
import com.devconnect.model.Usuario;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/publicarVacante")
public class PublicarVacanteServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("usuario") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        Usuario usuario = (Usuario) session.getAttribute("usuario");
        VacanteDAO vacanteDAO = new VacanteDAO();

        try {
            // 1. Validar y obtener parámetros básicos
            String titulo = validarCampo(request.getParameter("titulo"), "título");
            String empresa = validarCampo(request.getParameter("empresa"), "empresa");
            String ubicacion = validarCampo(request.getParameter("ubicacion"), "ubicación");
            double salario = validarSalario(request.getParameter("salario"));
            String contrato = validarCampo(request.getParameter("contrato"), "tipo de contrato");
            String descripcion = request.getParameter("descripcion");

            // 2. Crear y configurar la vacante
            Vacante vacante = new Vacante();
            vacante.setTitulo(titulo);
            vacante.setEmpresa(empresa);
            vacante.setUbicacion(ubicacion);
            vacante.setSalario(salario);
            vacante.setContrato(contrato);
            vacante.setDescripcion(descripcion);
            vacante.setUsuario(usuario);

            // 3. Registrar la vacante
            boolean guardado = vacanteDAO.registrarVacante(vacante);

            if (guardado) {
                // Guardar en sesión para mostrar en la página de éxito
                session.setAttribute("vacantePublicada", vacante);
                response.sendRedirect(request.getContextPath() + "/view/vacantePublicada.jsp");
            } else {
                manejarError(request, response, "Error al guardar la vacante en la base de datos");
            }

        } catch (IllegalArgumentException e) {
            manejarError(request, response, e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            manejarError(request, response, "Error interno del sistema: " + e.getMessage());
        }
    }

    // Métodos auxiliares

    private String validarCampo(String valor, String nombreCampo) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException("El campo " + nombreCampo + " no puede estar vacío");
        }
        return valor.trim();
    }

    private double validarSalario(String salarioStr) {
        if (salarioStr == null || salarioStr.trim().isEmpty()) {
            throw new IllegalArgumentException("El salario no puede estar vacío");
        }
        try {
            double salario = Double.parseDouble(salarioStr);
            if (salario <= 0) {
                throw new IllegalArgumentException("El salario debe ser mayor que cero");
            }
            return salario;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("El salario debe ser un número válido");
        }
    }

    private void manejarError(HttpServletRequest request, HttpServletResponse response, String mensajeError)
            throws ServletException, IOException {
        request.setAttribute("error", mensajeError);
        request.getRequestDispatcher("/view/publicar.jsp").forward(request, response);
    }
}



