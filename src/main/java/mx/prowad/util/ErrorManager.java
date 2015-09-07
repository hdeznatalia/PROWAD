package mx.prowad.util;


public class ErrorManager {
	public static void agregaMensajeError(ActionSupportPROWAD ap, Exception ex) {
		if(ex instanceof PROWADException) {
		PROWADException pe = (PROWADException) ex;
			if(pe instanceof PROWADValidacionException) {
				PROWADValidacionException pve = (PROWADValidacionException) pe;
				if(pve.getCampo() != null) {
					if(pe.getParametros() != null){
						ap.addFieldError(pve.getCampo(), ap.getText(pve.getIdMensaje(), pe.getParametros()));
					} else {
						ap.addFieldError(pve.getCampo(), ap.getText(pve.getIdMensaje()));
					}
				} else {
					if(pe.getParametros() != null){
						ap.addActionError(ap.getText(pe.getIdMensaje(), pe.getParametros()));
					} else {
						ap.addActionError(ap.getText(pe.getIdMensaje()));
					}
				}
			} else { 
				if(pe.getParametros() != null){
					ap.addActionError(ap.getText(pe.getIdMensaje(), pe.getParametros()));
				} else {
					ap.addActionError(ap.getText(pe.getIdMensaje()));
				}
			}
		} else {
			ap.addActionError(ap.getText("MSG13"));
		}
		SessionManager.set(ap.getActionErrors(), "mensajesError");
		System.err.println(ex.getMessage());
		ex.printStackTrace();
	}
}

