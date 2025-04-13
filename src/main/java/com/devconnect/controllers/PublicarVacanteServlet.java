package com.devconnect.controllers;
import com.devconnect.model.Vacante;
import com.devconnect.model.Usuario;
import com.devconnect.util.HibernateUtil;
import com.google.gson.Gson;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/publicarVacante")
public class PublicarVacanteServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Configuración CORS
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
        response.setHeader("Access-Control-Allow-Methods", "POST, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setContentType("application/json");

        Session session = null;
        PrintWriter out = response.getWriter();

        try {
            // 1. Verificar sesión
            HttpSession httpSession = request.getSession(false);
            if (httpSession == null || httpSession.getAttribute("usuario") == null) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                out.write("{\"error\": \"No autorizado\"}");
                return;
            }

            // 2. Obtener datos del formulario
            StringBuilder sb = new StringBuilder();
            String line;
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }

            // 3. Convertir JSON a objeto Vacante
            Gson gson = new Gson();
            Vacante vacante = gson.fromJson(sb.toString(), Vacante.class);

            // 4. Iniciar transacción de Hibernate
            session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            // 5. Asignar usuario (evitando LazyInitialization)
            Usuario usuario = (Usuario) httpSession.getAttribute("usuario");
            usuario = session.get(Usuario.class, usuario.getId()); // Recarga el usuario
            vacante.setUsuario(usuario);

            // 6. Guardar vacante
            session.persist(vacante);
            transaction.commit();

            // 7. Preparar respuesta SIN relaciones
            Map<String, Object> responseData = new HashMap<>();
            responseData.put("id", vacante.getId());
            responseData.put("titulo", vacante.getTitulo());
            responseData.put("empresa", vacante.getEmpresa());
            responseData.put("ubicacion", vacante.getUbicacion());
            responseData.put("salario", vacante.getSalario());
            responseData.put("contrato", vacante.getContrato());
            responseData.put("descripcion", vacante.getDescripcion());

            response.setStatus(HttpServletResponse.SC_CREATED);
            out.write(gson.toJson(responseData));

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.write("{\"error\": \"Error al publicar vacante\"}");
        } finally {
            if (session != null) session.close();
            out.close();
        }
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Configuración CORS para OPTIONS
        resp.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
        resp.setHeader("Access-Control-Allow-Methods", "POST, OPTIONS");
        resp.setHeader("Access-Control-Allow-Headers", "Content-Type");
        resp.setHeader("Access-Control-Allow-Credentials", "true");
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}