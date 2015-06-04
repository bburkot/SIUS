package pl.edu.agh.sius.server.pojo.responses;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import pl.edu.agh.sius.server.pojo.OperationStatus;
import pl.edu.agh.sius.server.pojo.OrderDetails;

@XmlRootElement(name="ResponseOrderDetails")
@XmlAccessorType(XmlAccessType.NONE)
public class ResponseOrderDetails implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	@XmlElement
	private OperationStatus operationStatus;
	
	@XmlElement
	private String msg;
	
	@XmlElement
	private OrderDetails orderDetails;
	
	// constructors
	public ResponseOrderDetails(){
		this.operationStatus = OperationStatus.ERROR;
	}
	
	public ResponseOrderDetails(OrderDetails orderDetails) {
		this.operationStatus = OperationStatus.ERROR;
		this.orderDetails = orderDetails;
	}
	
	public ResponseOrderDetails(OperationStatus operationStatus, String msg, OrderDetails orderDetails) {
		this.operationStatus = operationStatus;
		this.msg = msg;
		this.orderDetails = orderDetails;
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

	public OrderDetails getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(OrderDetails orderDetails) {
		this.orderDetails = orderDetails;
	}
}
