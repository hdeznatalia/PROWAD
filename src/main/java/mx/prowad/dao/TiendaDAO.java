package mx.prowad.dao;

import java.util.ArrayList;

import mx.prowad.model.Tienda;
import mx.prowad.util.HibernateUtil;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

public class TiendaDAO {
	protected static Session session = null;
	public TiendaDAO() {
		session = HibernateUtil.getSessionFactory().getCurrentSession();
	}
	
	public Tienda consultarTienda(int id) {
		Tienda tienda = null;

		try {
			session.beginTransaction();
			tienda = (Tienda) session.get(Tienda.class, id);
			session.getTransaction().commit();
		} catch (HibernateException he) {
			he.printStackTrace();
			session.getTransaction().rollback();
			throw he;
		}
		return tienda;

	}
	
	public void registrarTienda(Tienda tienda) {
		try {
			session.beginTransaction();
			session.save(tienda);
			session.getTransaction().commit();
		} catch (HibernateException he) {
			he.printStackTrace();
			session.getTransaction().rollback();
			throw he;
		}
	}

	public void modificarTienda(Tienda tienda) {

		try {
			session.beginTransaction();
			session.update(tienda);
			session.getTransaction().commit();
		} catch (HibernateException he) {
			he.printStackTrace();
			session.getTransaction().rollback();
			throw he;
		}
	}
	
	public void eliminarTienda(Tienda tienda) {

		try {
			session.beginTransaction();
			session.delete(tienda);
			session.getTransaction().commit();
		} catch (HibernateException he) {
			he.printStackTrace();
			session.getTransaction().rollback();
			throw he;
		}
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Tienda> consultarTiendas() {
		ArrayList<Tienda> results = null;

		try {
			session.beginTransaction();
			Query query = session
					.createQuery("from Tienda");
			results = (ArrayList<Tienda>) query.list();
			session.getTransaction().commit();

		} catch (HibernateException he) {
			he.printStackTrace();
			session.getTransaction().rollback();
			throw he;
		}

		return results;
	}
}
