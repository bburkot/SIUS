package agh.sius.client.http.viewmodel;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Window;

public class Order {
	@Wire	Window orderPage;
	
	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
	}
	
	@Command
	public void newProduct(){
		((Window)Executions.createComponents("/dialog-windows/newProduct.zul", orderPage, null)).doModal();
	}
	
	@Command
	public void orderDetails(){
		((Window)Executions.createComponents("/dialog-windows/orderDetails.zul", orderPage, null)).doModal();
	}
	
}
