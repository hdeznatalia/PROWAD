package mx.prowad.bs;

import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import mx.prowad.dao.UsuarioDAO;
import mx.prowad.model.Usuario;
import mx.prowad.util.Correo;
import mx.prowad.util.PROWADValidacionException;
import mx.prowad.util.Validador;


public class AccessBs {

	public static Usuario verificarLogin(String userName, String password) {
		List<Usuario> usuarios = null;
		if (Validador.esNuloOVacio(userName)) {
			throw new PROWADValidacionException(
					"El usuario no ingresó el correo electrónico", "MSG4", null,
					"userName");
		}
		
		if (Validador.esNuloOVacio(password)) {
			throw new PROWADValidacionException(
					"El usuario no ingresó la contraseña.", "MSG4", null,
					"password");
		}
		
		try {
			usuarios = new UsuarioDAO().findByCorreo(userName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (usuarios == null || usuarios.isEmpty() || !usuarios.get(0).getContrasena().equals(password)) {
			throw new PROWADValidacionException("Usuario no encontrado o contraseña incorrecta", "MSG31");
		}
		System.out.println("nombre: " + usuarios.get(0).getNombre());
		return usuarios.get(0);
		
	}

	public static boolean isLogged(Map<String, Object> userSession) {
		boolean logged = false;
		if (userSession != null) {
			if (userSession.get("login") != null) {
				logged = (Boolean) userSession.get("login");
				System.out.println(logged);
				return logged;
			}
		} 
		return false;
	}

	public static void recuperarContrasenia(String userName) throws AddressException, MessagingException {
		List<Usuario> usuarios = null;

		if (Validador.esNuloOVacio(userName)) {
			throw new PROWADValidacionException(
					"El usuario no ingresó el correo electrónico", "MSG4", null,
					"userName");
		}
		if (!Validador.esCorreo(userName)) {
			throw new PROWADValidacionException("Usuario no encontrado", "MSG33");

		}
		try {
			usuarios = new UsuarioDAO().findByCorreo(userName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (usuarios == null || usuarios.isEmpty()) {
			throw new PROWADValidacionException("Colaborador no encontrado", "MSG33");
		}
		Correo.enviarCorreo(usuarios.get(0), 1);
		
	}
	
	public static void verificarPermisos(String tmp, Usuario usuario) {
		
	}

}
