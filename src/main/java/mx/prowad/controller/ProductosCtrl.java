package mx.prowad.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import mx.prowad.bs.CategoriaBs;
import mx.prowad.bs.ProductoBs;
import mx.prowad.model.Atributo;
import mx.prowad.model.Categoria;
import mx.prowad.model.CategoriaAtributo;
import mx.prowad.model.Producto;
import mx.prowad.model.ProductoAtributo;
import mx.prowad.model.ProductoAtributoId;
import mx.prowad.util.*;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ModelDriven;

@ResultPath("/content/almacen/")
@Results({ @Result(name = ActionSupportPROWAD.SUCCESS, type = "redirectAction", params = {
		"actionName", "productos" }),
		@Result(name = "atributos", type = "json", params = {
				"root",
				"listAtributos" }),
})
public class ProductosCtrl extends ActionSupportPROWAD implements
ModelDriven<Producto>, SessionAware {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<String, Object> userSession;
	private Producto model;
	private List<Producto> productos;
	private Integer idSel;
	private String jsonCategoriasBD;
	private String jsonCategoriasModel;
	private List<Atributo> listAtributos;
	private String nombreCategoria;
	private String jsonProductoAtributo;
	
	public String index() throws Exception {
		try {
			buscarCatalogos();
			productos = ProductoBs.consultarProductos();
			@SuppressWarnings("unchecked")
			Collection<String> msjs = (Collection<String>) SessionManager
					.get("mensajesAccion");
			this.setActionMessages(msjs);
			SessionManager.delete("mensajesAccion");

		} catch (PROWADException pe) {
			ErrorManager.agregaMensajeError(this, pe);
		} catch (Exception e) {
			e.printStackTrace();
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
			resultado = index();
		}
		return resultado;
	}

	public String create() throws Exception {
		String resultado = null;
		try {
			System.out.println("jsonCategoriasModel: " + jsonCategoriasModel);
			System.out.println("jsonProductoAtributo: " + jsonProductoAtributo);
			List<Categoria> listCategorias = JsonUtil.mapJSONToArrayList(jsonCategoriasModel, Categoria.class);
			List<ProductoAtributo> listProductoAtributo = JsonUtil.mapJSONToArrayListDate(jsonProductoAtributo, ProductoAtributo.class, "dd/MM/yyyy");
					
			ProductoBs.agregarCategorias(model, listCategorias);
			ProductoBs.agregarAtributos(model, listProductoAtributo);
			ProductoBs.agregarTienda(model, 1);
			ProductoBs.registrarProducto(model);
			resultado = SUCCESS;
			
			addActionMessage(getText("MSG1", new String[] { "El",
					"Producto", "registrado" }));
			SessionManager.set(this.getActionMessages(), "mensajesAccion");
		} catch (PROWADValidacionException pve) {
			ErrorManager.agregaMensajeError(this, pve);
			resultado = editNew();
		} catch (PROWADException pe) {
			ErrorManager.agregaMensajeError(this, pe);
			resultado = index();
		} catch (Exception e) {
			ErrorManager.agregaMensajeError(this, e);
			resultado = index();
		}
		return resultado;
	}
	
	public String edit() throws Exception {

		String resultado = null;
		try {
			buscarCatalogos();
			prepararVista();
			resultado = EDIT;
		} catch (PROWADException pe) {
			ErrorManager.agregaMensajeError(this, pe);
			resultado = index();
		} catch (Exception e) {
			ErrorManager.agregaMensajeError(this, e);
			resultado = index();
		}
		return resultado;
	}

	public String update() throws Exception {
		String resultado = null;
		try {
			System.out.println("jsonCategoriasModel: " + jsonCategoriasModel);
			System.out.println("jsonProductoAtributo: " + jsonProductoAtributo);
			List<Categoria> listCategorias = JsonUtil.mapJSONToArrayList(jsonCategoriasModel, Categoria.class);
			List<ProductoAtributo> listProductoAtributo = JsonUtil.mapJSONToArrayListDate(jsonProductoAtributo, ProductoAtributo.class, "dd/MM/yyyy");
					
			ProductoBs.agregarCategorias(model, listCategorias);
			ProductoBs.agregarAtributos(model, listProductoAtributo);
			ProductoBs.modificarProducto(model);
			resultado = SUCCESS;
			
			addActionMessage(getText("MSG1", new String[] { "El",
					"Producto", "modificado" }));
			SessionManager.set(this.getActionMessages(), "mensajesAccion");
		} catch (PROWADValidacionException pve) {
			ErrorManager.agregaMensajeError(this, pve);
			resultado = edit();
		} catch (PROWADException pe) {
			ErrorManager.agregaMensajeError(this, pe);
			resultado = index();
		} catch (Exception e) {
			ErrorManager.agregaMensajeError(this, e);
			resultado = index();
		}
		return resultado;
	}
	
	public String destroy() throws Exception {
		String resultado = null;
		try {
			ProductoBs.eliminarProducto(model);
			resultado = SUCCESS;
			addActionMessage(getText("MSG1", new String[] { "El",
					"Producto", "eliminado" }));
			SessionManager.set(this.getActionMessages(), "mensajesAccion");
		} catch (PROWADException pe) {
			ErrorManager.agregaMensajeError(this, pe);
			resultado = index();
		} catch (Exception e) {
			ErrorManager.agregaMensajeError(this, e);
			resultado = index();
		}
		return resultado;
	}
	
	private void buscarCatalogos() {
		List<Categoria> categoriasBDAux = CategoriaBs.consultarCategorias();
		List<Categoria> categoriasBD = new ArrayList<Categoria>();
		for(Categoria cat : categoriasBDAux) {
			Categoria categoria = new Categoria();
			categoria.setId(cat.getId());
			categoria.setNombre(cat.getNombre());
			categoriasBD.add(categoria);
		}
		jsonCategoriasBD = JsonUtil.mapListToJSON(categoriasBD);
		
	}
	
	private void prepararVista() {
		List<Categoria> categoriasModelAux = ProductoBs.consultarCategorias(model);
		List<Categoria> categoriasModel = new ArrayList<Categoria>();
		for(Categoria cat : categoriasModelAux) {
			Categoria categoria = new Categoria();
			categoria.setId(cat.getId());
			categoria.setNombre(cat.getNombre());
			
			Set<CategoriaAtributo> catAtr = new HashSet<CategoriaAtributo>(0);
			
			for(CategoriaAtributo ca : cat.getCategoriasAtributo()) {
				Atributo atributo = new Atributo();
				atributo.setNombre(ca.getId().getAtributo().getNombre());
				atributo.setTipoDato(ca.getId().getAtributo().getTipoDato());
				CategoriaAtributo categoriaAtributo = new CategoriaAtributo(null, atributo);
				catAtr.add(categoriaAtributo);
			}
			
			categoria.setCategoriasAtributo(catAtr);
			categoriasModel.add(categoria);
		}
		jsonCategoriasModel = JsonUtil.mapListToJSON(categoriasModel);
		
		List<ProductoAtributo> productoAtributoModelAux = new ArrayList<ProductoAtributo>();
		productoAtributoModelAux.addAll(model.getProductosAtributo());
		
		List<ProductoAtributo> productoAtributoModel = new ArrayList<ProductoAtributo>();
		System.out.println("ProductosAtributo size: " + productoAtributoModelAux.size());
		for(ProductoAtributo pa : productoAtributoModelAux) {
			Producto producto = new Producto();
			producto.setNombre(pa.getId().getProducto().getNombre());
			
			Atributo atributo = new Atributo();
			atributo.setNombre(pa.getId().getAtributo().getNombre());
			atributo.setTipoDato(pa.getId().getAtributo().getTipoDato());
			
			ProductoAtributoId paid = new ProductoAtributoId(producto, atributo);
			pa.setId(paid);
			
			productoAtributoModel.add(pa);
		}
		jsonProductoAtributo = JsonUtil.mapListToJSON(productoAtributoModel);
		
	}

	public String verificarAtributosCategoria() {
		Categoria categoria = CategoriaBs.consultarCategoria(nombreCategoria);
		List<Atributo> listAtributosAux = CategoriaBs.consultarAtributos(categoria);
		listAtributos = new ArrayList<Atributo>();
		for(Atributo atr : listAtributosAux) {
			Atributo atributo = new Atributo();
			atributo.setId(atr.getId());
			atributo.setNombre(atr.getNombre());
			atributo.setTipoDato(atr.getTipoDato());
			listAtributos.add(atributo);
		}
		return "atributos";
	}
	
	public Map<String, Object> getUserSession() {
		return userSession;
	}
	public void setUserSession(Map<String, Object> userSession) {
		this.userSession = userSession;
	}
	public Producto getModel() {
		return (model == null) ? model = new Producto() : model;
	}
	public void setModel(Producto model) {
		this.model = model;
	}
	public List<Producto> getProductos() {
		return productos;
	}
	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}
	public Integer getIdSel() {
		return idSel;
	}
	public void setIdSel(Integer idSel) {
		this.idSel = idSel;
		if(idSel != null && idSel != 0) {
			model = ProductoBs.consultarProducto(idSel);
		}
	}
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		
	}

	public String getJsonCategoriasBD() {
		return jsonCategoriasBD;
	}

	public void setJsonCategoriasBD(String jsonCategoriasBD) {
		this.jsonCategoriasBD = jsonCategoriasBD;
	}

	public String getJsonCategoriasModel() {
		return jsonCategoriasModel;
	}

	public void setJsonCategoriasModel(String jsonCategoriasModel) {
		this.jsonCategoriasModel = jsonCategoriasModel;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<Atributo> getListAtributos() {
		return listAtributos;
	}

	public void setListAtributos(List<Atributo> listAtributos) {
		this.listAtributos = listAtributos;
	}

	public String getNombreCategoria() {
		return nombreCategoria;
	}

	public void setNombreCategoria(String nombreCategoria) {
		this.nombreCategoria = nombreCategoria;
	}

	public String getJsonProductoAtributo() {
		return jsonProductoAtributo;
	}

	public void setJsonProductoAtributo(String jsonProductoAtributo) {
		this.jsonProductoAtributo = jsonProductoAtributo;
	}

	
	
}
