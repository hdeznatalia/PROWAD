package mx.prowad.model;

// Generated 01-sep-2015 17:22:12 by Hibernate Tools 4.0.0

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * CategoriaAtributo generated by hbm2java
 */
@Entity
@Table(name = "Categoria_Atributo", catalog = "PROWAD")
public class CategoriaAtributo implements java.io.Serializable {

	private CategoriaAtributoId id;

	public CategoriaAtributo() {
	}

	public CategoriaAtributo(CategoriaAtributoId id) {
		this.id = id;
	}

	public CategoriaAtributo(Categoria categoria, Atributo atributo) {
		id = new CategoriaAtributoId(atributo, categoria);
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "atributoid", column = @Column(name = "Atributoid", nullable = false)),
			@AttributeOverride(name = "categoriaid", column = @Column(name = "Categoriaid", nullable = false)) })
	public CategoriaAtributoId getId() {
		return this.id;
	}

	public void setId(CategoriaAtributoId id) {
		this.id = id;
	}

}
