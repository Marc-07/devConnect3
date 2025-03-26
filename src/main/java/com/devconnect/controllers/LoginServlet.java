package com.devconnect.controllers;

import com.devconnect.dao.UsuarioDAO;
import com.devconnect.model.Usuario;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = usuarioDAO.validarUsuario(username, password);

        if (usuario != null) {
            HttpSession session = request.getSession();
            // Guardamos tanto el objeto completo como el username por separado
            session.setAttribute("usuario", usuario);
            session.setAttribute("user", usuario.getUsername()); // Esto es lo que usa welcome.jsp
            response.sendRedirect(request.getContextPath() + "/view/welcome.jsp");
        } else {
            response.sendRedirect(request.getContextPath() + "/view/login.jsp?error=invalid_credentials");
        }
    }
}