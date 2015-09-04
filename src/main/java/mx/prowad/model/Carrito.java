package mx.prowad.model;

// Generated 01-sep-2015 17:22:12 by Hibernate Tools 4.0.0

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Carrito generated by hbm2java
 */
@Entity
@Table(name = "Carrito", catalog = "PROWAD")
public class Carrito implements java.io.Serializable {

	private Integer id;
	private String usuariocurp;
	private Date fechaCompra;
	private int estadoid;

	public Carrito() {
	}

	public Carrito(String usuariocurp, int estadoid) {
		this.usuariocurp = usuariocurp;
		this.estadoid = estadoid;
	}

	public Carrito(String usuariocurp, Date fechaCompra, int estadoid) {
		this.usuariocurp = usuariocurp;
		this.fechaCompra = fechaCompra;
		this.estadoid = estadoid;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "Usuariocurp", nullable = false, length = 18)
	public String getUsuariocurp() {
		return this.usuariocurp;
	}

	public void setUsuariocurp(String usuariocurp) {
		this.usuariocurp = usuariocurp;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "FechaCompra", length = 10)
	public Date getFechaCompra() {
		return this.fechaCompra;
	}

	public void setFechaCompra(Date fechaCompra) {
		this.fechaCompra = fechaCompra;
	}

	@Column(name = "Estadoid", nullable = false)
	public int getEstadoid() {
		return this.estadoid;
	}

	public void setEstadoid(int estadoid) {
		this.estadoid = estadoid;
	}

}