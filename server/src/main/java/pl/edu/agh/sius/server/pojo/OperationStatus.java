package pl.edu.agh.sius.server.pojo;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;

@XmlEnum
public enum OperationStatus{
	@XmlEnumValue("SUCCEED") 	SUCCEED, 
	@XmlEnumValue("WARN") 		WARN, 
	@XmlEnumValue("ERROR") 		ERROR;
	
	private OperationStatus(){}
}