package mx.prowad.util;

public class PROWADValidacionException extends PROWADException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String campo;
	public PROWADValidacionException(String message, String idMensaje,
			String[] parametros) {
		super(message, idMensaje, parametros);
	}
	public PROWADValidacionException(String message, String idMensaje,
			String[] parametros, String campo) {
		super(message, idMensaje, parametros);
		this.campo = campo;
	}
	public PROWADValidacionException(String message, String idMensaje) {
		super(message, idMensaje);
	}
	public String getCampo() {
		return campo;
	}
	public void setCampo(String campo) {
		this.campo = campo;
	}
	
}
