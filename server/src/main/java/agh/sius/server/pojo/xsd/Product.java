package agh.sius.server.pojo.xsd;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="Product")
@XmlAccessorType(XmlAccessType.NONE)
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@XmlID
	@XmlElement
	private String id;
	
	@XmlElement
	private String name;
	
	@XmlElement
	private BigDecimal cost;
		
	@XmlElement
	@XmlElementWrapper
	private List<User> users;
	
	@XmlElement
	private int maxUserPerProduct;
}
