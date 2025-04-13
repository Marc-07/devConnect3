package com.devconnect.dao;

import com.devconnect.model.Vacante;
import com.devconnect.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class VacanteDAO {

    // Método para obtener vacante por ID (versión compatible con int)
    public Vacante obtenerVacantePorId(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Vacante.class, (long) id);
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener vacante por ID", e);
        }
    }

    // Método para eliminar vacante (versión compatible con int)
    public boolean eliminarVacante(int id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Vacante vacante = session.get(Vacante.class, (long) id);
            if (vacante != null) {
                session.remove(vacante);
                transaction.commit();
                return true;
            }
            return false;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Error al eliminar vacante", e);
        }
    }

    // Métodos adicionales
    public Optional<Vacante> obtenerPorId(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return Optional.ofNullable(session.get(Vacante.class, id));
        }
    }

    // Versión con parámetro usuarioId
    public List<Vacante> obtenerVacantesPorUsuario(Long usuarioId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Vacante WHERE usuario.id = :usuarioId", Vacante.class)
                    .setParameter("usuarioId", usuarioId)
                    .getResultList();
        }
    }

    // Nueva versión para obtener TODAS las vacantes sin filtrar
    public List<Vacante> obtenerTodasLasVacantes() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Vacante", Vacante.class)
                    .getResultList();
        }
    }

    public boolean guardar(Vacante vacante) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(vacante);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Error al guardar vacante", e);
        }
    }

    public boolean actualizarVacante(Vacante vacante) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.merge(vacante);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Error al actualizar vacante", e);
        }
    }
}
