package agh.sius.client.http.viewmodel;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Window;

public class SelectGroup {	
	@Wire	Window selectGroupPage;
	
	public static class Group {
		String name, id;
		public Group(){}
		public Group(String id, String name){this.name = name; this.id = id;}
		public String getName() { return name; } 	public void setName(String name) { this.name = name; }
		public String getId() { return id; } 		public void setId(String id) { this.id = id; } 
	}
	
	private ListModel<Group> groups;
	
	@Init
	public void init(){
		List<Group> temp = new ArrayList<>();
		temp.add(new Group("1", "Group 1"));
		temp.add(new Group("2", "Group 2"));
		temp.add(new Group("3", "Group 3"));
		
		setGroups(new ListModelList<SelectGroup.Group>(temp));
	}

	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
	}
	
	@Command
	public void selectGroup(@BindingParam("val") String id){
		Executions.sendRedirect("/group.zul?id=" + id);	
	}
	
	@Command
	public void addGroup(){
		((Window)Executions.createComponents("/dialog-windows/addGroup.zul", selectGroupPage, null)).doModal();
	}
	
	@Command
	public void findGroup(){
		((Window)Executions.createComponents("/dialog-windows/findGroup.zul", selectGroupPage, null)).doModal();
	}
	
	public ListModel<Group> getGroups() { return groups; }
	public void setGroups(ListModel<Group> groups) { this.groups = groups; }
}
