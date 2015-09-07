package mx.prowad.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import mx.prowad.bs.EstadoEnum;
import mx.prowad.bs.ProductoBs;
import mx.prowad.bs.CarritoBs;
import mx.prowad.dao.EstadoDAO;
import mx.prowad.model.Estado;
import mx.prowad.model.Producto;
import mx.prowad.model.Carrito;
import mx.prowad.model.ProductoCarrito;
import mx.prowad.model.TipoDato;
import mx.prowad.util.ActionSupportPROWAD;
import mx.prowad.util.ErrorManager;
import mx.prowad.util.JsonUtil;
import mx.prowad.util.PROWADException;
import mx.prowad.util.PROWADValidacionException;
import mx.prowad.util.SessionManager;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ModelDriven;

@ResultPath("/content/cliente/")
@Results({ @Result(name = ActionSupportPROWAD.SUCCESS, type = "redirectAction", params = {
		"actionName", "carritos" })
})
public class CarritosCtrl extends ActionSupportPROWAD implements
ModelDriven<Carrito>, SessionAware {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<String, Object> userSession;
	private Carrito model;
	private List<Carrito> carritos;
	private Integer idSel;
	private String jsonProductosBDTabla;
	private String jsonProductosCarritoModelTabla;
	private int idProducto;
	
	public String index() throws Exception {
		try {			
			carritos = CarritoBs.consultarCarritosUsuario();
			System.err.println("Carritos size: " + carritos.size());
			@SuppressWarnings("unchecked")
			Collection<String> msjs = (Collection<String>) SessionManager
					.get("mensajesAccion");
			this.setActionMessages(msjs);
			SessionManager.delete("mensajesAccion");

		} catch (PROWADException pe) {
			ErrorManager.agregaMensajeError(this, pe);
			return ACCESS;
		} catch (Exception e) {
			ErrorManager.agregaMensajeError(this, e);
			return ACCESS;
		}
		return INDEX;
	}

	public String editNew() throws Exception {

		String resultado = null;
		try {
			buscarCatalogos();
			resultado = EDITNEW;
		} catch (PROWADException pe) {
			ErrorManager.agregaMensajeError(this, pe);
			resultado = index();
		} catch (Exception e) {
			ErrorManager.agregaMensajeError(this, e);
			return ACCESS;
		}
		return resultado;
	}

	public String create() throws Exception {
		String resultado = null;
		try {
			System.out.println("jsonProductosModel: " + jsonProductosCarritoModelTabla);
			List<Producto> productos = JsonUtil.mapJSONToArrayList(jsonProductosCarritoModelTabla, Producto.class);
			CarritoBs.agregarProductos(model, productos);
			CarritoBs.registrarCarrito(model);
			resultado = SUCCESS;
			
			addActionMessage(getText("MSG1", new String[] { "El",
					"Carrito", "registrado" }));
			SessionManager.set(this.getActionMessages(), "mensajesAccion");
		} catch (PROWADValidacionException pve) {
			ErrorManager.agregaMensajeError(this, pve);
			resultado = editNew();
		} catch (PROWADException pe) {
			ErrorManager.agregaMensajeError(this, pe);
			resultado = index();
		} catch (Exception e) {
			ErrorManager.agregaMensajeError(this, e);
			return ACCESS;
		}
		return resultado;
	}
	
	public String edit() throws Exception {

		String resultado = null;
		try {
			buscarCatalogos();
			prepararVista();
			resultado = EDIT;
			
			@SuppressWarnings("unchecked")
			Collection<String> msjs = (Collection<String>) SessionManager
					.get("mensajesAccion");
			this.setActionMessages(msjs);
			SessionManager.delete("mensajesAccion");
			@SuppressWarnings("unchecked")
			Collection<String> msjsError = (Collection<String>) SessionManager
					.get("mensajesError");
			this.setActionErrors(msjsError);
			SessionManager.delete("mensajesError");
		} catch (PROWADException pe) {
			ErrorManager.agregaMensajeError(this, pe);
			resultado = index();
		} catch (Exception e) {
			ErrorManager.agregaMensajeError(this, e);
			return ACCESS;
		}
		return resultado;
	}
	
	public String show() throws Exception {

		String resultado = null;
		try {
			buscarCatalogos();
			prepararVista();
			resultado = SHOW;
			
			@SuppressWarnings("unchecked")
			Collection<String> msjs = (Collection<String>) SessionManager
					.get("mensajesAccion");
			this.setActionMessages(msjs);
			SessionManager.delete("mensajesAccion");
			@SuppressWarnings("unchecked")
			Collection<String> msjsError = (Collection<String>) SessionManager
					.get("mensajesError");
			this.setActionErrors(msjsError);
			SessionManager.delete("mensajesError");
		} catch (PROWADException pe) {
			ErrorManager.agregaMensajeError(this, pe);
			resultado = index();
		} catch (Exception e) {
			ErrorManager.agregaMensajeError(this, e);
			return ACCESS;
		}
		return resultado;
	}

	public String update() throws Exception {
		String resultado = null;
		try {
			List<Producto> productos = JsonUtil.mapJSONToArrayList(jsonProductosCarritoModelTabla, Producto.class);
			CarritoBs.agregarProductos(model, productos);
			CarritoBs.modificarCarrito(model);
			resultado = SUCCESS;
			
			addActionMessage(getText("MSG1", new String[] { "El",
					"Carrito", "modificado" }));
			SessionManager.set(this.getActionMessages(), "mensajesAccion");
		} catch (PROWADValidacionException pve) {
			ErrorManager.agregaMensajeError(this, pve);
			resultado = edit();
		} catch (PROWADException pe) {
			ErrorManager.agregaMensajeError(this, pe);
			resultado = index();
		} catch (Exception e) {
			e.printStackTrace();
			ErrorManager.agregaMensajeError(this, e);
			resultado = index();
		}
		return resultado;
	}
	
	public String destroy() throws Exception {
		String resultado = null;
		try {
			
			CarritoBs.eliminarProducto(idProducto);
			CarritoBs.modificarCarrito(model);
			resultado = edit();
			addActionMessage(getText("MSG1", new String[] { "El",
					"Producto", "eliminado" }));
			SessionManager.set(this.getActionMessages(), "mensajesAccion");
		} catch (PROWADException pe) {
			ErrorManager.agregaMensajeError(this, pe);
			resultado = edit();
		} catch (Exception e) {
			ErrorManager.agregaMensajeError(this, e);
			resultado = edit();
		}
		return resultado;
	}
	
	private void buscarCatalogos() {
		List<Producto> productosBDAux = ProductoBs.consultarProductos();
		List<Producto> productosBD = new ArrayList<Producto>();
		for(Producto atr : productosBDAux) {
			Producto producto = new Producto();
			producto.setId(atr.getId());
			producto.setNombre(atr.getNombre());
			producto.setCantidad(atr.getCantidad());
			producto.setPrecio(atr.getPrecio());
			productosBD.add(producto);
		}
		jsonProductosBDTabla = JsonUtil.mapListToJSON(productosBD);
		
	}
	
	private void prepararVista() {
		Set<ProductoCarrito> productosModelAux = model.getProductosCarrito();
		List<ProductoCarrito> productosCarritoModel = new ArrayList<ProductoCarrito>();
		for(ProductoCarrito pc : productosModelAux) {
			Producto producto = new Producto();
			producto.setId(pc.getId().getProducto().getId());
			producto.setNombre(pc.getId().getProducto().getNombre());
			producto.setPrecio(pc.getId().getProducto().getPrecio());
			producto.setCantidad(pc.getId().getProducto().getCantidad());
			ProductoCarrito proCar = new ProductoCarrito(producto, null);
			proCar.setCantidad(pc.getCantidad());
			
			productosCarritoModel.add(proCar);			
			
		}
		jsonProductosCarritoModelTabla = JsonUtil.mapListToJSON(productosCarritoModel);
		
	}
	
	public String comprarCarrito() throws Exception {
		String resultado = null;
		try {
			Estado estado = new EstadoDAO().consultarEstado(EstadoEnum.consultarIdEstado(EstadoEnum.Estado.PAGADO));
			model.setEstado(estado);
			
			model.setFechaCompra(new Date());
			
			CarritoBs.modificarCarrito(model);
			resultado = SUCCESS;
			
			addActionMessage(getText("MSG1", new String[] { "El",
					"Carrito", "comprado" }));
			CarritoBs.consultarCarritoActivo();
			SessionManager.set(this.getActionMessages(), "mensajesAccion");
		} catch (PROWADValidacionException pve) {
			ErrorManager.agregaMensajeError(this, pve);
			resultado = edit();
		} catch (PROWADException pe) {
			ErrorManager.agregaMensajeError(this, pe);
			resultado = index();
		} catch (Exception e) {
			e.printStackTrace();
			ErrorManager.agregaMensajeError(this, e);
			resultado = index();
		}
		return resultado;
	}
	
	public Map<String, Object> getUserSession() {
		return userSession;
	}
	public void setUserSession(Map<String, Object> userSession) {
		this.userSession = userSession;
	}
	public Carrito getModel() {
		return (model == null) ? model = new Carrito() : model;
	}
	public void setModel(Carrito model) {
		this.model = model;
	}
	public List<Carrito> getCarritos() {
		return carritos;
	}
	public void setCarritos(List<Carrito> carritos) {
		this.carritos = carritos;
	}
	public Integer getIdSel() {
		return idSel;
	}
	public void setIdSel(Integer idSel) {
		this.idSel = idSel;
		if(idSel != null && idSel != 0) {
			model = CarritoBs.consultarCarrito(idSel);
		}
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		
	}

	public String getJsonProductosBDTabla() {
		return jsonProductosBDTabla;
	}

	public void setJsonProductosBDTabla(String jsonProductosBDTabla) {
		this.jsonProductosBDTabla = jsonProductosBDTabla;
	}

	public String getJsonProductosCarritoModelTabla() {
		return jsonProductosCarritoModelTabla;
	}

	public void setJsonProductosCarritoModelTabla(
			String jsonProductosCarritoModelTabla) {
		this.jsonProductosCarritoModelTabla = jsonProductosCarritoModelTabla;
	}

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

}
