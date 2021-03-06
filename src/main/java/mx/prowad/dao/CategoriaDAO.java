package mx.prowad.dao;

import java.util.ArrayList;
import java.util.List;

import mx.prowad.model.Categoria;
import mx.prowad.model.CategoriaAtributo;
import mx.prowad.model.Usuario;
import mx.prowad.util.HibernateUtil;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

public class CategoriaDAO {
	protected static Session session = null;
	public CategoriaDAO() {
		session = HibernateUtil.getSessionFactory().getCurrentSession();
	}
	
	public Categoria consultarCategoria(int id) {
		Categoria categoria = null;

		try {
			session.beginTransaction();
			categoria = (Categoria) session.get(Categoria.class, id);
			session.getTransaction().commit();
		} catch (HibernateException he) {
			he.printStackTrace();
			session.getTransaction().rollback();
			throw he;
		}
		return categoria;

	}
	
	public void registrarCategoria(Categoria categoria) {
		try {
			session.beginTransaction();
			session.save(categoria);
			session.getTransaction().commit();
		} catch (HibernateException he) {
			he.printStackTrace();
			session.getTransaction().rollback();
			throw he;
		}
	}

	public void modificarCategoria(Categoria categoria) {

		try {
			session.beginTransaction();
			Query query1 = session.createQuery("DELETE FROM CategoriaAtributo WHERE id.categoria.id = :id");
			query1.setParameter("id", categoria.getId());
			query1.executeUpdate();
			
			for (CategoriaAtributo ca : categoria.getCategoriasAtributo()) {
				session.saveOrUpdate(ca);
			}
			session.update(categoria);
			session.getTransaction().commit();
		} catch (HibernateException he) {
			he.printStackTrace();
			session.getTransaction().rollback();
			throw he;
		}
	}
	
	public void eliminarCategoria(Categoria categoria) {

		try {
			session.beginTransaction();
			session.delete(categoria);
			session.getTransaction().commit();
		} catch (HibernateException he) {
			he.printStackTrace();
			session.getTransaction().rollback();
			throw he;
		}
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Categoria> consultarCategorias() {
		ArrayList<Categoria> results = null;

		try {
			session.beginTransaction();
			Query query = session
					.createQuery("from Categoria");
			results = (ArrayList<Categoria>) query.list();
			session.getTransaction().commit();

		} catch (HibernateException he) {
			he.printStackTrace();
			session.getTransaction().rollback();
			throw he;
		}

		return results;
	}

	@SuppressWarnings("unchecked")
	public Categoria consultarCategoria(String nombre) {
		List<Categoria> results = null;

		try {
			session.beginTransaction();
			Query query = session.createQuery("from Categoria where nombre = :nombre");
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
