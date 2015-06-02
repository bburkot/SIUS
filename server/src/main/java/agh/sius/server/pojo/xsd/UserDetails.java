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


@XmlRootElement(name="UserDetails")
@XmlAccessorType(XmlAccessType.NONE)
public class UserDetails implements Serializable {
	private static final long serialVersionUID = 1L;	
	
	@XmlID
	@XmlElement
	private String id;
	
	@XmlElement
	private String name;
	
	@XmlElement
	private String surname;
	
	@XmlElement
	private String email;
	
	@XmlElement
	private BigDecimal maxDept;
	
	@XmlElement
	@XmlElementWrapper
	private List<Group> groups;
	
	@XmlElement
	private List<Billing> userBillings;
}
