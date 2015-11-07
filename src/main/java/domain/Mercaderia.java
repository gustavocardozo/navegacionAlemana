package domain;

import java.io.Serializable;
import java.util.Date;


public class Mercaderia implements Serializable{
	

	private static final long serialVersionUID = -7221233997644135296L;
	
	private Long id;
	private String userName;
	private String proveedorName;
	private Date fechaIngreso;
	private Date fechaEgreso;
	private String descripcion;
	private Integer remito;
	private Ubicacion ubicacion;
	
	
	
	
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getRemito() {
		return remito;
	}

	public void setRemito(Integer remito) {
		this.remito = remito;
	}

	public Mercaderia() {
		id = null;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getProveedorName() {
		return proveedorName;
	}

	public void setProveedorName(String proveedorName) {
		this.proveedorName = proveedorName;
	}

	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public Date getFechaEgreso() {
		return fechaEgreso;
	}

	public void setFechaEgreso(Date fechaEgreso) {
		this.fechaEgreso = fechaEgreso;
	}

	public Ubicacion getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}
	
}
