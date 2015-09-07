package mx.prowad.controller;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import mx.prowad.bs.RolEnum;
import mx.prowad.bs.UsuarioBs;
import mx.prowad.model.Usuario;
import mx.prowad.util.ActionSupportPROWAD;
import mx.prowad.util.ErrorManager;
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
		"actionName", "personal-almacen" })
})
public class PersonalAlmacenCtrl extends ActionSupportPROWAD implements
ModelDriven<Usuario>, SessionAware{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<String, Object> userSession;
	private Usuario model;
	private List<Usuario> personalAlmacen;
	private String idSel;
	
	public String index() throws Exception {
		try {
			personalAlmacen = UsuarioBs.consultarPersonalAlmacen();
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
			UsuarioBs.agregarTienda(model, 1);
			UsuarioBs.agregarRol(model, RolEnum.Rol.ALMACENISTA);
			UsuarioBs.registrarUsuario(model);
			resultado = SUCCESS;
			
			addActionMessage(getText("MSG1", new String[] { "El",
					"Almacenista", "registrado" }));
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
			UsuarioBs.agregarTienda(model, 1);
			UsuarioBs.agregarRol(model, RolEnum.Rol.ALMACENISTA);
			UsuarioBs.modificarUsuario(model);
			resultado = SUCCESS;
			
			addActionMessage(getText("MSG1", new String[] { "El",
					"Almacenista", "modificado" }));
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
			UsuarioBs.eliminarUsuario(model);
			resultado = SUCCESS;
			addActionMessage(getText("MSG1", new String[] { "El",
					"Almacenista", "eliminado" }));
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

	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		
	}

	public Map<String, Object> getUserSession() {
		return userSession;
	}

	public void setUserSession(Map<String, Object> userSession) {
		this.userSession = userSession;
	}

	public Usuario getModel() {
		return (model == null) ? model = new Usuario() : model;
	}

	public void setModel(Usuario model) {
		this.model = model;
	}

	public List<Usuario> getPersonalAlmacen() {
		return personalAlmacen;
	}

	public void setPersonalAlmacen(List<Usuario> personalAlmacen) {
		this.personalAlmacen = personalAlmacen;
	}

	public String getIdSel() {
		return idSel;
	}

	public void setIdSel(String idSel) {
		this.idSel = idSel;
		if(idSel != null && !idSel.equals("")) {
			model = UsuarioBs.consultarUsuario(idSel);
		}
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
