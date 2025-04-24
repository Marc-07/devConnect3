package com.devconnect.dao;

import com.devconnect.model.Usuario;
import com.devconnect.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class UsuarioDAO {

    public boolean registrarUsuario(Usuario usuario) {
        if (existeUsuario(usuario.getUsername())) {
            return false; // Usuario ya registrado
        }

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(usuario);
            transaction.commit();
            return true;
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
    public boolean existeUsuario(String username) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Usuario> query = session.createQuery("FROM Usuario WHERE username = :username", Usuario.class);
            query.setParameter("username", username);
            return query.uniqueResult() != null;  // Si el resultado es null, el usuario no existe
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}