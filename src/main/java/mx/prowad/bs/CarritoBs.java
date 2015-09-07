package mx.prowad.bs;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.JDBCException;

import mx.prowad.dao.CarritoDAO;
import mx.prowad.dao.EstadoDAO;
import mx.prowad.dao.ProductoDAO;
import mx.prowad.dao.UsuarioDAO;
import mx.prowad.model.Carrito;
import mx.prowad.model.Estado;
import mx.prowad.model.Producto;
import mx.prowad.model.ProductoCarrito;
import mx.prowad.model.Usuario;
import mx.prowad.util.PROWADException;
import mx.prowad.util.SessionManager;

public class CarritoBs {
	public static Carrito consultarCarrito(int id) {
		Carrito carrito = null;
		carrito = new CarritoDAO().consultarCarrito(id);
		if (carrito == null) {
			throw new PROWADException("No se puede consultar el carrito.",
					"MSG13");
		}
		return carrito;
	}
	
	public static Carrito consultarCarrito(Date fecha) {
		Carrito carrito = null;
		carrito = new CarritoDAO().consultarCarrito(fecha);
		if (carrito == null) {
			throw new PROWADException("No se puede consultar el carrito.",
					"MSG13");
		}
		return carrito;
	}

	public static List<Carrito> consultarCarritosUsuario() throws Exception {
		Usuario usrActivo = SessionManager.consultarUsuarioActivo();
		List<Carrito> carritos = new CarritoDAO().consultarCarritosUsuario(usrActivo.getCurp());
		if (carritos == null) {
			throw new PROWADException(
					"No se pueden consultar los carritos.",
					"MSG13");
		}
		return carritos;
	}

	public static void registrarCarrito(Carrito model) throws Exception{
		validar(model);
		new CarritoDAO().registrarCarrito(model);
	}

	private static void validar(Carrito model) {
		//Faltan validaciones
	}

	public static void eliminarCarrito(Carrito model) throws Exception {
		try {
			new CarritoDAO().eliminarCarrito(model);
		} catch (JDBCException je) {
			if(je.getErrorCode() == 1451) {
				throw new PROWADException(
						"No se puede eliminar el carrito.",
						"MSG14");
			}
			System.out.println("ERROR CODE " + je.getErrorCode());
			je.printStackTrace();
			throw new Exception();
		}
	}

	public static void modificarCarrito(Carrito model) {
		validar(model);
		new CarritoDAO().modificarCarrito(model);
		
	}

	public static void agregarProductos(Carrito model,
			List<Producto> productos) {
		Set<ProductoCarrito> productosCarrito = new HashSet<ProductoCarrito>(0);
		model.getProductosCarrito().clear();
		for(Producto p : productos) {
			Producto productoBD = new ProductoDAO().consultarProducto(p.getNombre());
			ProductoCarrito pc = new ProductoCarrito(productoBD, model);
			productosCarrito.add(pc);
		}
		model.setProductosCarrito(productosCarrito);
		
	}

	public static List<Producto> consultarProductos(Carrito model) {
		List<Producto> productos = new ArrayList<Producto>();
		for(ProductoCarrito pc : model.getProductosCarrito()) {
			Producto producto = pc.getId().getProducto();
			productos.add(producto);
		}
		return productos;
	}

	public static void agregarProducto(Carrito carritoActivo, Producto model, int cantidadProducto, int cantidadProductoAnterior) {
		
		int cantidadBodega = model.getCantidad();
		
		
		Set<ProductoCarrito> productosCarritoAux = carritoActivo.getProductosCarrito();
		boolean existe = false;

		Set<ProductoCarrito> productosCarrito = new HashSet<ProductoCarrito>(0);
		
		System.err.println("antes productos desde bs: " + productosCarritoAux.size());
		
		for(ProductoCarrito prodCarr : carritoActivo.getProductosCarrito()) {
			if(prodCarr.getId().getProducto().getNombre().equals(model.getNombre())) {
				cantidadProductoAnterior = prodCarr.getCantidad();
				prodCarr.setCantidad(cantidadProducto);
				existe = true;
			}
			productosCarrito.add(prodCarr);
		}
		
		if(!existe) {
			ProductoCarrito pc = new ProductoCarrito(model, carritoActivo);
			pc.setCantidad(cantidadProducto); 
			productosCarrito.add(pc);
			cantidadProductoAnterior = 0;
		}
		
		ProductoBs.verificarBodega(cantidadBodega, cantidadProducto, cantidadProductoAnterior);
		ProductoBs.realizarCambioBodega(model, cantidadBodega, cantidadProducto, cantidadProductoAnterior);
		
		carritoActivo.getProductosCarrito().clear();
		carritoActivo.setProductosCarrito(productosCarrito);
		SessionManager.set(carritoActivo, "carritoActivo");
		
		
		System.err.println("total productos desde bs: " + carritoActivo.getProductosCarrito().size());

	}

	public static Carrito consultarCarritoActivo() throws Exception {
		Carrito carritoActivo = new CarritoDAO().consultarCarritoActivo();
		if(carritoActivo == null) {
			
			carritoActivo = new Carrito();

			Estado estado = new EstadoDAO().consultarEstado(EstadoEnum.consultarIdEstado(EstadoEnum.Estado.PENDIENTE));
			carritoActivo.setEstado(estado);

			Usuario usuarioActivo = SessionManager.consultarUsuarioActivo();
			Usuario usuario = new UsuarioDAO().consultarUsuarioCURP(usuarioActivo.getCurp());
			carritoActivo.setUsuario(usuario);
			
			new CarritoDAO().registrarCarrito(carritoActivo);
			
		}
		
		SessionManager.set(carritoActivo, "carritoActivo");
		
		return carritoActivo;
	}

	public static void eliminarProducto(int idProducto) throws Exception {
		Producto model = new ProductoDAO().consultarProducto(idProducto);
		
		Carrito carritoActivo = SessionManager.consultarCarritoActivo();
		int cantidadProductoAnterior = 0;
		Set<ProductoCarrito> productosCarritoAux = carritoActivo.getProductosCarrito();

		Set<ProductoCarrito> productosCarrito = new HashSet<ProductoCarrito>(0);
		
		System.err.println("antes productos desde bs: " + productosCarritoAux.size());
		
		for(ProductoCarrito prodCarr : carritoActivo.getProductosCarrito()) {
			if(prodCarr.getId().getProducto().getNombre().equals("")) {
				cantidadProductoAnterior = prodCarr.getCantidad();
			}
			productosCarrito.add(prodCarr);
		}
		
		ProductoBs.realizarCambioBodega(model, model.getCantidad(), 0, cantidadProductoAnterior);
		
		carritoActivo.getProductosCarrito().clear();
		carritoActivo.setProductosCarrito(productosCarrito);
		SessionManager.set(carritoActivo, "carritoActivo");
		
		
		System.err.println("total productos desde bs: " + carritoActivo.getProductosCarrito().size());
		
	}
}
