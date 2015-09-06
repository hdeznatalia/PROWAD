package mx.prowad.dao;


import java.util.ArrayList;
import java.util.List;

import mx.prowad.model.CategoriaAtributo;
import mx.prowad.model.Producto;
import mx.prowad.model.ProductoAtributo;
import mx.prowad.model.ProductoCategoria;
import mx.prowad.util.HibernateUtil;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

public class ProductoDAO {
	protected static Session session = null;
	public ProductoDAO() {
		session = HibernateUtil.getSessionFactory().getCurrentSession();
	}
	
	public Producto consultarProducto(int id) {
		Producto Producto = null;

		try {
			session.beginTransaction();
			Producto = (Producto) session.get(Producto.class, id);
			session.getTransaction().commit();
		} catch (HibernateException he) {
			he.printStackTrace();
			session.getTransaction().rollback();
			throw he;
		}
		return Producto;

	}
	
	public void registrarProducto(Producto Producto) {
		try {
			session.beginTransaction();
			session.save(Producto);
			session.getTransaction().commit();
		} catch (HibernateException he) {
			he.printStackTrace();
			session.getTransaction().rollback();
			throw he;
		}
	}

	public void modificarProducto(Producto producto) {

		try {
			session.beginTransaction();
			
			Query query1 = session.createQuery("DELETE FROM ProductoCategoria WHERE id.producto.id = :id");
			query1.setParameter("id", producto.getId());
			query1.executeUpdate();
			for (ProductoCategoria pc : producto.getProductosCategoria()) {
				session.saveOrUpdate(pc);
			}
			
			Query query2 = session.createQuery("DELETE FROM ProductoAtributo WHERE id.producto.id = :id");
			query2.setParameter("id", producto.getId());
			query2.executeUpdate();
			for (ProductoAtributo pa : producto.getProductosAtributo()) {
				session.saveOrUpdate(pa);
			}
			
			session.update(producto);
			session.getTransaction().commit();
		} catch (HibernateException he) {
			he.printStackTrace();
			session.getTransaction().rollback();
			throw he;
		}
	}
	
	public void eliminarProducto(Producto Producto) {

		try {
			session.beginTransaction();
			session.delete(Producto);
			session.getTransaction().commit();
		} catch (HibernateException he) {
			he.printStackTrace();
			session.getTransaction().rollback();
			throw he;
		}
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Producto> consultarProductos() {
		ArrayList<Producto> results = null;

		try {
			session.beginTransaction();
			Query query = session
					.createQuery("from Producto");
			results = (ArrayList<Producto>) query.list();
			session.getTransaction().commit();

		} catch (HibernateException he) {
			he.printStackTrace();
			session.getTransaction().rollback();
			throw he;
		}

		return results;
	}

	@SuppressWarnings("unchecked")
	public Producto consultarProducto(String nombre) {
		List<Producto> results = null;

		try {
			session.beginTransaction();
			Query query = session.createQuery("from Producto where nombre = :nombre");
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
