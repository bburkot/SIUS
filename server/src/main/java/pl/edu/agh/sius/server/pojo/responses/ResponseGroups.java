package pl.edu.agh.sius.server.pojo.responses;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import pl.edu.agh.sius.server.pojo.Group;
import pl.edu.agh.sius.server.pojo.OperationStatus;

@XmlRootElement(name="ResponseGroups")
@XmlAccessorType(XmlAccessType.NONE)
public class ResponseGroups implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	@XmlElement
	private OperationStatus operationStatus;
	
	@XmlElement
	private String msg;
	
	@XmlElement
	private List<Group> groups;
	
	// constructors
	public ResponseGroups(){
		this.operationStatus = OperationStatus.ERROR;
	}
	
	public ResponseGroups(List<Group> groups) {
		this.operationStatus = OperationStatus.SUCCEED;
		this.groups = groups;
	}
	
	public ResponseGroups(OperationStatus operationStatus, String msg,	List<Group> groups) {
		this.operationStatus = operationStatus;
		this.msg = msg;
		this.groups = groups;
	}

	public void succeed() {
		operationStatus = OperationStatus.SUCCEED;		
	}
	public void failed(){
		operationStatus = OperationStatus.ERROR;
	}
	
	// getters and setters
	public OperationStatus getOperationStatus() {
		return operationStatus;
	}

	public void setOperationStatus(OperationStatus operationStatus) {
		this.operationStatus = operationStatus;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<Group> getGroups() {
		if (groups == null)
			return new ArrayList<Group>();
		return groups;
	}
	
	public void setGroups(List<Group> groups){
		this.groups = groups;
	}
}
