package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import security.LoginController;
import service.MercaderiaService;
import service.ProveedorService;
import service.ZonaService;
import util.FacesUtils;
import domain.Mercaderia;
import domain.Proveedor;
import domain.Zona;
import domain.Zona.TIPO_CATEGORIA;
import domain.Zona.TIPO_PESO;
import domain.Zona.TIPO_TEMPERATURA;

@ManagedBean(name = "ubicarMercaderiaController")
@ViewScoped
public class UbicarMercaderiaController implements Serializable {

	private static final long serialVersionUID = 6494781319024311778L;
	
	private static final String PAGE_UBICAR = "ubicar";
	private static final String msgID = "ubicarMercaderiaCRUDTabView:ubicarMercaderiaCRUDMsgForm:ubicarMercaderiaCRUDMsg";
	
	@ManagedProperty("#{loginController}")
	private LoginController loginController;
	
	@ManagedProperty("#{proveedorService}")
	private ProveedorService proveedorService;
	
	@ManagedProperty("#{zonaService}")
	private ZonaService zonaService;
	
	@ManagedProperty("#{mercaderiaService}")
	private MercaderiaService mercaderiaService;

	private List<Proveedor> proveedors;
	private Proveedor proveedor;
	
	private List<TIPO_CATEGORIA> categorias;
	private List<TIPO_PESO> pesos;
	private List<TIPO_TEMPERATURA> temperaturas;
	
	private TIPO_CATEGORIA categoria;
	private TIPO_PESO peso;
	private TIPO_TEMPERATURA temperatura;
	
	private Zona zona;
	
	private String nombre;
	
	private String desProducto;
	
	private Integer cantidad;
	
	private String proveedorString;
	private Map<String,String> provedoresLista;
	
	private String remito;


	public UbicarMercaderiaController() {
	}

	
	@PostConstruct
	public void init() {
		categorias = new ArrayList<TIPO_CATEGORIA>(Arrays.asList(TIPO_CATEGORIA.values()));
		pesos = new ArrayList<Zona.TIPO_PESO>(Arrays.asList(TIPO_PESO.values()));
		temperaturas = new ArrayList<Zona.TIPO_TEMPERATURA>(Arrays.asList(TIPO_TEMPERATURA.values()));
		proveedors = proveedorService.findAll();
		
        
		// Convierto en una lista de string para el combo
		Iterator<Proveedor> it = proveedors.iterator();
		Proveedor prov = new Proveedor();
		provedoresLista = new HashMap<String, String>();
		while(it.hasNext()){
			 prov = it.next();
			 //provedoresLista.put(prov.getId().toString(), prov.getNombre());
			 provedoresLista.put(prov.getNombre(),prov.getId().toString());
			 
			 
		}
	}
	
	private boolean commit(Mercaderia mercaderia) {
		boolean is = false;
		
		if (zonaService.saveOrUpdate(zona)) {
			load();
			showSaveMessage();
			is = true;
		}else{
			showErrorGuardar();
		}
		return is;
	}
	public String save() {
		boolean wasSaved = false;
		String url = "";
		
		Mercaderia merca = new Mercaderia();
		//merca.setBultos(cantidad);
		merca.setFechaIngreso(new Date());
		merca.setProveedorName(proveedorString);
		merca.setUserName(loginController.getUsername());
		merca.setDescripcion(desProducto);
		merca.setRemito(Integer.parseInt(remito));
		
//		if (null != zona.getMercaderias()) {
//			zona.getMercaderias().add(merca);
//		}else{
//			List<Mercaderia> list = new ArrayList<Mercaderia>();
//			list.add(merca);
//			zona.setMercaderias(list);
//		}
		
		if (null != merca) {
			//selectedUser.copyFromOther(userCRUD);
			wasSaved = commit(merca);
		}
		
		if (wasSaved) {
			clean();
			//load();
		}
		
		return url;
	}
	

	
	public String load(){
		clean();
		nombre = loginController.getCurrentUserName();
		return PAGE_UBICAR;
	}
	
	
	public void add(){
		
		if (null != temperatura &&
				null != categoria &&
					null != peso) {
			
			zona = zonaService.findByProperties(temperatura, categoria, peso);
			if (null != zona) {
				
				if (zona.getCantidadPorMercaderia() <= zona.getCapacidad()) {
					
					Integer cap = zona.getCantidadPorMercaderia() + cantidad;
					
					if (cap < zona.getCapacidad()) {
						//PROCEDER A GUARDAR
						//showErrorGuardar();
						save();
						

					}else{
						showErrorSeSuperaLaCantidadDeLaZonaMessage();//MENSAJE LA CANTIDAD DE BULTOS SUPERA LO POSIBLE A ALMACENAR
					}
					
				}else{
					showErrorZonaEncontradaNoTieneCapacidadMessage(); //MENSAJE LA ZONA ENCONTRADA NO TIENE CAPACIDAD DE ALMACENAMIENTO
				}
			}else{
				showErrorNoSeEncontroUbicacionMessage();//MENSAJE NO SE ENCONTRO UBICACION PARA ESTA MERCADERIA
			}
		}else{
			showErrorCompletarCamposMessage();//MENSAJE COMPLETE TODOS LOS CAMPOS
		}
	}
	
