package mx.prowad.model;

// Generated 01-sep-2015 17:22:12 by Hibernate Tools 4.0.0

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * ProductoCategoria generated by hbm2java
 */
@Entity
@Table(name = "Producto_Categoria", catalog = "PROWAD")
public class ProductoCategoria implements java.io.Serializable {

	private ProductoCategoriaId id;

	public ProductoCategoria() {
	}
	
	public ProductoCategoria(Producto producto, Categoria categoria) {
		this.id = new ProductoCategoriaId(producto, categoria);
	}

	public ProductoCategoria(ProductoCategoriaId id) {
		this.id = id;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "productoid", column = @Column(name = "Productoid", nullable = false)),
			@AttributeOverride(name = "categoriaid", column = @Column(name = "Categoriaid", nullable = false)) })
	public ProductoCategoriaId getId() {
		return this.id;
	}

	public void setId(ProductoCategoriaId id) {
		this.id = id;
	}

}
