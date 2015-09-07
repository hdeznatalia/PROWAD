package mx.prowad.bs;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.JDBCException;

import mx.prowad.dao.TiendaDAO;
import mx.prowad.dao.UsuarioDAO;
import mx.prowad.model.Tienda;
import mx.prowad.model.Usuario;
import mx.prowad.util.PROWADException;
import mx.prowad.dao.RolDAO;
import mx.prowad.model.Rol;

public class UsuarioBs {
	public static Usuario consultarUsuario(String curp) {
		Usuario usuario = null;
		usuario = new UsuarioDAO().consultarUsuarioCURP(curp);
		if (usuario == null) {
			throw new PROWADException("No se puede consultar el usuario.",
					"MSG13");
		}
		return usuario;
	}

	public static List<Usuario> consultarUsuarios() {
		List<Usuario> usuarios = new UsuarioDAO().consultarUsuarios();
		if (usuarios == null) {
			throw new PROWADException(
					"No se pueden consultar los usuarios.",
					"MSG13");
		}
		return usuarios;
	}

	public static void registrarUsuario(Usuario model) throws Exception{
		validar(model);
		new UsuarioDAO().registrarUsuario(model);
	}

	private static void validar(Usuario model) {
		//Faltan validaciones
	}

	public static void eliminarUsuario(Usuario model) throws Exception {
		try {
			new UsuarioDAO().eliminarUsuario(model);
		} catch (JDBCException je) {
			if(je.getErrorCode() == 1451) {
				throw new PROWADException(
						"No se puede eliminar el usuario.",
						"MSG14");
			}
			System.out.println("ERROR CODE " + je.getErrorCode());
			je.printStackTrace();
			throw new Exception();
		}
		
	}

	public static void modificarUsuario(Usuario model) {
		validar(model);
		new UsuarioDAO().modificarUsuario(model);
		
	}

	public static List<Usuario> consultarPersonalAlmacen() {
		List<Usuario> usuarios = new UsuarioDAO().consultarUsuarios();
		List<Usuario> usuariosAlmacen = new ArrayList<Usuario>();
		
		if (usuarios == null) {
			throw new PROWADException(
					"No se pueden consultar los usuarios.",
					"MSG13");
		}
		
		for(Usuario usr : usuarios) {
			if(usr.getRol().getId() == RolEnum.consultarIdRol(RolEnum.Rol.ALMACENISTA)) {
				usuariosAlmacen.add(usr);
			}
		}
		return usuariosAlmacen;
	}

	public static void agregarTienda(Usuario model, int idTienda) {
		Tienda tienda = new TiendaDAO().consultarTienda(idTienda);
		model.setTienda(tienda);
		
	}

	public static void agregarRol(Usuario model, RolEnum.Rol rolEnum) {
		Rol rolModel = new RolDAO().consultarRol(RolEnum.consultarIdRol(rolEnum));
		model.setRol(rolModel);
		
	}
	
	public static boolean esAdministrador(Usuario model) {
		if (model.getRol().getId() == RolEnum.consultarIdRol(RolEnum.Rol.ADMINISTRADOR)) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean esCliente(Usuario model) {
		if (model.getRol().getId() == RolEnum.consultarIdRol(RolEnum.Rol.CLIENTE)) {
			return true;
		} else {
			return false;
		}	}
	
	public static boolean esAlmacenista(Usuario model) {
		if (model.getRol().getId() == RolEnum.consultarIdRol(RolEnum.Rol.ALMACENISTA)) {
			return true;
		} else {
			return false;
		}
	}
}
