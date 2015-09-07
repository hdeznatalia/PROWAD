package mx.prowad.controller;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import mx.prowad.bs.AtributoBs;
import mx.prowad.model.Atributo;
import mx.prowad.model.TipoDato;
import mx.prowad.util.*;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ModelDriven;

@ResultPath("/content/administrador/")
@Results({ @Result(name = ActionSupportPROWAD.SUCCESS, type = "redirectAction", params = {
		"actionName", "atributos" })
})
public class AtributosCtrl extends ActionSupportPROWAD implements
ModelDriven<Atributo>, SessionAware {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<String, Object> userSession;
	private Atributo model;
	private List<Atributo> atributos;
	private Integer idSel;
	private int idTipoDato;
	private List<TipoDato> listTipoDato;
	
	public String index() throws Exception {
		try {
			atributos = AtributoBs.consultarAtributos();
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
			AtributoBs.agregarTipoDato(model, idTipoDato);
			AtributoBs.registrarAtributo(model);
			resultado = SUCCESS;
			
			addActionMessage(getText("MSG1", new String[] { "El",
					"Atributo", "registrado" }));
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
			AtributoBs.agregarTipoDato(model, idTipoDato);
			AtributoBs.modificarAtributo(model);
			resultado = SUCCESS;
			
			addActionMessage(getText("MSG1", new String[] { "El",
					"Atributo", "modificado" }));
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
			AtributoBs.eliminarAtributo(model);
			resultado = SUCCESS;
			addActionMessage(getText("MSG1", new String[] { "El",
					"Atributo", "eliminado" }));
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
		listTipoDato = AtributoBs.consultarTiposDato();
		
	}
	
	private void prepararVista() {
		this.idTipoDato = model.getTipoDato().getId();
		
	}

	
	public Map<String, Object> getUserSession() {
		return userSession;
	}
	public void setUserSession(Map<String, Object> userSession) {
		this.userSession = userSession;
	}
	public Atributo getModel() {
		return (model == null) ? model = new Atributo() : model;
	}
	public void setModel(Atributo model) {
		this.model = model;
	}
	public List<Atributo> getAtributos() {
		return atributos;
	}
	public void setAtributos(List<Atributo> atributos) {
		this.atributos = atributos;
	}
	public Integer getIdSel() {
		return idSel;
	}
	public void setIdSel(Integer idSel) {
		this.idSel = idSel;
		if(idSel != null && idSel != 0) {
			model = AtributoBs.consultarAtributo(idSel);
		}
	}
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		
	}

	public int getIdTipoDato() {
		return idTipoDato;
	}

	public void setIdTipoDato(int idTipoDato) {
		this.idTipoDato = idTipoDato;
	}

	public List<TipoDato> getListTipoDato() {
		return listTipoDato;
	}

	public void setListTipoDato(List<TipoDato> listTipoDato) {
		this.listTipoDato = listTipoDato;
	}
	
	
}
