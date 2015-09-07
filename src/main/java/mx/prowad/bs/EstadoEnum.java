package mx.prowad.bs;

public class EstadoEnum {
	public enum Estado {
		PENDIENTE, PAGADO
	}
	public static int consultarIdEstado(Estado estado) {
		switch(estado) {
		case PENDIENTE:
			return 1;
		case PAGADO:
			return 2;
		default:
			return -1;
		
		}
	}
}
