package mx.prowad.model;

// Generated 01-sep-2015 17:22:12 by Hibernate Tools 4.0.0

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * CategoriaAtributoId generated by hbm2java
 */
@Embeddable
public class CategoriaAtributoId implements java.io.Serializable {

	private Atributo atributo;
	private Categoria categoria;

	public CategoriaAtributoId() {
	}

	public CategoriaAtributoId(Atributo atributo, Categoria categoria) {
		this.atributo = atributo;
		this.categoria = categoria;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "Atributoid", referencedColumnName = "id")
	public Atributo getAtributo() {
		return this.atributo;
	}

	public void setAtributo(Atributo atributo) {
		this.atributo = atributo;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "Categoriaid", referencedColumnName = "id")
	public Categoria getCategoria() {
		return this.categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
}
