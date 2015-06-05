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
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import pl.edu.agh.sius.Group;
import pl.edu.agh.sius.OperationStatus;
import pl.edu.agh.sius.User;
import pl.edu.agh.sius.server.responses.ResponseGroupDetails;
import pl.edu.agh.sius.server.responses.ResponseGroups;
import pl.edu.agh.sius.server.responses.ResponseSimple;

public class SelectGroup {	
	private Component selectGroupPage;
	private User loggedUser;
	private ListModel<Group> groups;
	private Window modalWindow;
	private Group newGroup;
	private String findGroupPattern;
	private ListModel<Group> findGroups;	
	
	@Init
	public void init(){
		loggedUser = (User) Sessions.getCurrent().getAttribute("user");
		
		if (loggedUser == null){			
			Executions.sendRedirect("/");
			return;
		}
		if (loggedUser.getGroups() == null)
			setGroups(new ListModelList<Group>());
		else
			setGroups(new ListModelList<Group>(loggedUser.getGroups().getGroups()));		
	}

	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
		selectGroupPage = view;
	}
	
	@Command
	public void selectGroup(@BindingParam("val") String id){
		Executions.sendRedirect("/group.zul?id=" + id);	
	}
	
	@Command
	public void showAddGroup(){

		if (modalWindow != null)
			modalWindow.detach();
		newGroup = new Group();
		modalWindow = ((Window)Executions.createComponents("/dialog-windows/addGroup.zul", selectGroupPage, null));
		modalWindow.doModal();
	}
	
	@Command
	public void addGroup(){
		System.out.println("client " + newGroup);
		ResponseGroupDetails response = ServiceUtils.getService().addGroup(newGroup, loggedUser);
		if (response.getOperationStatus() == OperationStatus.SUCCEED){
			System.out.println(response.getGroupDetails());
			newGroup.setId(response.getGroupDetails().getId());
			loggedUser.getGroups().getGroups().add(newGroup);
			Sessions.getCurrent().setAttribute("user", loggedUser);
			
			Executions.sendRedirect("/group.zul?id=" + response.getGroupDetails().getId());	
		} else {
			String msg = "Nie można dodać nowej grupu o nazwie " + newGroup.getName() +"\n";
			if (response.getMsg() != null)
				msg += "Powód: " + response.getMsg();
			Messagebox.show(msg, "Error", Messagebox.OK, Messagebox.ERROR);
		}
	}
	
	@Command
	public void showFindGroup(){
		findGroups = new ListModelList<Group>();
		findGroupPattern = "";
		
		if (modalWindow != null)
			modalWindow.detach();
		modalWindow = ((Window)Executions.createComponents("/dialog-windows/findGroup.zul", selectGroupPage, null));
		modalWindow .doModal();
	}
	
	@Command
	@NotifyChange("findGroups")
	public void findGroup(){
		System.out.println(findGroupPattern);
		ResponseGroups response = ServiceUtils.getService().findGroup(findGroupPattern, loggedUser);
		if (response.getOperationStatus() == OperationStatus.SUCCEED){			
			findGroups = new ListModelList<Group>(response.getGroups());
		} else {
			String msg = "Operacja wyszukania grupy zakonczyła się niepomyślnie\n";
			if (response.getMsg() != null)
				msg += "Powód: " + response.getMsg();
			Messagebox.show(msg, "Error", Messagebox.OK, Messagebox.ERROR);
		}	
	}

	@Command
	public void applicateToGroup(@BindingParam("groupId") String groupId){
		
		ResponseSimple response = ServiceUtils.getService().applicateToGroup(groupId, loggedUser);
		if (response.getOperationStatus() == OperationStatus.SUCCEED){			
			Messagebox.show("Wysłano prośbę o przyjęcie", "Sukces", Messagebox.OK, Messagebox.INFORMATION);
		} else {
			String msg = "Operacja zakonczyła się niepomyślnie\n";
			if (response.getMsg() != null)
				msg += "Powód: " + response.getMsg();
			Messagebox.show(msg, "Error", Messagebox.OK, Messagebox.ERROR);
		}	
	}
	
	
	// getters and setters
	public ListModel<Group> getGroups() { return groups; }
	public void setGroups(ListModel<Group> groups) { this.groups = groups; }

	public Group getNewGroup() {
		return newGroup;
	}

	public void setNewGroup(Group newGroup) {
		this.newGroup = newGroup;
	}

	public String getFindGroupPattern() {
		return findGroupPattern;
	}

	public void setFindGroupPattern(String findGroupPattern) {
		this.findGroupPattern = findGroupPattern;
	}

	public ListModel<Group> getFindGroups() {
		return findGroups;
	}

	public void setFindGroups(ListModel<Group> findGroups) {
		this.findGroups = findGroups;
	}
}
