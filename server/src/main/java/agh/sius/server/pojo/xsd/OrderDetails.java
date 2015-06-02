package agh.sius.server.pojo.xsd;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="OrderDetails")
@XmlAccessorType(XmlAccessType.NONE)
public class OrderDetails implements Serializable {
	private static final long serialVersionUID = 1L;

	@XmlID
	@XmlElement
	private String id;
	
	@XmlElement
	private String msg;
	
	@XmlElement 
	private String title;
	
	@XmlElement
	private OrderState state;
	
	@XmlElement
	private Date date;
	
	@XmlElement
	private User realizedBy;
	
	@XmlElement
	@XmlElementWrapper
	private List<Product> products;
}
