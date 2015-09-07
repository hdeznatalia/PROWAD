package mx.prowad.util;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.NoSuchElementException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import mx.prowad.bs.UsuarioBs;
import mx.prowad.model.Carrito;
import mx.prowad.model.Usuario;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

public class SessionManager {
	/**
	 * Método proxy que regresa el objeto que se encuentra en la sesión con la
	 * llave especificada en nombre.
	 * 
	 * @param nombre
	 *            llave del objeto
	 * @return objeto que se obtuvo de la sesión
	 */
	public static Object get(String nombre) {
		return ActionContext.getContext().getSession().get(nombre);
	}

	/**
	 * Método proxy que sube un objeto a la sesión con cuya llave se especifica
	 * en nombre
	 * 
	 * @param o
	 *            objeto a subir a la sesión
	 * @param nombre
	 *            llave del objeto
	 * @return objeto que se agregó a la sesión
	 */
	public static Object set(Object o, String nombre) {
		return ActionContext.getContext().getSession().put(nombre,o);
	}

	/**
	 * Método proxy que limpia la sesión.
	 */
	public static void clear() {
		ActionContext.getContext().getSession().clear();
	}

	/**
	 * Método proxy que elimina un objeto de la sesión cuya llave se especifica
	 * en nombre
	 * 
	 * @param nombre
	 *            llave del objeto
	 * @return objeto que se eliminó de la sesión
	 */
	public static Object delete(String nombre) {
		return ActionContext.getContext().getSession().remove(nombre);
	}

	/**
	 * Método proxy que verifica si la sesión está limpia
	 * 
	 * @return false si la sesión no está limpia
	 */
	public boolean isEmpty() {
		return ActionContext.getContext().getSession().isEmpty();
	}
	
	
	
	public static boolean isLogged() {
		HttpSession session = ServletActionContext.getRequest().getSession(false); 
		boolean logged = false;
		if (session != null) {
			if (session.getAttribute("login") != null) {
				logged = (Boolean) session.getAttribute("login");
				return logged;
			}
		} 
		return false;
	}

	public static void pushURL(HttpServletRequest request) throws Exception{
		@SuppressWarnings("unchecked")
		Deque<String> URLStack = (Deque<String>) SessionManager.get("URLStack");
		if(URLStack == null) {
			URLStack = new ArrayDeque<String>();
		}
		
		//Pruebas
//		System.out.println("URL****************"+request.getRequestURL().toString());  
//		System.out.println("URI****************"+request.getRequestURI().toString());
//		System.out.println("ContextPath****************"+request.getContextPath().toString());

		String url = request.getHeader("Referer");
		if(url == null) {
//			System.out.println("url null");
			url = request.getRequestURL().toString();
		}
//		System.out.println("push url: " + url);
		URLStack.push(url);
		SessionManager.set(URLStack, "URLStack");
	}

	public static String popURL(HttpServletRequest request) throws Exception{		
		@SuppressWarnings("unchecked")
		Deque<String> URLStack = (Deque<String>) SessionManager.get("URLStack");
		String urlPrev = null;
		try {
		urlPrev = URLStack.pop();
		} catch (NoSuchElementException nse) {
			System.err.println("No hay url en la pila");
			urlPrev = request.getContextPath();
		} catch (NullPointerException npe) {
			System.err.println("No existe URLStack en la pila");
			urlPrev = request.getContextPath();
		}
		SessionManager.set(URLStack, "URLStack");
//		System.out.println("pop url: " + urlPrev);
		return urlPrev;
	}

	public static Carrito consultarCarritoActivo() throws Exception {
		Carrito carrito = (Carrito) SessionManager.get("carritoActivo");
		return carrito;
	}

	public static Usuario consultarUsuarioActivo() throws Exception {
		HttpSession session = ServletActionContext.getRequest().getSession(
				false);
		Usuario usuario = null;
		String curp = null;
		if (session != null && session.getAttribute("curp") != null) {
			curp = (String) session.getAttribute("curp");
		}

		usuario = UsuarioBs.consultarUsuario(curp);

		if (usuario == null) {
			throw new PROWADException("No se puede consultar el usuario",
					"MSG16");
		}
		return usuario;
	}

}
