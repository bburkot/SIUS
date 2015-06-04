package pl.edu.agh.sius.client.http.viewmodel;

import org.zkoss.bind.annotation.Command;
import org.zkoss.zk.ui.Executions;

public class Login {
	private String login;
	private String password;
	
	@Command
	public void signin(){
		Executions.sendRedirect("/selectGroup.zul");	
	}
	
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
}
