package domain;

import java.util.List;

public class Zona {

	public enum TIPO_CATEGORIA {
		ELECTRODOMESTICOS, LACTEOS, MUEBLES, CARNES, AUTOMOTRIZ, BAZAR, ROPA, OTROS
	}

	public enum TIPO_PESO {
		LIVIANO, NORMAL, ALTO
	}

	public enum TIPO_TEMPERATURA {
		BAJA, NORMAL, ALTA
	}
	
	private Long id;
	private String nombre;
	private Integer capacidad;
	private boolean estado;
	private TIPO_CATEGORIA categoria;
	private TIPO_PESO peso;
	private TIPO_TEMPERATURA temperatura;
	private List<Ubicacion> ubicaciones;
	
	public Zona() {
		
	}

	public Integer getCantidadPorMercaderia(){
		
//		Integer cant = 0;
//		
//		if (null != mercaderias) {
//			for (Mercaderia mercaderia : mercaderias) {
//				cant += mercaderia.getBultos();
//			}
//		}
//		
//		return cant;
		return 0;
	}
	
	
	public void copyFromOther(Zona zona){
		this.setNombre(zona.getNombre());
		this.setCapacidad(zona.getCapacidad());
		this.setEstado(zona.isEstado());
		this.setCategoria(zona.getCategoria());
		this.setPeso(zona.getPeso());
		this.setTemperatura(zona.getTemperatura());
		this.setUbicaciones(zona.getUbicaciones());
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(Integer capacidad) {
		this.capacidad = capacidad;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public TIPO_CATEGORIA getCategoria() {
		return categoria;
	}

	public void setCategoria(TIPO_CATEGORIA categoria) {
		this.categoria = categoria;
	}

	public TIPO_PESO getPeso() {
		return peso;
	}

	public void setPeso(TIPO_PESO peso) {
		this.peso = peso;
	}

	public TIPO_TEMPERATURA getTemperatura() {
		return temperatura;
	}

	public void setTemperatura(TIPO_TEMPERATURA temperatura) {
		this.temperatura = temperatura;
	}

	public List<Ubicacion> getUbicaciones() {
		return ubicaciones;
	}

	public void setUbicaciones(List<Ubicacion> ubicaciones) {
		this.ubicaciones = ubicaciones;
	}

}
