package mx.prowad.dao;


import java.util.ArrayList;

import mx.prowad.model.Atributo;
import mx.prowad.util.HibernateUtil;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

public class AtributoDAO {
	protected static Session session = null;
	public AtributoDAO() {
		session = HibernateUtil.getSessionFactory().getCurrentSession();
	}
	
	public Atributo consultarAtributo(int id) {
		Atributo atributo = null;

		try {
			session.beginTransaction();
			atributo = (Atributo) session.get(Atributo.class, id);
			session.getTransaction().commit();
		} catch (HibernateException he) {
			he.printStackTrace();
			session.getTransaction().rollback();
			throw he;
		}
		return atributo;

	}
	
	public void registrarAtributo(Atributo atributo) {
		try {
			session.beginTransaction();
			session.save(atributo);
			session.getTransaction().commit();
		} catch (HibernateException he) {
			he.printStackTrace();
			session.getTransaction().rollback();
			throw he;
		}
	}

	public void modificarAtributo(Atributo atributo) {

		try {
			session.beginTransaction();
			session.update(atributo);
			session.getTransaction().commit();
		} catch (HibernateException he) {
			he.printStackTrace();
			session.getTransaction().rollback();
			throw he;
		}
	}
	
	public void eliminarAtributo(Atributo atributo) {

		try {
			session.beginTransaction();
			session.delete(atributo);
			session.getTransaction().commit();
		} catch (HibernateException he) {
			he.printStackTrace();
			session.getTransaction().rollback();
			throw he;
		}
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Atributo> consultarAtributos() {
		ArrayList<Atributo> results = null;

		try {
			session.beginTransaction();
			Query query = session
					.createQuery("from Atributo");
			results = (ArrayList<Atributo>) query.list();
			session.getTransaction().commit();

		} catch (HibernateException he) {
			he.printStackTrace();
			session.getTransaction().rollback();
			throw he;
		}

		return results;
	}
}
