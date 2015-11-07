package security;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.sql.DataSource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.UserController;
import domain.User;
import domain.User.TIPO_USUARIO;
import service.UserService;


@ManagedBean(name = "loginController")
@SessionScoped
public class LoginController implements Serializable {

	private static final long serialVersionUID = 4319382794923129729L;	
	private static final Logger LOG = LoggerFactory.getLogger("retbam.web.editor");
		
	public static final String HOME_URL = "index.xhtml";
	public static final String HOME_OUTCOME = "home";	
	
	@ManagedProperty("#{dataSource}")
	private DataSource dataSource;
	
	@ManagedProperty("#{userService}")
	private UserService userService;
	
	private String username;
	private String password;

	public LoginController(){
		
	}
	
	public String submit() throws IOException{
		String url = null;
		
		if(null != username){
			try {
				buildRealmShiro();
				
				SecurityUtils.getSubject().login(
						new UsernamePasswordToken(username, password));
				
							
				url = HOME_OUTCOME;
		
			} catch (AuthenticationException e) {
				FacesMessage fmsg = new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Error", "");
				FacesContext.getCurrentInstance().addMessage(null, fmsg);

			} catch (Exception e) {
				FacesMessage fmsg = new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Error", "");
				FacesContext.getCurrentInstance().addMessage(null, fmsg);
			}
		}else{
			FacesMessage fmsg = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Error", "");
			FacesContext.getCurrentInstance().addMessage(null, fmsg);
		}
		
		password = null;
		return url;
	}
		
	
	private void buildRealmShiro() {
		try {
			DefaultWebSecurityManager dwsm = (DefaultWebSecurityManager) SecurityUtils
					.getSecurityManager();
			
			Realm provider = DatabaseAuthProviderInit.init(dataSource);
		
			if (null != dwsm.getRealms()) {
				dwsm.getRealms().clear();
			}
			
			dwsm.setRealm(provider);
			
		} catch (Exception e) {
			LOG.error("Error loading realm configuration shiro " , e);
		}
	}
	

	/*
	 * Getters & Setters
     */	
	
	public String getCurrentUserName(){
		@SuppressWarnings("unused")
		Subject currentUser = SecurityUtils.getSubject();
		//return null != currentUser.getPrincipal() ? currentUser.getPrincipal().toString() : "";
		return null != username ? username : "";
	}

	public String getUsername() {
		return null == username ? getCurrentUserName() : username ;
	}

	public String getPassword() {
		return password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
