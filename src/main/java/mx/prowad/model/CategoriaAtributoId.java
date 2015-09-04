package mx.prowad.model;

// Generated 01-sep-2015 17:22:12 by Hibernate Tools 4.0.0

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * CategoriaAtributoId generated by hbm2java
 */
@Embeddable
public class CategoriaAtributoId implements java.io.Serializable {

	private int atributoid;
	private int categoriaid;

	public CategoriaAtributoId() {
	}

	public CategoriaAtributoId(int atributoid, int categoriaid) {
		this.atributoid = atributoid;
		this.categoriaid = categoriaid;
	}

	@Column(name = "Atributoid", nullable = false)
	public int getAtributoid() {
		return this.atributoid;
	}

	public void setAtributoid(int atributoid) {
		this.atributoid = atributoid;
	}

	@Column(name = "Categoriaid", nullable = false)
	public int getCategoriaid() {
		return this.categoriaid;
	}

	public void setCategoriaid(int categoriaid) {
		this.categoriaid = categoriaid;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof CategoriaAtributoId))
			return false;
		CategoriaAtributoId castOther = (CategoriaAtributoId) other;

		return (this.getAtributoid() == castOther.getAtributoid())
				&& (this.getCategoriaid() == castOther.getCategoriaid());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getAtributoid();
		result = 37 * result + this.getCategoriaid();
		return result;
	}

}