package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import service.ZonaService;
import util.FacesUtils;
import domain.Zona;
import domain.Zona.TIPO_CATEGORIA;
import domain.Zona.TIPO_PESO;
import domain.Zona.TIPO_TEMPERATURA;

@ManagedBean(name = "zonaController")
@SessionScoped
public class ZonaController implements Serializable {

	private static final long serialVersionUID = 6604100330240730617L;
	
	private static final String ZONA_LIST_PAGE = "zonaList";
	private static final String ZONA_CRUD_PAGE = "crudZona";
	
	private static final String messagesName = ":zonaListMsgForm:zonaListMsg";
	private static final String zonaListForm = "zonaListForm";

	
	@ManagedProperty("#{zonaService}")
	private ZonaService zonaService;
	
	
	private List<Zona> zonaList;
	
	private Zona selectedZona ;
	private Zona zonaCRUD ;
	
	
	private List<TIPO_CATEGORIA> categoriaList = new ArrayList<TIPO_CATEGORIA>();
	private List<TIPO_PESO> pesoList = new ArrayList<TIPO_PESO>();
	private List<TIPO_TEMPERATURA> temperaturaList = new ArrayList<TIPO_TEMPERATURA>();
	
	
	private TIPO_CATEGORIA categoria;
	private TIPO_PESO peso;
	private TIPO_TEMPERATURA temperatura;
	
	private boolean editor;
	
	public ZonaController() {
		
	}
	
	
	@PostConstruct
	public void init() {
		categoriaList = new ArrayList<TIPO_CATEGORIA>(Arrays.asList(TIPO_CATEGORIA.values()));
		pesoList = new ArrayList<TIPO_PESO>(Arrays.asList(TIPO_PESO.values()));
		temperaturaList = new ArrayList<TIPO_TEMPERATURA>(Arrays.asList(TIPO_TEMPERATURA.values()));
		categoria = null;
		peso = null;
		temperatura = null;
		
		load();
	}
	
	
	/**
	 * Carga la lista de zonas nuevamente
	 */
	private void load(){
		zonaList = zonaService.findAll();
	}

	
	
	public String add(){
		editor = false;
		zonaCRUD = new Zona();
		
		return ZONA_CRUD_PAGE;
	}
	
	public String edit(){
		if (null != selectedZona) {

			if(isValidatedEdit(selectedZona)) {
				editor = true;
				zonaCRUD = new Zona();
				zonaCRUD.copyFromOther(selectedZona);
				
				categoria = selectedZona.getCategoria();
				peso = selectedZona.getPeso();
				temperatura = selectedZona.getTemperatura();
			
				return ZONA_CRUD_PAGE;
			}else{
				FacesUtils.infoMessage("msg.zona.error.editar.notIsEmpty");
				updateMessages();
			}
		}
		
		return null;
	}
	
	private boolean isValidatedEdit(Zona zona){
		boolean is = true;
		//if (null != zona.getMercaderias()) {
			//if (!zona.getMercaderias().isEmpty()) {
				//is = false;
			//}
		//}
		return is;
	}
	
	
	public void remove(){
		if (null != selectedZona) {
			if(isValidatedEdit(selectedZona)){
				if (zonaService.deleteByPrimaryKey(selectedZona.getId())) {
					showRemoveMessage();
					clean();
					updateList();
				}else{
					showErrorMessage();
				}
			}else{
				FacesUtils.infoMessage("msg.zona.error.eliminar.notIsEmpty");
				updateMessages();
			}
		}
	}
	
	
	public String save(){
		boolean wasSaved = false;
		
		zonaCRUD.setCategoria(categoria);
		zonaCRUD.setPeso(peso);
		zonaCRUD.setTemperatura(temperatura);
		
		if (editor) {
			selectedZona.copyFromOther(zonaCRUD);
			wasSaved = commit(selectedZona);
		}else{
			wasSaved = commit(zonaCRUD);
		}
		
		return wasSaved ? ZONA_LIST_PAGE : "";
	}
	
	private boolean commit(Zona zona) {
		boolean is = false;
		
		if (zonaService.saveOrUpdate(zona)) {
			load();
			showSaveMessage();
			is = true;
		}else{
			showErrorMessage();
		}
		
		return is;
	}
	
	
	public String cancel(){
		clean();
		return ZONA_LIST_PAGE;
	}
	
	
	private void clean(){
		load();
		selectedZona = null;
		editor = false;
	}
	
	
	private void updateList(){
		FacesUtils.updateComponent(zonaListForm);
	}
	
	private void updateMessages(){
		FacesUtils.updateComponent(messagesName);
	}
	
	
	private void showSaveMessage(){
		FacesUtils.infoMessage("msg.success.save.user");
		updateMessages();
	}
	
	private void showRemoveMessage(){
		FacesUtils.infoMessage("msg.success.remove.user");
		updateMessages();
	}
	
	
	private void showErrorMessage(){
		FacesUtils.errorMessage("msg.error.save");
		updateMessages();
	}
	
	
	/**
	 * gys
	 */

	
	

	public ZonaService getZonaService() {
		return zonaService;
	}

	public List<TIPO_CATEGORIA> getCategoriaList() {
		return categoriaList;
	}


	public void setCategoriaList(List<TIPO_CATEGORIA> categoriaList) {
		this.categoriaList = categoriaList;
	}


	public List<TIPO_PESO> getPesoList() {
		return pesoList;
	}


	public void setPesoList(List<TIPO_PESO> pesoList) {
		this.pesoList = pesoList;
	}


	public List<TIPO_TEMPERATURA> getTemperaturaList() {
		return temperaturaList;
	}


	public void setTemperaturaList(List<TIPO_TEMPERATURA> temperaturaList) {
		this.temperaturaList = temperaturaList;
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


	public void setZonaService(ZonaService zonaService) {
		this.zonaService = zonaService;
	}


	public List<Zona> getZonaList() {
		return zonaList;
	}


	public void setZonaList(List<Zona> zonaList) {
		this.zonaList = zonaList;
	}


	public Zona getSelectedZona() {
		return selectedZona;
	}


	public void setSelectedZona(Zona selectedZona) {
		this.selectedZona = selectedZona;
	}


	public boolean isEditor() {
		return editor;
	}


	public void setEditor(boolean editor) {
		this.editor = editor;
	}


	public Zona getZonaCRUD() {
		return zonaCRUD;
	}


	public void setZonaCRUD(Zona zonaCRUD) {
		this.zonaCRUD = zonaCRUD;
	}

	

}
