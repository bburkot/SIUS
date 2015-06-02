package agh.sius.server.pojo.xsd;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Group")
@XmlAccessorType(XmlAccessType.NONE)
public class Group implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@XmlElement
	@XmlID
	private String id;
	
	@XmlElement
	private String name;
}
