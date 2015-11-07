package domain;

import java.io.Serializable;

public class Ubicacion implements Serializable{
	
	/*	Gustavo Cardozo		*/
	private static final long serialVersionUID = -2145089024177400506L;
	
	private Long id;
	private Boolean estado;
	private Mercaderia mercaderia;
	private Zona zona;
	
	public Ubicacion() {
		
	}

	
	public Ubicacion(Long id, Boolean estado, Mercaderia mercaderia, Zona zona) {
		super();
		this.id = id;
		this.estado = estado;
		this.mercaderia = mercaderia;
		this.zona = zona;
	}


	
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Boolean getEstado() {
		return estado;
	}


	public void setEstado(Boolean estado) {
		this.estado = estado;
	}


	public Mercaderia getMercaderia() {
		return mercaderia;
	}


	public void setMercaderia(Mercaderia mercaderia) {
		this.mercaderia = mercaderia;
	}


	public Zona getZona() {
		return zona;
	}


	public void setZona(Zona zona) {
		this.zona = zona;
	}

}

