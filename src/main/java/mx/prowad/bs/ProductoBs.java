package mx.prowad.bs;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.JDBCException;

import mx.prowad.dao.AtributoDAO;
import mx.prowad.dao.CategoriaDAO;
import mx.prowad.dao.ProductoDAO;
import mx.prowad.dao.TiendaDAO;
import mx.prowad.model.Atributo;
import mx.prowad.model.Categoria;
import mx.prowad.model.CategoriaAtributo;
import mx.prowad.model.Producto;
import mx.prowad.model.ProductoAtributo;
import mx.prowad.model.ProductoAtributoId;
import mx.prowad.model.ProductoCategoria;
import mx.prowad.model.Tienda;
import mx.prowad.model.Usuario;
import mx.prowad.util.PROWADException;

public class ProductoBs {
	public static Producto consultarProducto(int id) {
		Producto producto = null;
		producto = new ProductoDAO().consultarProducto(id);
		if (producto == null) {
			throw new PROWADException("No se puede consultar el producto.",
					"MSG13");
		}
		return producto;
	}

	public static List<Producto> consultarProductos() {
		List<Producto> productos = new ProductoDAO().consultarProductos();
		if (productos == null) {
			throw new PROWADException(
					"No se pueden consultar los productos.",
					"MSG13");
		}
		return productos;
	}

	public static void registrarProducto(Producto model) throws Exception{
		validar(model);
		new ProductoDAO().registrarProducto(model);
	}

	private static void validar(Producto model) {
		//Faltan validaciones
	}

	public static void eliminarProducto(Producto model) throws Exception {
		try {
			new ProductoDAO().eliminarProducto(model);
		} catch (JDBCException je) {
			if(je.getErrorCode() == 1451) {
				throw new PROWADException(
						"No se puede eliminar el producto.",
						"MSG14");
			}
			System.out.println("ERROR CODE " + je.getErrorCode());
			je.printStackTrace();
			throw new Exception();
		}
		
	}

	public static void modificarProducto(Producto model) {
		validar(model);
		new ProductoDAO().modificarProducto(model);
		
	}

	public static List<Categoria> consultarCategorias(Producto model) {
		List<Categoria> categorias = new ArrayList<Categoria>();
		for(ProductoCategoria pc : model.getProductosCategoria()) {
			Categoria categoria = pc.getId().getCategoria();
			categorias.add(categoria);
		}
		return categorias;
	}
	
	public static void agregarTienda(Producto model, int idTienda) {
		Tienda tienda = new TiendaDAO().consultarTienda(idTienda);
		model.setTienda(tienda);
		
	}

	public static void agregarCategorias(Producto model,
			List<Categoria> listCategorias) {
		Set<ProductoCategoria> productosCategoria = new HashSet<ProductoCategoria>(0);
		for(Categoria ca : listCategorias) {
			Categoria categoriaBD = new CategoriaDAO().consultarCategoria(ca.getNombre());
			ProductoCategoria pc = new ProductoCategoria(model, categoriaBD);
			productosCategoria.add(pc);
		}
		model.getProductosCategoria().clear();
		model.setProductosCategoria(productosCategoria);
		
	}
	
	public static void agregarAtributos(Producto model,
			List<ProductoAtributo> listProductoAtributo) throws ParseException {
		Set<String> atributos = new HashSet<String>(0);
		Set<ProductoAtributo> productosAtributo = new HashSet<ProductoAtributo>(0);
		
		for(ProductoAtributo pa : listProductoAtributo) {
			Atributo atributo = new AtributoDAO().consultarAtributo(pa.getId().getAtributo().getNombre());
			if(atributos.add(atributo.getNombre())) {
				ProductoAtributoId paid = new ProductoAtributoId(model, atributo);
				pa.setId(paid);
				if(pa.getValorFecha() != null) {
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					Date d = sdf.parse(pa.getValorFecha().toString());
					pa.setValorFecha(d);
				}
				productosAtributo.add(pa);
			}
		}
		model.getProductosAtributo().clear();
		model.setProductosAtributo(productosAtributo);
		
	}

	@SuppressWarnings("unchecked")
	public static List<ProductoAtributo> consultarAtributos(Producto model) {
		return (List<ProductoAtributo>) model.getProductosAtributo();
	}
}
