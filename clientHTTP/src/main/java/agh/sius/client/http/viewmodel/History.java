package agh.sius.client.http.viewmodel;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Window;

public class History {
	@Wire	Window historyPage;
	
	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
	}
	
	@Command
	public void pay(@BindingParam("val") Object user){
		((Window)Executions.createComponents("/dialog-windows/payToUser.zul", historyPage, null)).doModal();
	}
	
	@Command
	public void billings(){
		((Window)Executions.createComponents("/dialog-windows/userBillings.zul", historyPage, null)).doModal();
	}
}
