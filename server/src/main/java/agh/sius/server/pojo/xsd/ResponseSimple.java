package agh.sius.server.pojo.xsd;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="ResponseSimple")
@XmlAccessorType(XmlAccessType.NONE)
public class ResponseSimple {
	@XmlElement
	private OperationStatus operationStatus;
	
	@XmlElement
	private String msg;
}
