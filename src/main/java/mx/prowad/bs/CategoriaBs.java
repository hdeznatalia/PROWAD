package mx.prowad.bs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.JDBCException;

import mx.prowad.dao.AtributoDAO;
import mx.prowad.dao.CategoriaDAO;
import mx.prowad.model.Atributo;
import mx.prowad.model.Categoria;
import mx.prowad.model.CategoriaAtributo;
import mx.prowad.model.Producto;
import mx.prowad.util.PROWADException;

public class CategoriaBs {
	public static Categoria consultarCategoria(int id) {
		Categoria categoria = null;
		categoria = new CategoriaDAO().consultarCategoria(id);
		if (categoria == null) {
			throw new PROWADException("No se puede consultar la categoria.",
					"MSG13");
		}
		return categoria;
	}
	
	public static Categoria consultarCategoria(String nombre) {
		Categoria categoria = null;
		categoria = new CategoriaDAO().consultarCategoria(nombre);
		if (categoria == null) {
			throw new PROWADException("No se puede consultar la categoria.",
					"MSG13");
		}
		return categoria;
	}

	public static List<Categoria> consultarCategorias() {
		List<Categoria> categorias = new CategoriaDAO().consultarCategorias();
		if (categorias == null) {
			throw new PROWADException(
					"No se pueden consultar las categorias.",
					"MSG13");
		}
		return categorias;
	}

	public static void registrarCategoria(Categoria model) throws Exception{
		validar(model);
		new CategoriaDAO().registrarCategoria(model);
	}

	private static void validar(Categoria model) {
		//Faltan validaciones
	}

	public static void eliminarCategoria(Categoria model) throws Exception {
		try {
			new CategoriaDAO().eliminarCategoria(model);
		} catch (JDBCException je) {
			if(je.getErrorCode() == 1451) {
				throw new PROWADException(
						"No se puede eliminar la categoría.",
						"MSG14");
			}
			System.out.println("ERROR CODE " + je.getErrorCode());
			je.printStackTrace();
			throw new Exception();
		}
	}

	public static void modificarCategoria(Categoria model) {
		validar(model);
		new CategoriaDAO().modificarCategoria(model);
		
	}

	public static void agregarAtributos(Categoria model,
			List<Atributo> atributos) {
		Set<CategoriaAtributo> categoriasAtributo = new HashSet<CategoriaAtributo>(0);
		model.getCategoriasAtributo().clear();
		for(Atributo atr : atributos) {
			Atributo atributoBD = new AtributoDAO().consultarAtributo(atr.getNombre());
			CategoriaAtributo ca = new CategoriaAtributo(model, atributoBD);
			categoriasAtributo.add(ca);
		}
		model.setCategoriasAtributo(categoriasAtributo);
		
	}

	public static List<Atributo> consultarAtributos(Categoria model) {
		List<Atributo> atributos = new ArrayList<Atributo>();
		for(CategoriaAtributo ca : model.getCategoriasAtributo()) {
			Atributo atributo = ca.getId().getAtributo();
			System.out.println("nombre: " + atributo.getNombre());
			System.out.println("nombre: " + atributo.getTipoDato().getNombre());
			atributos.add(atributo);
		}
		return atributos;
	}
}
