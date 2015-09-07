package mx.prowad.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import mx.prowad.bs.AtributoBs;
import mx.prowad.bs.CategoriaBs;
import mx.prowad.model.Atributo;
import mx.prowad.model.Categoria;
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

@ResultPath("/content/administrador/")
@Results({ @Result(name = ActionSupportPROWAD.SUCCESS, type = "redirectAction", params = {
		"actionName", "categorias" })
})
public class CategoriasCtrl extends ActionSupportPROWAD implements
ModelDriven<Categoria>, SessionAware {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<String, Object> userSession;
	private Categoria model;
	private List<Categoria> categorias;
	private Integer idSel;
	private String jsonAtributosBDTabla;
	private String jsonAtributosModelTabla;
	
	public String index() throws Exception {
		try {
			categorias = CategoriaBs.consultarCategorias();
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
			resultado = index();
		}
		return resultado;
	}

	public String create() throws Exception {
		String resultado = null;
		try {
			System.out.println("jsonAtributosModel: " + jsonAtributosModelTabla);
			List<Atributo> atributos = JsonUtil.mapJSONToArrayList(jsonAtributosModelTabla, Atributo.class);
			CategoriaBs.agregarAtributos(model, atributos);
			System.out.println("model.getCategoriasAtributo().size: " + model.getCategoriasAtributo().size());
			CategoriaBs.registrarCategoria(model);
			resultado = SUCCESS;
			
			addActionMessage(getText("MSG1", new String[] { "La",
					"Categoría", "registrada" }));
			SessionManager.set(this.getActionMessages(), "mensajesAccion");
		} catch (PROWADValidacionException pve) {
			ErrorManager.agregaMensajeError(this, pve);
			resultado = editNew();
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
			List<Atributo> atributos = JsonUtil.mapJSONToArrayList(jsonAtributosModelTabla, Atributo.class);
			CategoriaBs.agregarAtributos(model, atributos);
			CategoriaBs.modificarCategoria(model);
			resultado = SUCCESS;
			
			addActionMessage(getText("MSG1", new String[] { "La",
					"Categoría", "modificada" }));
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
			CategoriaBs.eliminarCategoria(model);
			resultado = SUCCESS;
			addActionMessage(getText("MSG1", new String[] { "La",
					"Categoría", "eliminada" }));
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
		List<Atributo> atributosBDAux = AtributoBs.consultarAtributos();
		List<Atributo> atributosBD = new ArrayList<Atributo>();
		for(Atributo atr : atributosBDAux) {
			Atributo atributo = new Atributo();
			atributo.setId(atr.getId());
			atributo.setNombre(atr.getNombre());
			atributo.setTipoDato(atr.getTipoDato());
			atributosBD.add(atributo);
		}
		jsonAtributosBDTabla = JsonUtil.mapListToJSON(atributosBD);
		
	}
	
	private void prepararVista() {
		List<Atributo> atributosModelAux = CategoriaBs.consultarAtributos(model);
		List<Atributo> atributosModel = new ArrayList<Atributo>();
		for(Atributo atr : atributosModelAux) {
			Atributo atributo = new Atributo();
			atributo.setId(atr.getId());
			atributo.setNombre(atr.getNombre());
			atributo.setTipoDato(atr.getTipoDato());
			atributosModel.add(atributo);
		}
		jsonAtributosModelTabla = JsonUtil.mapListToJSON(atributosModel);
		
	}
	
	public Map<String, Object> getUserSession() {
		return userSession;
	}
	public void setUserSession(Map<String, Object> userSession) {
		this.userSession = userSession;
	}
	public Categoria getModel() {
		return (model == null) ? model = new Categoria() : model;
	}
	public void setModel(Categoria model) {
		this.model = model;
	}
	public List<Categoria> getCategorias() {
		return categorias;
	}
	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}
	public Integer getIdSel() {
		return idSel;
	}
	public void setIdSel(Integer idSel) {
		this.idSel = idSel;
		if(idSel != null && idSel != 0) {
			model = CategoriaBs.consultarCategoria(idSel);
		}
	}
	public String getJsonCategoriasTabla() {
		return jsonAtributosModelTabla;
	}
	public void setJsonCategoriasTabla(String jsonAtributosTabla) {
		this.jsonAtributosModelTabla = jsonAtributosTabla;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		
	}

	public String getJsonAtributosBDTabla() {
		return jsonAtributosBDTabla;
	}

	public void setJsonAtributosBDTabla(String jsonAtributosBDTabla) {
		this.jsonAtributosBDTabla = jsonAtributosBDTabla;
	}

	public String getJsonAtributosModelTabla() {
		return jsonAtributosModelTabla;
	}

	public void setJsonAtributosModelTabla(String jsonAtributosModelTabla) {
		this.jsonAtributosModelTabla = jsonAtributosModelTabla;
	}
	
	
	
}
