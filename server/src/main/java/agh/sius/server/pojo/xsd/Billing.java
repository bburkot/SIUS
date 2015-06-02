package agh.sius.server.pojo.xsd;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Billing")
@XmlAccessorType(XmlAccessType.NONE)
public class Billing implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@XmlID
	@XmlElement
	private String id;
	
	@XmlElement
	private User from;
	
	@XmlElement
	private User to;
	
	@XmlElement
	private BigDecimal amount;
}
