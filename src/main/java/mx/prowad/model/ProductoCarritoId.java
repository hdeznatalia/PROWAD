package mx.prowad.model;

// Generated 01-sep-2015 17:22:12 by Hibernate Tools 4.0.0

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * ProductoCarritoId generated by hbm2java
 */
@Embeddable
public class ProductoCarritoId implements java.io.Serializable {

	private int productoid;
	private int carritoid;

	public ProductoCarritoId() {
	}

	public ProductoCarritoId(int productoid, int carritoid) {
		this.productoid = productoid;
		this.carritoid = carritoid;
	}

	@Column(name = "Productoid", nullable = false)
	public int getProductoid() {
		return this.productoid;
	}

	public void setProductoid(int productoid) {
		this.productoid = productoid;
	}

	@Column(name = "Carritoid", nullable = false)
	public int getCarritoid() {
		return this.carritoid;
	}

	public void setCarritoid(int carritoid) {
		this.carritoid = carritoid;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ProductoCarritoId))
			return false;
		ProductoCarritoId castOther = (ProductoCarritoId) other;

		return (this.getProductoid() == castOther.getProductoid())
				&& (this.getCarritoid() == castOther.getCarritoid());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getProductoid();
		result = 37 * result + this.getCarritoid();
		return result;
	}

}