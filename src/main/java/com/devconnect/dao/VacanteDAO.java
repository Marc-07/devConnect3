package com.devconnect.dao;

import com.devconnect.model.Vacante;
import com.devconnect.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;
import java.util.Collections;  // Añade esto

public class VacanteDAO {

    // En VacanteDAO.java
    public List<Vacante> obtenerVacantesPorUsuario(Long usuarioId) {  // Cambia int por Long
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Vacante> query = session.createQuery(
                    "FROM Vacante WHERE usuario.id = :usuarioId", Vacante.class
            );
            query.setParameter("usuarioId", usuarioId);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    // ▶ Método para REGISTRAR una nueva vacante (ya lo tenías)
    public boolean registrarVacante(Vacante vacante) {
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
            e.printStackTrace();
            return false;
        }
    }

    // ▶ Método para BUSCAR una vacante por ID (ya lo tenías)
    public Vacante obtenerVacantePorId(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Vacante.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // ▶ Método para ACTUALIZAR una vacante (por si luego lo necesitas)
    public boolean actualizarVacante(Vacante vacante) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.merge(vacante); // Actualiza la entidad
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    // ▶ Método para ELIMINAR una vacante (por si luego lo necesitas)
    public boolean eliminarVacante(int idVacante) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Vacante vacante = session.get(Vacante.class, idVacante);
            if (vacante != null) {
                session.remove(vacante); // Elimina la entidad
                transaction.commit();
                return true;
            }
            return false;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }
}
