package mx.prowad.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mx.prowad.bs.EstadoEnum;
import mx.prowad.model.Carrito;
import mx.prowad.model.Estado;
import mx.prowad.model.ProductoCarrito;
import mx.prowad.util.HibernateUtil;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

public class CarritoDAO {
	protected static Session session = null;
	public CarritoDAO() {
		session = HibernateUtil.getSessionFactory().getCurrentSession();
	}
	
	public Carrito consultarCarrito(int id) {
		Carrito carrito = null;

		try {
			session.beginTransaction();
			carrito = (Carrito) session.get(Carrito.class, id);
			session.getTransaction().commit();
		} catch (HibernateException he) {
			he.printStackTrace();
			session.getTransaction().rollback();
			throw he;
		}
		return carrito;

	}
	
	public void registrarCarrito(Carrito carrito) {
		try {
			session.beginTransaction();
			session.save(carrito);
			session.getTransaction().commit();
		} catch (HibernateException he) {
			he.printStackTrace();
			session.getTransaction().rollback();
			throw he;
		}
	}

	public void modificarCarrito(Carrito carrito) {

		try {
			session.beginTransaction();
			Query query1 = session.createQuery("DELETE FROM ProductoCarrito WHERE id.carrito.id = :id");
			query1.setParameter("id", carrito.getId());
			query1.executeUpdate();
			
			for (ProductoCarrito pc : carrito.getProductosCarrito()) {
				session.saveOrUpdate(pc);
			}
			session.update(carrito);
			session.getTransaction().commit();
		} catch (HibernateException he) {
			he.printStackTrace();
			session.getTransaction().rollback();
			throw he;
		}
	}
	
	public void eliminarCarrito(Carrito carrito) {

		try {
			session.beginTransaction();
			session.delete(carrito);
			session.getTransaction().commit();
		} catch (HibernateException he) {
			he.printStackTrace();
			session.getTransaction().rollback();
			throw he;
		}
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Carrito> consultarCarritos() {
		ArrayList<Carrito> results = null;

		try {
			session.beginTransaction();
			Query query = session
					.createQuery("from Carrito");
			results = (ArrayList<Carrito>) query.list();
			session.getTransaction().commit();

		} catch (HibernateException he) {
			he.printStackTrace();
			session.getTransaction().rollback();
			throw he;
		}

		return results;
	}

	@SuppressWarnings("unchecked")
	public Carrito consultarCarrito(Date fechaCompra) {
		List<Carrito> results = null;

		try {
			session.beginTransaction();
			Query query = session.createQuery("from Carrito where fechaCompra = :fecha");
			query.setParameter("fecha", fechaCompra.toString());
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

	public Carrito consultarCarritoActivo() {
		List<Carrito> results = null;

		try {
			session.beginTransaction();
			Query query = session.createQuery("from Carrito where estado.id = :id");
			query.setParameter("id", EstadoEnum.consultarIdEstado(EstadoEnum.Estado.PENDIENTE));
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

	public List<Carrito> consultarCarritosUsuario(String curp) {
		List<Carrito> results = null;

		try {
			session.beginTransaction();
			Query query = session.createQuery("from Carrito where usuario.curp = :curp");
			query.setParameter("curp", curp);
			results = query.list();
			session.getTransaction().commit();
		} catch (HibernateException he) {
			he.printStackTrace();
			session.getTransaction().rollback();
			throw he;
		}
		if (results.isEmpty()) {
			return new ArrayList<Carrito>();
		} else
			return results;
	}
}
