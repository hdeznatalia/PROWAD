package mx.prowad.bs;

import java.util.List;

import mx.prowad.dao.AtributoDAO;
import mx.prowad.dao.TipoDatoDAO;
import mx.prowad.model.Atributo;
import mx.prowad.model.TipoDato;
import mx.prowad.util.PROWADException;

public class AtributoBs {
	public static Atributo consultarAtributo(int id) {
		Atributo atributo = null;
		atributo = new AtributoDAO().consultarAtributo(id);
		if (atributo == null) {
			throw new PROWADException("No se puede consultar el atributo.",
					"MSG13");
		}
		return atributo;
	}

	public static List<Atributo> consultarAtributos() {
		List<Atributo> atributos = new AtributoDAO().consultarAtributos();
		if (atributos == null) {
			throw new PROWADException(
					"No se pueden consultar los atributos.",
					"MSG13");
		}
		return atributos;
	}

	public static void registrarAtributo(Atributo model) throws Exception{
		validar(model);
		new AtributoDAO().registrarAtributo(model);
	}

	private static void validar(Atributo model) {
		//Faltan validaciones
	}

	public static void agregarTipoDato(Atributo model, int idTipoDato) {
		TipoDato tipoDato = new TipoDatoDAO().consultarTipoDato(idTipoDato);
		model.setTipoDato(tipoDato);
		
	}

	public static List<TipoDato> consultarTiposDato() {
		List<TipoDato> tiposDato = new TipoDatoDAO().consultarTiposDato();
		if (tiposDato == null) {
			throw new PROWADException(
					"No se pueden consultar los tipos de dato.",
					"MSG13");
		}
		return tiposDato;
	}

	public static void eliminarAtributo(Atributo model) {
		new AtributoDAO().eliminarAtributo(model);
		
	}

	public static void modificarAtributo(Atributo model) {
		validar(model);
		new AtributoDAO().modificarAtributo(model);
		
	}
}
