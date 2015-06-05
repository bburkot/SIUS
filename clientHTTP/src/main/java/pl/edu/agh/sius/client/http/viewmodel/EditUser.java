package pl.edu.agh.sius.client.http.viewmodel;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import pl.edu.agh.sius.OperationStatus;
import pl.edu.agh.sius.User;
import pl.edu.agh.sius.server.responses.ResponseSimple;

public class EditUser {
	private User loggedUser;
	private User user;
	
	@Init
	public void init(){
		loggedUser = (User) Sessions.getCurrent().getAttribute("user");
		
		if (loggedUser == null){			 
			Executions.sendRedirect("/");
			return;
		}
		
		user = new User(loggedUser);
	}
	
	@Command
	public void updateData(String password){
		ResponseSimple response = ServiceUtils.getService().updateUser(user, password);
		if (response.getOperationStatus() == OperationStatus.SUCCEED){
			loggedUser = user;
			Sessions.getCurrent().setAttribute("user", user);
			Executions.sendRedirect("/selectGroup.zul");
		} else {
			String msg = "Operacja nie powiodła sie\n";
			if (response.getMsg() != null)
				msg += "Powód: " + response.getMsg();
			Messagebox.show(msg, "Error", Messagebox.OK, Messagebox.ERROR);
		}
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
	
}
