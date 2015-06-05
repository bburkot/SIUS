package pl.edu.agh.sius.client.http.viewmodel;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import pl.edu.agh.sius.OperationStatus;
import pl.edu.agh.sius.OrderDetails;
import pl.edu.agh.sius.OrderState;
import pl.edu.agh.sius.Product;
import pl.edu.agh.sius.User;
import pl.edu.agh.sius.server.responses.ResponseOrderDetails;
import pl.edu.agh.sius.server.responses.ResponseProduct;
import pl.edu.agh.sius.server.responses.ResponseSimple;

public class Order {
	private Window modalWindow;
	private Component orderWindow;
	private User loggedUser;
	private ListModelList<Product> products;
	private Product newProduct;
	private OrderDetails orderDetails;
	
	@Wire Button addNewProductButton;
	@Wire Button closeOrderButton;
	
	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
		orderWindow = view;
	}
	
	@Init
	public void init(){
		loggedUser = (User) Sessions.getCurrent().getAttribute("user");
		if (loggedUser == null){			 
			Executions.sendRedirect("/");
			return;
		}
		//orderDetails = (OrderDetails) Sessions.getCurrent().getAttribute("order");
		if (orderDetails == null) {
			String orderID = Executions.getCurrent().getParameter("id");
			ResponseOrderDetails response = ServiceUtils.getService().getOrderDetails(orderID, loggedUser);
			if (response.getOperationStatus() == OperationStatus.SUCCEED)
				orderDetails = response.getOrderDetails();
			else 
				Executions.sendRedirect("/selectGroup.zul");
		}
		if (orderDetails.getProducts() == null)
			setProducts(new ListModelList<Product>());
		else
			setProducts(new ListModelList<Product>(orderDetails.getProducts().getProducts()));
	}
	
	@Command
	public void showNewProduct(){
		if (modalWindow != null)
			modalWindow.detach();
		
		newProduct = new Product();
		modalWindow = ((Window)Executions.createComponents("/dialog-windows/newProduct.zul", orderWindow, null));
		modalWindow.doModal();
	}
	
	@Command
	@NotifyChange("products")
	public void newProduct(){
		ResponseProduct response = ServiceUtils.getService()
				.newProductInOrder(orderDetails.getId(), newProduct, loggedUser);
		if (response.getOperationStatus() == OperationStatus.SUCCEED){
			products.add(response.getProducts());
			if (orderDetails.getProducts() == null)
				orderDetails.setProducts(new OrderDetails.Products());
			orderDetails.getProducts().getProducts().add(response.getProducts());
			Sessions.getCurrent().setAttribute("order", orderDetails);
			modalWindow.detach();
		} else {
			String msg = "Operacja nie powiodła sie\n";
			if (response.getMsg() != null)
				msg += "Powód: " + response.getMsg();
			Messagebox.show(msg, "Error", Messagebox.OK, Messagebox.ERROR);
		}
	}
	
	@Command
	public void showOrderDetails(){
		if (modalWindow != null)
			modalWindow.detach();
		
		modalWindow = ((Window)Executions.createComponents("/dialog-windows/orderDetails.zul", orderWindow, null));
		modalWindow.doModal();
	}

	@Command
	@NotifyChange("products")
	public void joinToProduct(@BindingParam("val") Product product){
		ResponseSimple response = ServiceUtils.getService().joinToProduct(product.getId(), loggedUser);
		if (response.getOperationStatus() == OperationStatus.SUCCEED){
			product.getUsers().getUsers().add(loggedUser);
		} else {
			String msg = "Operacja nie powiodła sie\n";
			if (response.getMsg() != null)
				msg += "Powód: " + response.getMsg();
			Messagebox.show(msg, "Error", Messagebox.OK, Messagebox.ERROR);
		}
	}
	
	@Command
	@NotifyChange("products")
	public void cancelProduct(
			@BindingParam("val") Product product,
			@BindingParam("user") User delUser
	){
		System.out.println(product);
		System.out.println(delUser);
		ResponseSimple response = ServiceUtils.getService()
				.cancelUserFromProduct(delUser.getId(), product.getId(), loggedUser);
		
		if (response.getOperationStatus() == OperationStatus.SUCCEED){
			if (product.getUsers().getUsers().size() > 1)
				product.getUsers().getUsers().remove(delUser);
			else
				products.remove(product);
		} else {
			String msg = "Operacja nie powiodła sie\n";
			if (response.getMsg() != null)
				msg += "Powód: " + response.getMsg();
			Messagebox.show(msg, "Error", Messagebox.OK, Messagebox.ERROR);
		}
	}
	
	@Command
	public boolean canCancel(Product product, User user){
		if (orderDetails.getState() == OrderState.OPEN
			&& ( user.getId().equals(loggedUser.getId()) 
				   || loggedUser.getId().equals(orderDetails.getRealizedBy().getId())
			)	)
			return true;
				
		return false;
	}
	
	public boolean getCanCloseOrder(){
		if (orderDetails == null)
			return false;
		return orderDetails.getState() == OrderState.OPEN 
				&& orderDetails.getRealizedBy().getId().equals(loggedUser.getId());
	}
	
	
	@Command
	@NotifyChange({"canCloseOrder", "getCanAddProduct()"})
	public void closeOrder(){
		ResponseSimple response = ServiceUtils.getService().closeOrder(orderDetails.getId(), loggedUser);
		if (response.getOperationStatus() == OperationStatus.SUCCEED){
			orderDetails.setState(OrderState.REALIZED);
			addNewProductButton.setVisible(false);
			closeOrderButton.setVisible(false);
			
		} else {
			String msg = "Operacja nie powiodła sie\n";
			if (response.getMsg() != null)
				msg += "Powód: " + response.getMsg();
			Messagebox.show(msg, "Error", Messagebox.OK, Messagebox.ERROR);
		}
	}
	

	public boolean getCanAddProduct(){
		if (orderDetails == null)
			return false;
		return orderDetails.getState() == OrderState.OPEN;
	}
	
	public ListModelList<Product> getProducts() {
		return products;
	}

	public void setProducts(ListModelList<Product> products) {
		this.products = products;
	}

	public Product getNewProduct() {
		return newProduct;
	}

	public void setNewProduct(Product newProduct) {
		this.newProduct = newProduct;
	}
	
}
