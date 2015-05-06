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

public class Group {
	@Wire	Window groupPage;
	
	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
	}
	
	@Command
	public void newOrder(){
		((Window)Executions.createComponents("/dialog-windows/newOrder.zul", groupPage, null)).doModal();
	}
	
	@Command
	public void changeDebtLimit(@BindingParam("val") Object user){
		((Window)Executions.createComponents("/dialog-windows/changeDebtLimit.zul", groupPage, null)).doModal();
	}
}
