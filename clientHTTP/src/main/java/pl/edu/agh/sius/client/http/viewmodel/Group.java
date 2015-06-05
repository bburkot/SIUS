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
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import pl.edu.agh.sius.GroupDetails;
import pl.edu.agh.sius.OperationStatus;
import pl.edu.agh.sius.OrderDetails;
import pl.edu.agh.sius.OrderState;
import pl.edu.agh.sius.User;
import pl.edu.agh.sius.server.responses.ResponseGroupDetails;
import pl.edu.agh.sius.server.responses.ResponseOrderDetails;
import pl.edu.agh.sius.server.responses.ResponseSimple;

public class Group {
	private Component groupWindow;
	private User loggedUser;
	private GroupDetails groupDetails;
	private ListModelList<OrderDetails> openOrders;
	private ListModelList<OrderDetails> closedOrders;
	private ListModelList<User> members;
	private ListModelList<User> applicants;
	private User changeDebtUser;
	private Window modalWindow;
	private OrderDetails newOrder;
	
	
	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
		groupWindow = view;
	}
	
	@Init
	@NotifyChange({"members", "applicants", "openOrders", "closedOrders"})
	public void init(){
		loggedUser = (User) Sessions.getCurrent().getAttribute("user");
		
		if (loggedUser == null){			 
			Executions.sendRedirect("/");
			return;
		}
		String groupID = Executions.getCurrent().getParameter("id");
		
		ResponseGroupDetails response = ServiceUtils.getService().getGroupDetails(groupID, loggedUser);
		if (response.getOperationStatus() == OperationStatus.SUCCEED){
			groupDetails = response.getGroupDetails();
			members = new ListModelList<User>(groupDetails.getMemberUsers().getMemberUsers());

			if (groupDetails.getApplicantUsers() != null)
				applicants = new ListModelList<User>(groupDetails.getApplicantUsers().getApplicantUsers());
			else 
				applicants = new ListModelList<User>();
			
			
			openOrders = new ListModelList<OrderDetails>();
			closedOrders = new ListModelList<OrderDetails>();
			
			if (groupDetails.getOrders() != null)
				System.out.println("groupDetails.getOrders().getOrders().size() " + groupDetails.getOrders().getOrders().size());
			if (groupDetails.getOrders() != null)
				for (OrderDetails order : groupDetails.getOrders().getOrders())
					if (order.getState() == OrderState.OPEN)
						openOrders.add(order);
					else 
						closedOrders.add(order);
													
		} else {
			Executions.sendRedirect("/selectGroup.zul");
		}
	}
	
	@Command
	@NotifyChange({"members", "applicants"})
	public void acceptApplication(@BindingParam("user") User applicant){
		ResponseSimple response = ServiceUtils.getService()
				.acceptApplication(applicant.getId(), groupDetails.getId(), loggedUser);
		
		if (response.getOperationStatus() == OperationStatus.SUCCEED){
			applicants.remove(applicant);
			members.add(applicant);
		} else {
			String msg = "Operacja nie powiodła sie\n";
			if (response.getMsg() != null)
				msg += "Powód: " + response.getMsg();
			Messagebox.show(msg, "Error", Messagebox.OK, Messagebox.ERROR);
		}
	}
	
	@Command
	@NotifyChange({"members", "applicants"})
	public void removeUser(@BindingParam("user") User user){				
		ResponseSimple response = ServiceUtils.getService().removeUserFromGroup(user.getId(), groupDetails.getId(), loggedUser);
		
		if (response.getOperationStatus() == OperationStatus.SUCCEED){
			if (applicants.contains(user))
				applicants.remove(user);
			else if (members.contains(user))
				members.remove(user);
		} else {
			String msg = "Operacja nie powiodła sie\n";
			if (response.getMsg() != null)
				msg += "Powód: " + response.getMsg();
			Messagebox.show(msg, "Error", Messagebox.OK, Messagebox.ERROR);
		}	
		
		if (user.getId().equals(loggedUser.getId()))
			Executions.sendRedirect("/selectGroup.zul");
	}
	
	@Command
	public void showChangeDebtLimit(@BindingParam("val") User user){
		if (user.getId().equals(loggedUser.getId()))
			return;
		
		if (modalWindow != null)
			modalWindow.detach();
		
		setChangeDebtUser(user);
		
		modalWindow = ((Window)Executions.createComponents("/dialog-windows/changeDebtLimit.zul", groupWindow, null));
		modalWindow.doModal();
	}
	
	@Command
	@NotifyChange({"members"})
	public void changeDebtLimit(){
		ResponseSimple response = ServiceUtils.getService().changeDept(changeDebtUser.getId(),
				changeDebtUser.getMaxDept(), loggedUser);
		if (response.getOperationStatus() == OperationStatus.SUCCEED){
			modalWindow.detach();
		} else {
			String msg = "Operacja nie powiodła sie\n";
			if (response.getMsg() != null)
				msg += "Powód: " + response.getMsg();
			Messagebox.show(msg, "Error", Messagebox.OK, Messagebox.ERROR);
		}
	}
	
	@Command
	public void showNewOrder(){
		if (modalWindow != null)
			modalWindow.detach();
		
		newOrder = new OrderDetails();
		modalWindow = (Window) Executions.createComponents("/dialog-windows/newOrder.zul", groupWindow, null);
		modalWindow.doModal();
	}
	
	@Command
	@NotifyChange("openOrders")
	public void newOrder(){
		ResponseOrderDetails response = ServiceUtils.getService().addOrder(groupDetails.getId(), newOrder, loggedUser);
		if (response.getOperationStatus() == OperationStatus.SUCCEED){
			openOrders.add(response.getOrderDetails());
			modalWindow.detach();
		} else {
			String msg = "Operacja nie powiodła sie\n";
			if (response.getMsg() != null)
				msg += "Powód: " + response.getMsg();
			Messagebox.show(msg, "Error", Messagebox.OK, Messagebox.ERROR);
		}
	}
	
	@Command
	public void showOrder(@BindingParam("order") OrderDetails order){
		Sessions.getCurrent().setAttribute("order", order);
		Executions.sendRedirect("/order.zul?id=" + order.getId());
	}

	
	// getters and setters
	public ListModelList<OrderDetails> getOpenOrders() {
		return openOrders;
	}

	public ListModelList<OrderDetails> getClosedOrders() {
		return closedOrders;
	}

	public ListModelList<User> getMembers() {
		return members;
	}

	public ListModelList<User> getApplicants() {
		return applicants;
	}

	public User getChangeDebtUser() {
		return changeDebtUser;
	}

	public void setChangeDebtUser(User changeDebtUser) {
		this.changeDebtUser = changeDebtUser;
	}

	public OrderDetails getNewOrder() {
		return newOrder;
	}

	public void setNewOrder(OrderDetails newOrder) {
		this.newOrder = newOrder;
	}

	public GroupDetails getGroupDetails() {
		return groupDetails;
	}

}
