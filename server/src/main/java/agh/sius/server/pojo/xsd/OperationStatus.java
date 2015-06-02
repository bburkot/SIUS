package agh.sius.server.pojo.xsd;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;

@XmlEnum
public enum OperationStatus{
	@XmlEnumValue("SUCCEED") 	SUCCEED, 
	@XmlEnumValue("WARN") 		WARN, 
	@XmlEnumValue("ERROR") 		ERROR;
	
	private OperationStatus(){}
}