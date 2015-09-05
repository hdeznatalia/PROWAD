package mx.prowad.dao;


import java.util.ArrayList;
import java.util.List;

import mx.prowad.model.Rol;
import mx.prowad.util.HibernateUtil;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

public class RolDAO {
	protected static Session session = null;
	public RolDAO() {
		session = HibernateUtil.getSessionFactory().getCurrentSession();
	}
	
	public Rol consultarRol(int id) {
		Rol rol = null;

		try {
			session.beginTransaction();
			rol = (Rol) session.get(Rol.class, id);
			session.getTransaction().commit();
		} catch (HibernateException he) {
			he.printStackTrace();
			session.getTransaction().rollback();
			throw he;
		}
		return rol;

	}
	
	public void registrarRol(Rol rol) {
		try {
			session.beginTransaction();
			session.save(rol);
			session.getTransaction().commit();
		} catch (HibernateException he) {
			he.printStackTrace();
			session.getTransaction().rollback();
			throw he;
		}
	}

	public void modificarRol(Rol rol) {

		try {
			session.beginTransaction();
			session.update(rol);
			session.getTransaction().commit();
		} catch (HibernateException he) {
			he.printStackTrace();
			session.getTransaction().rollback();
			throw he;
		}
	}
	
	public void eliminarRol(Rol rol) {

		try {
			session.beginTransaction();
			session.delete(rol);
			session.getTransaction().commit();
		} catch (HibernateException he) {
			he.printStackTrace();
			session.getTransaction().rollback();
			throw he;
		}
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Rol> consultarRols() {
		ArrayList<Rol> results = null;

		try {
			session.beginTransaction();
			Query query = session
					.createQuery("from Rol");
			results = (ArrayList<Rol>) query.list();
			session.getTransaction().commit();

		} catch (HibernateException he) {
			he.printStackTrace();
			session.getTransaction().rollback();
			throw he;
		}

		return results;
	}

	@SuppressWarnings("unchecked")
	public Rol consultarRol(String nombre) {
		List<Rol> results = null;

		try {
			session.beginTransaction();
			Query query = session.createQuery("from Rol where nombre = :nombre");
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
