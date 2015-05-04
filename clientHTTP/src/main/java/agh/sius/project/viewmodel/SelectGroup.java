package agh.sius.project.viewmodel;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;

public class SelectGroup {
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

	
	@Command
	public void selectGroup(@BindingParam("val") String id){
		Executions.sendRedirect("/groupOrders.zul?id=" + id);	
	}
	
	public ListModel<Group> getGroups() { return groups; }
	public void setGroups(ListModel<Group> groups) { this.groups = groups; }
}
