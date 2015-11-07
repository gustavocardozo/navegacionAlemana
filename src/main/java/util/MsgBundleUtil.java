package util;

import java.text.MessageFormat;
import java.util.ResourceBundle;

import javax.faces.context.FacesContext;


public class MsgBundleUtil {
	
	private static final String BASE_NAME = "messages";
	
	public static String getMessage(String message, Object...params) {
		String detail = message;
		
		try {
			ResourceBundle msg = getResourceBundle();
			detail = MessageFormat.format(msg.getString(message), params);
		} catch (Exception e) {
			
		}
		
		return detail;
	}

	public static String getMessages(String... messages) {
		String message = "";
		int count = 0;

		for (String msg : messages) {
			message = message.concat(MsgBundleUtil.getMessage(msg));
			if(0 == count){
				message = message.concat(": ");
			}else if(count < messages.length-1){
				message = message.concat(", ");				
			}else{
				message = message.concat(".");
			}
						
			count ++;
		}
		
		return message;
	}

	public static ResourceBundle getResourceBundle() {
		FacesContext context = FacesContext.getCurrentInstance();
		ResourceBundle msg = 
			ResourceBundle.getBundle(BASE_NAME, context.getViewRoot().getLocale());
		
		return msg;
	}
}
