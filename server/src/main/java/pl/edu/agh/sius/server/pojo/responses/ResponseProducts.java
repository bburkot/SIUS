package pl.edu.agh.sius.server.pojo.responses;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import pl.edu.agh.sius.server.pojo.OperationStatus;
import pl.edu.agh.sius.server.pojo.Product;

@XmlRootElement(name="ResponseProducts")
@XmlAccessorType(XmlAccessType.NONE)
public class ResponseProducts implements Serializable {
	private static final long serialVersionUID = 1L;
		
	@XmlElement
	private OperationStatus operationStatus;
	
	@XmlElement
	private String msg;
	
	@XmlElement
	private List<Product> products;	
	
	public ResponseProducts(){
		operationStatus = OperationStatus.ERROR;
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

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
