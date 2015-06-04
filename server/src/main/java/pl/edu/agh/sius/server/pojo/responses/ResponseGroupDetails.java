package pl.edu.agh.sius.server.pojo.responses;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import pl.edu.agh.sius.server.pojo.GroupDetails;
import pl.edu.agh.sius.server.pojo.OperationStatus;


@XmlRootElement(name="ResponseGroupDetails")
@XmlAccessorType(XmlAccessType.NONE)
public class ResponseGroupDetails implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	@XmlElement
	private OperationStatus operationStatus;
	
	@XmlElement
	private String msg;
	
	@XmlElement
	private GroupDetails groupDetails;

	// constructors
	public ResponseGroupDetails(){
		this.operationStatus = OperationStatus.ERROR;
	}
	
	public ResponseGroupDetails(GroupDetails groupDetails) {
		this.operationStatus = OperationStatus.ERROR;
		this.groupDetails = groupDetails;
	}
	
	public ResponseGroupDetails(OperationStatus operationStatus, String msg,	GroupDetails groupDetails) {
		this.operationStatus = operationStatus;
		this.msg = msg;
		this.groupDetails = groupDetails;
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

	public GroupDetails getGroupDetails() {
		return groupDetails;
	}

	public void setGroupDetails(GroupDetails groupDetails) {
		this.groupDetails = groupDetails;
	}
}
