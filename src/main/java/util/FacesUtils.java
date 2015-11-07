package util;

import java.util.List;
import java.util.Locale;

import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;

public class FacesUtils {
	/*
	 * Messages
	 */
	
	public static void infoMessage(String message, Object...params) {
		FacesMessage fmsg = new FacesMessage(FacesMessage.SEVERITY_INFO,
				MsgBundleUtil.getMessage(message, params), "");
		FacesContext.getCurrentInstance().addMessage(null, fmsg);
	}
	
	public static FacesMessage infoMessages(String... messages) {		
		FacesMessage fmsg = new FacesMessage(FacesMessage.SEVERITY_INFO,
				MsgBundleUtil.getMessages(messages), "");
		FacesContext.getCurrentInstance().addMessage(null, fmsg);		
		return fmsg;
	}

	public static void warningMessage(String message, Object...params) {
		FacesMessage fmsg = new FacesMessage(FacesMessage.SEVERITY_WARN,
				MsgBundleUtil.getMessage(message, params), "");
		FacesContext.getCurrentInstance().addMessage(null, fmsg);
	}
	
	public static FacesMessage getErrorMessage(String message, Object...params) {
		FacesMessage fmsg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
				MsgBundleUtil.getMessage(message, params),"");
		return fmsg;
	}
	
	public static FacesMessage errorMessage(String message, Object...params) {
		FacesMessage fmsg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
				MsgBundleUtil.getMessage(message, params),"");
		FacesContext.getCurrentInstance().addMessage(null, fmsg);
		return fmsg;
	}
	
	public static FacesMessage getErrorMessages(String... messages) {		
		FacesMessage fmsg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
				MsgBundleUtil.getMessages(messages), "");		
		return fmsg;
	}
	
	public static FacesMessage errorMessages(String... messages) {		
		FacesMessage fmsg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
				MsgBundleUtil.getMessages(messages), "");
		FacesContext.getCurrentInstance().addMessage(null, fmsg);		
		return fmsg;
	}

	public static void fatalMessage(String message, Object...params) {
		FacesMessage fmsg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
				MsgBundleUtil.getMessage(message, params),"");
		FacesContext.getCurrentInstance().addMessage(null, fmsg);
	}	
	
	/*
	 * Dialogs
	 */
	
	public static void navigateToOutcome(String outcome){
		NavigationHandler navHandler = 
				FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
		navHandler.handleNavigation(FacesContext.getCurrentInstance(), null, outcome);
	}
	
	public static void dialogInfoMessage(String message) {
		FacesMessage fmsg = new FacesMessage(FacesMessage.SEVERITY_INFO,
				MsgBundleUtil.getMessage("msg.word.infomation"), MsgBundleUtil.getMessage(message));
		RequestContext.getCurrentInstance().showMessageInDialog(fmsg);
	}
	
	public static void dialogInfoMessage(String header, String message) {
		FacesMessage fmsg = new FacesMessage(FacesMessage.SEVERITY_INFO,
				MsgBundleUtil.getMessage(header), MsgBundleUtil.getMessage(message));
		RequestContext.getCurrentInstance().showMessageInDialog(fmsg);
	}

	public static void dialogWarningMessage(String message) {
		FacesMessage fmsg = new FacesMessage(FacesMessage.SEVERITY_WARN,
				MsgBundleUtil.getMessage("msg.word.warning"), MsgBundleUtil.getMessage(message));
		RequestContext.getCurrentInstance().showMessageInDialog(fmsg);
	}
	
	public static void dialogWarningMessage(String header, String message) {
		FacesMessage fmsg = new FacesMessage(FacesMessage.SEVERITY_WARN,
				MsgBundleUtil.getMessage(header), MsgBundleUtil.getMessage(message));
		RequestContext.getCurrentInstance().showMessageInDialog(fmsg);
	}
	
	public static void dialogErrorMessage(String message) {
		FacesMessage fmsg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
				MsgBundleUtil.getMessage("msg.word.error"), MsgBundleUtil.getMessage(message));
		RequestContext.getCurrentInstance().showMessageInDialog(fmsg);
	}
	
	public static void dialogErrorMessage(String header, String message) {
		FacesMessage fmsg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
				MsgBundleUtil.getMessage(header), MsgBundleUtil.getMessage(message));
		RequestContext.getCurrentInstance().showMessageInDialog(fmsg);
	}
	
	public static void dialogErrorMessage(String header, String message, Object... params) {
		FacesMessage fmsg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
				MsgBundleUtil.getMessage(header), MsgBundleUtil.getMessage(message, params));
		RequestContext.getCurrentInstance().showMessageInDialog(fmsg);
	}
	
	public static void dialogFatalMessage(String message) {
		FacesMessage fmsg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
				MsgBundleUtil.getMessage("message.common.fatalError"), MsgBundleUtil.getMessage(message));
		RequestContext.getCurrentInstance().showMessageInDialog(fmsg);
	}
	
	public static void dialogFatalMessage(String header, String message) {
		FacesMessage fmsg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
				MsgBundleUtil.getMessage(header), MsgBundleUtil.getMessage(message));
		RequestContext.getCurrentInstance().showMessageInDialog(fmsg);
	}
	
	/*
	 * Functions Session / Request maps
	 */
	
	public static Object getFromRequestParameterMap(String key){
		return FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap().get(key);
	}
	
	public static Object getFromRequestMap(String key){
		return FacesContext.getCurrentInstance().getExternalContext()
				.getRequestMap().get(key);
	}
	
	public static void addToRequestMap(String key, Object value){
		FacesContext.getCurrentInstance().getExternalContext()
			.getRequestMap().put(key, value);		
	}
	
	public static void removeFromRequestMap(String key){
		FacesContext.getCurrentInstance().getExternalContext()
			.getRequestMap().remove(key);		
	}
	
	public static Object getFromSessiontMap(String key){
		return FacesContext.getCurrentInstance().getExternalContext()
				.getSessionMap().get(key);
	}
	
	public static void addToSessionMap(String key, Object value){
		FacesContext.getCurrentInstance().getExternalContext()
			.getSessionMap().put(key, value);		
	}	
	
	public static void removeFromSessionMap(String key){
		FacesContext.getCurrentInstance().getExternalContext()
			.getSessionMap().remove(key);		
	}
	
	/* 
	 * Others
	 */
	
	public static Locale getViewLocale(){
		return FacesContext.getCurrentInstance().getViewRoot().getLocale();
	}
	
	public static HttpSession getHTTPSession(){
		return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	}
	
	public static UIComponent findComponent(List<UIComponent> components, String id){
		UIComponent component = null;
		
		for (UIComponent c: components) {
			if(id.equals(c.getId())){
				component = c;
				break;
			}
		}
		
		return component;
	}
	
	/**
	 * @param id of the searched component
	 * @return component in view
	 */
	public static UIComponent findInViewRoot(String id){
		return FacesContext.getCurrentInstance().getViewRoot().findComponent(id);
	}
	
		
	public static <T> void putBeanInSessionMap(T bean, String beanName) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getSessionMap().put(beanName, bean);
	}
	
	public static void executeScript(String script){
		RequestContext.getCurrentInstance().execute(script);
	}
	
	public static void updateComponent(String compId){
		RequestContext.getCurrentInstance().update(compId);
	}
	
}
