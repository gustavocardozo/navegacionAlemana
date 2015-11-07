package security;
import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.shiro.SecurityUtils;


@ManagedBean(name = "logoutController")
@RequestScoped
public class LogoutController {
	
	public static final String HOME_URL = "login.xhtml";
	

	public void submit(ActionEvent actionEvent) throws IOException {
		FacesContext context = FacesContext.getCurrentInstance();
		SecurityUtils.getSubject().logout();
		ExternalContext ec = context.getExternalContext();
		ec.invalidateSession();		
		ec.redirect(ec.getRequestContextPath()  + "/faces/" + HOME_URL);
	}
}

