package mx.prowad.dao;


import java.util.ArrayList;
import java.util.List;

import mx.prowad.model.Usuario;
import mx.prowad.util.HibernateUtil;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

public class UsuarioDAO {
	protected static Session session = null;
	public UsuarioDAO() {
		session = HibernateUtil.getSessionFactory().getCurrentSession();
	}
	
	public Usuario consultarUsuario(String id) {
		Usuario usuario = null;

		try {
			session.beginTransaction();
			usuario = (Usuario) session.get(Usuario.class, id);
			session.getTransaction().commit();
		} catch (HibernateException he) {
			he.printStackTrace();
			session.getTransaction().rollback();
			throw he;
		}
		return usuario;

	}
	
	public void registrarUsuario(Usuario usuario) {
		try {
			session.beginTransaction();
			session.save(usuario);
			session.getTransaction().commit();
		} catch (HibernateException he) {
			he.printStackTrace();
			session.getTransaction().rollback();
			throw he;
		}
	}

	public void modificarUsuario(Usuario usuario) {

		try {
			session.beginTransaction();
			session.update(usuario);
			session.getTransaction().commit();
		} catch (HibernateException he) {
			he.printStackTrace();
			session.getTransaction().rollback();
			throw he;
		}
	}
	
	public void eliminarUsuario(Usuario usuario) {

		try {
			session.beginTransaction();
			session.delete(usuario);
			session.getTransaction().commit();
		} catch (HibernateException he) {
			he.printStackTrace();
			session.getTransaction().rollback();
			throw he;
		}
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Usuario> consultarUsuarios() {
		ArrayList<Usuario> results = null;

		try {
			session.beginTransaction();
			Query query = session
					.createQuery("from Usuario");
			results = (ArrayList<Usuario>) query.list();
			session.getTransaction().commit();

		} catch (HibernateException he) {
			he.printStackTrace();
			session.getTransaction().rollback();
			throw he;
		}

		return results;
	}

	@SuppressWarnings("unchecked")
	public Usuario consultarUsuarioCURP(String curp) {
		List<Usuario> results = null;

		try {
			session.beginTransaction();
			Query query = session.createQuery("from Usuario where curp = :curp");
			query.setParameter("curp", curp);
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
