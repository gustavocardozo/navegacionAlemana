package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import service.UserService;
import util.FacesUtils;
import util.PasswordUtils;
import domain.User;
import domain.User.TIPO_USUARIO;


@ManagedBean(name = "userController")
@SessionScoped
public class UserController implements Serializable{

	private static final long serialVersionUID = -3770573459254222700L;
	
	@ManagedProperty("#{userService}")
	private UserService userService;
	
	private static final String messagesName = ":userListMsgForm:userListMsg";
	private static final String userCrudPage = "crudUser";
	private static final String userListPage = "userList";
	private static final String userListForm = "userListForm";
	
	private List<User> userList = new ArrayList<User>();
	private List<TIPO_USUARIO> tipoUsuerioList = new ArrayList<User.TIPO_USUARIO>();
	private User selectedUser;
	private User userCRUD;
	private TIPO_USUARIO tipoUsuario;
	
	
	private boolean editor;
	private boolean editarPassword;
	
	private String password;
	
	public UserController() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void init() {
		tipoUsuerioList = new ArrayList<User.TIPO_USUARIO>(Arrays.asList(TIPO_USUARIO.values()));
		load();
	}
	
	/**
	 * Carga la lista de usuario nuevamente
	 */
	private void load(){
		userList = userService.findAll();
	}
	
	/**
	 * envia a la pagina de agregar
	 * @return
	 */
	public String add() {
		userCRUD = new User();
		userCRUD.setFechaIngreso(new Date());
		selectedUser = null;
		editor = false;
		password = null;
		return userCrudPage;
	}
	
	/**
	 * Envia a la pagina de edicion
	 * 
	 * @return
	 */
	public String edit() {
		String url = "";
		
		if (null != selectedUser) {
			userCRUD = selectedUser.clone();
			tipoUsuario = userCRUD.getTipoUsuario();
			editor = true;
			editarPassword = false;
			password = null;
			url = userCrudPage;
		}
		
		return url;
	}
	
	/**
	 * Elimina el usuario seleccionado
	 * (Lo pasa a inactivo)
	 */
	public void remove() {
		if (null != selectedUser) {
			selectedUser.setEstado(false);
			selectedUser.setFechaEgreso(new Date());
	
			if(userService.saveOrUpdate(selectedUser)){
				showRemoveMessage();
			}else{
				showErrorMessage();
			}
			clean();
			updateMessagesList();
			updateListPanel();
		}
	}

	/*
	 * Guarda el usuario
	 */
	public String save() {
		boolean wasSaved = false;
		String url = "";
			
		userCRUD.setTipoUsuario(this.getTipoUsuario());
			
		if (null != selectedUser) {
			selectedUser.copyFromOther(userCRUD);
			wasSaved = commit(selectedUser , url);
		}else{		
			wasSaved = commit(userCRUD , url);
		}
		
		if (wasSaved) {
			//clean();
			load();
		}
		
		return url;
	}

	private boolean commit(User user , String url ) {		
		boolean wasSaved = false;

		if (changePassword(user)) {
			if (isFechaNacValidated(user)) {
				if (userService.saveOrUpdate(user)) {
					showSaveMessage();
					wasSaved = true;
				}else{
					showErrorMessage();
				}
			}else{
				showFechaNacMessage();
			}
		}else{
			showPasswordMessage();
		}
		
		return wasSaved;
	}
	
	/**
	 * Cancela la edicion y vuelve a la pagina de la
	 * lista de usuario
	 * @return
	 */
	public String cancel() {
		clean();
		return userListPage;
	}
	

