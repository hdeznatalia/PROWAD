package mx.prowad.bs;

public class RolEnum {
	public enum Rol {
		ADMINISTRADOR, ALMACENISTA, CLIENTE
	}
	public static int consultarIdRol(Rol rol) {
		switch(rol) {
		case ADMINISTRADOR:
			return 1;
		case ALMACENISTA:
			return 2;
		case CLIENTE:
			return 3;
		default:
			return -1;
		
		}
	}
}
