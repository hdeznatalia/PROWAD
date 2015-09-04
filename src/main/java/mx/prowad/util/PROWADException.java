package mx.prowad.util;

import java.util.ArrayList;

public class PROWADException extends RuntimeException{

	/**
	 * 
	 */
	private String idMensaje;
	private String[] parametros; 
	private static final long serialVersionUID = 1L;
	
		
		public PROWADException(String message, String idMensaje) {
		super(message);
		this.idMensaje = idMensaje;
	}
		public PROWADException (String message, String idMensaje, String[] parametros) {
	        super (message);
	        this.idMensaje = idMensaje;
	        this.parametros = parametros;
	    }

		public PROWADException (String message) {
	        super (message);
	    }
	
	    public PROWADException (Throwable cause) {
	        super (cause);
	    }
	
	    public PROWADException (String message, Throwable cause) {
	        super (message, cause);
	    }

		public String getIdMensaje() {
			return idMensaje;
		}

		public void setIdMensaje(String idMensaje) {
			this.idMensaje = idMensaje;
		}
		public String[] getParametros() {
			return parametros;
		}
		public void setParametros(String[] parametros) {
			this.parametros = parametros;
		}

		
	    
	    

}
