package com.devconnect.controllers;

import com.devconnect.dao.UsuarioDAO;
import com.devconnect.model.Usuario;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/api/usuario/*")
public class UsuarioServlet extends HttpServlet {

    private final UsuarioDAO usuarioDAO = new UsuarioDAO();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setCorsHeaders(resp);

        String path = req.getPathInfo(); // /register o /login
        BufferedReader reader = req.getReader();
        Usuario userRequest = objectMapper.readValue(reader, Usuario.class);

        resp.setContentType("application/json");

        if ("/register".equals(path)) {
            boolean registrado = usuarioDAO.registrarUsuario(userRequest);

            if (registrado) {
                resp.setStatus(HttpServletResponse.SC_CREATED);
                resp.getWriter().write("{\"mensaje\": \"Usuario registrado exitosamente\"}");
            } else {
                resp.setStatus(HttpServletResponse.SC_CONFLICT); // 409
                resp.getWriter().write("{\"error\": \"El usuario ya está registrado\"}");
            }
        }

        else if ("/login".equals(path)) {
            Usuario usuarioValidado = usuarioDAO.validarUsuario(userRequest.getUsername(), userRequest.getPassword());

            if (usuarioValidado != null) {
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.getWriter().write("{\"mensaje\": \"Inicio de sesión exitoso\"}");
            } else {
                resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401
                resp.getWriter().write("{\"error\": \"Credenciales incorrectas\"}");
            }
        }

        else {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND); // 404
            resp.getWriter().write("{\"error\": \"Ruta no válida\"}");
        }
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setCorsHeaders(resp);
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    private void setCorsHeaders(HttpServletResponse resp) {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        resp.setHeader("Access-Control-Allow-Headers", "Content-Type");
    }
}

