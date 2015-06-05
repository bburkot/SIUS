package pl.edu.agh.sius.client.http.viewmodel;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;

import pl.edu.agh.sius.User;

public class Header {
	private User loggedUser;

	
	@Init
	public void init(){
		loggedUser = (User) Sessions.getCurrent().getAttribute("user");
		
		if (loggedUser == null){			
			Executions.sendRedirect("/");
			return;
		}		
	}

	@Command
	public void logout(){
		ServiceUtils.getService().logout(loggedUser);
		Sessions.getCurrent().removeAttribute("user");
		Executions.sendRedirect("/");
	}

	public User getLoggedUser() {
		return loggedUser;
	}
	public void setLoggedUser(User loggedUser) {
		this.loggedUser = loggedUser;
	}
}
