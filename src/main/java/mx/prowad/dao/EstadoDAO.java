package mx.prowad.dao;


import java.util.ArrayList;
import java.util.List;

import mx.prowad.model.Estado;
import mx.prowad.util.HibernateUtil;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

public class EstadoDAO {
	protected static Session session = null;
	public EstadoDAO() {
		session = HibernateUtil.getSessionFactory().getCurrentSession();
	}
	
	public Estado consultarEstado(int id) {
		Estado estado = null;

		try {
			session.beginTransaction();
			estado = (Estado) session.get(Estado.class, id);
			session.getTransaction().commit();
		} catch (HibernateException he) {
			he.printStackTrace();
			session.getTransaction().rollback();
			throw he;
		}
		return estado;

	}
	
	public void registrarEstado(Estado estado) {
		try {
			session.beginTransaction();
			session.save(estado);
			session.getTransaction().commit();
		} catch (HibernateException he) {
			he.printStackTrace();
			session.getTransaction().rollback();
			throw he;
		}
	}

	public void modificarEstado(Estado estado) {

		try {
			session.beginTransaction();
			session.update(estado);
			session.getTransaction().commit();
		} catch (HibernateException he) {
			he.printStackTrace();
			session.getTransaction().rollback();
			throw he;
		}
	}
	
	public void eliminarEstado(Estado estado) {

		try {
			session.beginTransaction();
			session.delete(estado);
			session.getTransaction().commit();
		} catch (HibernateException he) {
			he.printStackTrace();
			session.getTransaction().rollback();
			throw he;
		}
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Estado> consultarEstados() {
		ArrayList<Estado> results = null;

		try {
			session.beginTransaction();
			Query query = session
					.createQuery("from Estado");
			results = (ArrayList<Estado>) query.list();
			session.getTransaction().commit();

		} catch (HibernateException he) {
			he.printStackTrace();
			session.getTransaction().rollback();
			throw he;
		}

		return results;
	}

	@SuppressWarnings("unchecked")
	public Estado consultarEstado(String nombre) {
		List<Estado> results = null;

		try {
			session.beginTransaction();
			Query query = session.createQuery("from Estado where nombre = :nombre");
			query.setParameter("nombre", nombre);
			results = query.list();
			session.getTransaction().commit();
		} catch (HibernateException he) {
			he.printStackTrace();
			session.getTransaction().rollback();
			throw he;
		}
		if (results.isEmpty()) {
			return null;
		} else
			return results.get(0);
		
	}
}