	public boolean isDisabledEditOrDelete(){
		boolean canBe = true;
		
		if (null != selectedUser) {
			if (selectedUser.isEstado() || null == selectedUser.getFechaEgreso()) {
				canBe = false;
			}
		}		
		return canBe;
	}
	
	
	public boolean changePassword(User user){
		boolean isCheck = false;
		
		
		if (!editor || editarPassword) {
			String password = userCRUD.getPassword();
			if (PasswordUtils.validate(password)){
				userCRUD.setPassword(PasswordUtils.shaHash(password));
				isCheck = true;
			}
			
		}else{
			isCheck = true;
		}
		
		return isCheck;
	}
	
	
	public boolean isFechaNacValidated(User user){
		boolean is = false;
		
		Date today = new Date();
		Calendar todayCalendar = Calendar.getInstance();
		todayCalendar.setTime(today);
		
		Date nac = user.getFechaNac();
		Calendar nacCalendar = Calendar.getInstance();
		nacCalendar.setTime(nac);
		
		Integer nacYear = nacCalendar.get(Calendar.YEAR);
		Integer todayYear = todayCalendar.get(Calendar.YEAR);
		
		Integer nacMonth = nacCalendar.get(Calendar.MONTH);
		Integer todayMonth = todayCalendar.get(Calendar.MONTH);
		
		Integer nacDay = nacCalendar.get(Calendar.DAY_OF_MONTH);
		Integer todayDay = todayCalendar.get(Calendar.DAY_OF_MONTH);
		
		
		Integer diferenciaYear = todayYear - nacYear;
		Integer diferenciaMonth = todayMonth - nacMonth;
		Integer diferenciaDay = todayDay - nacDay;
		
		
		if (diferenciaYear < 100 && diferenciaYear >= 18) {
			
			if (diferenciaYear == 18) {
				
				if (diferenciaMonth.equals(0)) {
					
					if (diferenciaDay > 0) {
						is = true;
					}
					
				}else if(diferenciaMonth > 1){
					is = true;
				}
			}else{
				
				is = true;
			}
		}
				
		return is;
	}
	
	
	/**
	 * 
	 * 
	 */
	
	
	private void clean(){
		selectedUser = null;
		userCRUD = null;
		tipoUsuario = null;
		password = null;
		editarPassword = false;
		editor = false;
		userList = userService.findAll();
	}
	
	private void updateListPanel() {
		FacesUtils.
		updateComponent(userListForm);
	}
	
	private void showSaveMessage(){
		FacesUtils.infoMessage("msg.success.save.user");
		updateMessagesList();
	}
	
	private void showRemoveMessage(){
		FacesUtils.infoMessage("msg.success.remove.user");
		updateMessages();
	}
	
	private void close() {
		// TODO cerrar panel abierto
		
	}

	private void showErrorMessage(){
		FacesUtils.errorMessage("msg.error.save");
		updateMessages();
	}
	
	private void showConstraintMessage(){
		FacesUtils.errorMessages("msg.word.username", "msg.validation.username.duplicity");
		updateMessages();
	}

	private void showPasswordMessage(){
		FacesUtils.errorMessages("msg.password.password", "msg.password.message.error.regex");
		updateMessages();
	}

	private void showFechaNacMessage(){
		FacesUtils.errorMessages("msg.fechaNac", "msg.fechaNac.error");
		updateMessages();
	}
	
	
	private void updateMessages(){
		FacesUtils.updateComponent(messagesName);
	}
	
	private void updateMessagesList(){
		FacesUtils.updateComponent("userListMsgForm:userListMsg");
	}
	
	
	public UserService getUserService() {
		return userService;
	}
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public List<domain.User> getUserList() {
		return userList;
	}

	public void setUserList(List<domain.User> userList) {
		this.userList = userList;
	}

	public User getSelectedUser() {
		return selectedUser;
	}

	public void setSelectedUser(User selectedUser) {
		this.selectedUser = selectedUser;
	}

	public User getUserCRUD() {
		return userCRUD;
	}

	public void setUserCRUD(User userCRUD) {
		this.userCRUD = userCRUD;
	}

	public List<TIPO_USUARIO> getTipoUsuerioList() {
		return tipoUsuerioList;
	}

	public void setTipoUsuerioList(List<TIPO_USUARIO> tipoUsuerioList) {
		this.tipoUsuerioList = tipoUsuerioList;
	}

	public TIPO_USUARIO getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(TIPO_USUARIO tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public boolean isEditor() {
		return editor;
	}

	public void setEditor(boolean editor) {
		this.editor = editor;
	}

	public boolean isEditarPassword() {
		return editarPassword;
	}

	public void setEditarPassword(boolean editarPassword) {
		this.editarPassword = editarPassword;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
