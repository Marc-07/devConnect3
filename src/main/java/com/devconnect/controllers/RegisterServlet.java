package com.devconnect.controllers;

import com.devconnect.dao.UsuarioDAO;
import com.devconnect.model.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtener parámetros del formulario
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String correo = request.getParameter("correo");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Crear objeto Usuario
        Usuario usuario = new Usuario(nombre, apellido, correo, username, password);

        try {
            // Registrar usuario
            usuarioDAO.registrarUsuario(usuario);
            response.sendRedirect("view/success.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error al registrar: " + e.getMessage());
            request.getRequestDispatcher("view/register.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("view/register.jsp").forward(request, response);
    }
}


