package mx.prowad.controller;

import java.util.Collection;
import java.util.Map;

import mx.prowad.bs.AccessBs;
import mx.prowad.bs.CarritoBs;
import mx.prowad.bs.UsuarioBs;
import mx.prowad.model.Usuario;
import mx.prowad.util.ActionSupportPROWAD;
import mx.prowad.util.ErrorManager;
import mx.prowad.util.PROWADException;
import mx.prowad.util.PROWADValidacionException;
import mx.prowad.util.SessionManager;

import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionContext;

@InterceptorRef(value="defaultStack")

@ResultPath("/content/")

@Results({
		@Result(name = "admin", type = "redirectAction", params = {
				"actionName", "atributos" }),
		@Result(name = "store", type = "redirectAction", params = {
				"actionName", "productos" }),
		@Result(name = "customer", type = "redirectAction", params = {
				"actionName", "productos-compra" }),
		@Result(name = "recover", type = "dispatcher", location = "recover.jsp")
		})
public class AccessCtrl extends ActionSupportPROWAD implements SessionAware {
	/** 
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<String, Object> userSession;
	private Usuario usuario;
	String resultado;
	private String userName;
	private String password;

	@SuppressWarnings("unchecked")
	public String index() {
		Collection<String> mensajes;
		Collection<String> mensajesError;
		
		resultado = INDEX;
			
		try {
			if (SessionManager.isLogged()) {
				usuario = SessionManager.consultarUsuarioActivo();
				if (UsuarioBs.esAdministrador(usuario)) {
					resultado = "admin";
				} else if (UsuarioBs.esCliente(usuario)) {
					resultado = "customer";
				} else if (UsuarioBs.esAlmacenista(usuario)){
					resultado = "store";
				}
			}
			mensajes = (Collection<String>) SessionManager.get("mensajesAccion");	
			mensajesError = (Collection<String>) SessionManager.get("mensajesError");	
			this.setActionMessages(mensajes);
			this.setActionErrors(mensajesError);
			SessionManager.delete("mensajesError");
			SessionManager.delete("mensajesAccion");

		} catch (PROWADException pe) {
			ErrorManager.agregaMensajeError(this, pe);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return resultado;
	}

	public String login() throws Exception {
		Map<String, Object> session = null;
		try {
			if (userSession != null) {
				userSession.clear();
			}
			usuario = AccessBs.verificarLogin(userName, password);
			session = ActionContext.getContext().getSession();
			session.put("login", true);
			session.put("curp", usuario.getCurp());
			setSession(session);
			if (UsuarioBs.esAdministrador(usuario)) {
				resultado = "admin";
			} else if (UsuarioBs.esCliente(usuario)) {
				CarritoBs.consultarCarritoActivo();
				resultado = "customer";
			} else if (UsuarioBs.esAlmacenista(usuario)){
				resultado = "store";
			}

		} catch (PROWADValidacionException pve) {
			ErrorManager.agregaMensajeError(this, pve);
			return index();
		} catch (PROWADException pe) {
			ErrorManager.agregaMensajeError(this, pe);
		} catch (Exception e) {
			ErrorManager.agregaMensajeError(this, e);
		}
		return resultado;
	}

	public String logout() {
		if (userSession != null) {
			userSession.clear();
		}
		return index();
	}

	public String recover() {
		String resultado = null;
		try {
			resultado = "recover";
		} catch (PROWADValidacionException pve) {
			ErrorManager.agregaMensajeError(this, pve);
			resultado = recover();
		} catch (PROWADException pe) {
			ErrorManager.agregaMensajeError(this, pe);
			resultado = index();
		} catch (Exception e) {
			ErrorManager.agregaMensajeError(this, e);
			resultado = index();
		}
		return resultado;
	}

	public String sendPassword() {
		String resultado = null;
		try {
			AccessBs.recuperarContrasenia(userName);
			resultado = INDEX;
			addActionMessage(getText("MSG32"));

			SessionManager.set(this.getActionMessages(), "mensajesAccion");

		} catch (PROWADValidacionException pve) {
			ErrorManager.agregaMensajeError(this, pve);
			resultado = recover();
		} catch (PROWADException pe) {
			ErrorManager.agregaMensajeError(this, pe);
			resultado = index();
		} catch (Exception e) {
			ErrorManager.agregaMensajeError(this, e);
			resultado = index();
		}
		return resultado;
	}
	
	public static String getMenu(String curp) throws Exception {
		System.out.println("curp: " + curp);
		if (UsuarioBs.esAdministrador(UsuarioBs.consultarUsuario(curp))) {
			return "administradorMenu";
		}
		if (UsuarioBs.esCliente(UsuarioBs.consultarUsuario(curp))) {
			return "clienteMenu";
		}
		if (UsuarioBs.esAlmacenista(UsuarioBs.consultarUsuario(curp))){
			return "almacenistaMenu";
		}
		return "error";
	}

	public void setSession(Map<String, Object> session) {
		this.userSession = session;
	}

	public Map<String, Object> getUserSession() {
		return userSession;
	}

	public void setUserSession(Map<String, Object> userSession) {
		this.userSession = userSession;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
