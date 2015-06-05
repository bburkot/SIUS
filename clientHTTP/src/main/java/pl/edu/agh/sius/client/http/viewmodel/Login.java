package pl.edu.agh.sius.client.http.viewmodel;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import pl.edu.agh.sius.OperationStatus;
import pl.edu.agh.sius.User;
import pl.edu.agh.sius.server.responses.ResponseSimple;
import pl.edu.agh.sius.server.responses.ResponseUser;

public class Login {
	private String login = "user";
	private String password = "password";
	private Component view;
	private Window registerWindow;
	private User newUser;
	
	
	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
		this.view = view;
	}
	
	@Command
	public void signin(){		
		ResponseUser response = ServiceUtils.getService().login(login, password, "http");
		
		if (response.getOperationStatus() == OperationStatus.SUCCEED){
			Sessions.getCurrent().setAttribute("user", response.getUser());
			System.out.println("login: " + response.getUser());
			Executions.sendRedirect("/selectGroup.zul");
		} else {
			Messagebox.show("Podane dane są nieprawidłowe", "Error", Messagebox.OK, Messagebox.ERROR);
		}		
	}
	
	
	@Command
	public void register(){
		newUser = new User();
		registerWindow = ((Window)Executions.createComponents("/dialog-windows/register.zul", view, null));
		registerWindow.doModal();
	}
	
	@Command
	public void registerNewUser(String password){
		System.out.println(newUser + " password: " + password);
		
		registerWindow.detach();
		ResponseSimple response = ServiceUtils.getService().registerUser(newUser, password);
		if (response.getOperationStatus() == OperationStatus.SUCCEED){
			Messagebox.show("udało się zarejestrować użytkonika " + newUser.getLogin() +"\n", "Sukces", Messagebox.OK, Messagebox.INFORMATION);
		} else {
			String msg = "Nie udało się zarejestrować użytkonika " + newUser.getLogin() +"\n";
			if (response.getMsg() != null)
				msg += "Powód: " + response.getMsg();
			Messagebox.show(msg, "Error", Messagebox.OK, Messagebox.ERROR);
		}
	}
	
	
	// gettes and setters
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public User getNewUser() {
		return newUser;
	}

	public void setNewUser(User newUser) {
		this.newUser = newUser;
	}


}
