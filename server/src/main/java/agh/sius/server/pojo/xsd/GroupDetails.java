package agh.sius.server.pojo.xsd;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="GroupDetails")
@XmlAccessorType(XmlAccessType.NONE)
public class GroupDetails {
	
	@XmlID
	@XmlElement
	private String id;
	
	@XmlElement
	private String name;
	
	@XmlElement
	@XmlElementWrapper
	private List<OrderDetails> orders;
	
	@XmlElement
	@XmlElementWrapper
	private List<User> memberUsers;
	
	@XmlElement
	@XmlElementWrapper
	private List<User> applicantUsers;
}
