package controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import service.MercaderiaService;
import util.FacesUtils;
import domain.Mercaderia;


@ManagedBean(name = "despacharMercaderiController")
@ViewScoped
public class DespacharMercaderiController implements Serializable{

	private static final long serialVersionUID = 5571815424732529338L;
	
	private static final String messagesName = ":mercaderiaListMsgForm:mercaderiaListMsg";
	
	
	@ManagedProperty("#{mercaderiaService}")
	private MercaderiaService mercaderiaService;
	
	private List<Mercaderia> mercaderias;
	private Mercaderia mercaderia;
	
	public DespacharMercaderiController() {
		
	}
	
	
	@PostConstruct
	public void init() {
		load();
	}
	
	
	/**
	 * Trae todas las mercaderias de la base
	 */
	private void load(){
		mercaderias = mercaderiaService.findAll();
	}
	
	
	public void despachar(){
		if (null != mercaderia) {
			try {
				boolean saved = mercaderiaService.despachar(mercaderia);
				
				if (saved) {
					showSaveMessage();
				}else{
					showErrorMessage();
				}
				
			} catch (Exception e) {
				
			}
		}
	}
	
	public boolean isDisabledDespachar(){
		boolean isDisabled = true;
		
		if (null != mercaderia) {
			isDisabled = null != mercaderia.getFechaEgreso() ? false : true;
		}
		
		
		return isDisabled;
	}
	

	private void showSaveMessage(){
		FacesUtils.infoMessage("msg.success.save.user");
		updateMessages();
		load();
	}
	
	private void showErrorMessage(){
		FacesUtils.errorMessage("msg.error.save");
		updateMessages();
		load();
	}
	
	private void updateMessages(){
		FacesUtils.updateComponent(messagesName);
	}

	
	/**
	 * G Y S
	 */
	
	public MercaderiaService getMercaderiaService() {
		return mercaderiaService;
	}


	public void setMercaderiaService(MercaderiaService mercaderiaService) {
		this.mercaderiaService = mercaderiaService;
	}


	public List<Mercaderia> getMercaderias() {
		return mercaderias;
	}


	public void setMercaderias(List<Mercaderia> mercaderias) {
		this.mercaderias = mercaderias;
	}


	public Mercaderia getMercaderia() {
		return mercaderia;
	}


	public void setMercaderia(Mercaderia mercaderia) {
		this.mercaderia = mercaderia;
	}

	
}