	private void showSaveMessage(){
		FacesUtils.infoMessage("msg.success.save.mercaderia");
		FacesUtils.updateComponent(msgID);
	}
	
	private void showErrorGuardar() {
		// TODO Auto-generated method stub
		FacesUtils.errorMessage("msg.error.save.mercaderia");//LOS MENSAJES SE TIENE QUE AGREGAR EN messages.properties
		FacesUtils.updateComponent(msgID);
	}


	private void showErrorCompletarCamposMessage(){
		FacesUtils.errorMessage("msg.mercaderia.error.save.CompletarCampos");//LOS MENSAJES SE TIENE QUE AGREGAR EN messages.properties
		FacesUtils.updateComponent(msgID);
	}
	
	private void showErrorNoSeEncontroUbicacionMessage(){
		FacesUtils.errorMessage("msg.mercaderia.error.save.NoSeEncontroUbicacion");//LOS MENSAJES SE TIENE QUE AGREGAR EN messages.properties
		FacesUtils.updateComponent(msgID);
	}
	
	private void showErrorZonaEncontradaNoTieneCapacidadMessage(){
		FacesUtils.errorMessage("msg.mercaderia.error.save.ZonaEncontradaNoTieneCapacidad");//LOS MENSAJES SE TIENE QUE AGREGAR EN messages.properties
		FacesUtils.updateComponent(msgID);
	}
	
	private void showErrorSeSuperaLaCantidadDeLaZonaMessage(){
		FacesUtils.errorMessage("msg.mercaderia.error.save.SuperaLaCantidadDeLaZona");//LOS MENSAJES SE TIENE QUE AGREGAR EN messages.properties
		FacesUtils.updateComponent(msgID);
	}

	
	
	private void clean(){
		proveedorString = null;
		zona = null;
		cantidad = null;
		categoria = null;
		peso = null;
		temperatura = null;
		nombre = null;
		desProducto = null;
		remito = null;
		
	}
	
	
	
	/**
	 * G Y S
	 * @return
	 */
	public MercaderiaService getMercaderiaService() {
		return mercaderiaService;
	}

	public void setMercaderiaService(MercaderiaService mercaderiaService) {
		this.mercaderiaService = mercaderiaService;
	}

	public Map<String, String> getProvedoresLista() {
		return provedoresLista;
	}


	public void setProvedoresLista(Map<String, String> provedoresLista) {
		this.provedoresLista = provedoresLista;
	}
	
	public String getRemito() {
		return remito;
	}


	public void setRemito(String remito) {
		this.remito = remito;
	}


	public String getProveedorString() {
		return proveedorString;
	}


	public void setProveedorString(String proveedorString) {
		this.proveedorString = proveedorString;
	}
	
	public List<Proveedor> getProveedors() {
		return proveedors;
	}

	public void setProveedors(List<Proveedor> proveedors) {
		this.proveedors = proveedors;
	}

	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	public Zona getZona() {
		return zona;
	}

	public void setZona(Zona zona) {
		this.zona = zona;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<TIPO_CATEGORIA> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<TIPO_CATEGORIA> categorias) {
		this.categorias = categorias;
	}

	public List<TIPO_PESO> getPesos() {
		return pesos;
	}

	public void setPesos(List<TIPO_PESO> pesos) {
		this.pesos = pesos;
	}

	public List<TIPO_TEMPERATURA> getTemperaturas() {
		return temperaturas;
	}

	public void setTemperaturas(List<TIPO_TEMPERATURA> temperaturas) {
		this.temperaturas = temperaturas;
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


	public LoginController getLoginController() {
		return loginController;
	}


	public void setLoginController(LoginController loginController) {
		this.loginController = loginController;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public ProveedorService getProveedorService() {
		return proveedorService;
	}


	public void setProveedorService(ProveedorService proveedorService) {
		this.proveedorService = proveedorService;
	}


	public ZonaService getZonaService() {
		return zonaService;
	}


	public void setZonaService(ZonaService zonaService) {
		this.zonaService = zonaService;
	}


	public String getDesProducto() {
		return desProducto;
	}


	public void setDesProducto(String desProducto) {
		this.desProducto = desProducto;
	}

}
