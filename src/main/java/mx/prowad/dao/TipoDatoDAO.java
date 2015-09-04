package mx.prowad.dao;

import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import mx.prowad.model.TipoDato;
import mx.prowad.util.HibernateUtil;

public class TipoDatoDAO {
	protected static Session session = null;
	public TipoDatoDAO() {
		session = HibernateUtil.getSessionFactory().getCurrentSession();
	}
	
	public TipoDato consultarTipoDato(int id) {
		TipoDato tipoDato = null;

		try {
			session.beginTransaction();
			tipoDato = (TipoDato) session.get(TipoDato.class, id);
			session.getTransaction().commit();
		} catch (HibernateException he) {
			he.printStackTrace();
			session.getTransaction().rollback();
			throw he;
		}
		return tipoDato;

	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<TipoDato> consultarTiposDato() {
		ArrayList<TipoDato> results = null;

		try {
			session.beginTransaction();
			Query query = session
					.createQuery("from TipoDato");
			results = (ArrayList<TipoDato>) query.list();
			session.getTransaction().commit();

		} catch (HibernateException he) {
			he.printStackTrace();
			session.getTransaction().rollback();
			throw he;
		}

		return results;
	}
}
