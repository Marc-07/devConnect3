package com.devconnect.dao;

import com.devconnect.model.Usuario;
import com.devconnect.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UsuarioDAO {

    // Método para registrar un nuevo usuario
    public void registrarUsuario(Usuario usuario) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Iniciar transacción
            transaction = session.beginTransaction();

            // Guardar el usuario
            session.persist(usuario);

            // Commit transacción
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Error al registrar usuario", e);
        }
    }

    // Método para validar usuario (login)
    public Usuario validarUsuario(String username, String password) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Usuario usuario = session.createQuery(
                            "FROM Usuario WHERE username = :username", Usuario.class)
                    .setParameter("username", username)
                    .uniqueResult();

            // Verificación básica de contraseña (deberías usar BCrypt en producción)
            if (usuario != null && usuario.getPassword().equals(password)) {
                return usuario;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}