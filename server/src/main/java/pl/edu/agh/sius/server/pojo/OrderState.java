package pl.edu.agh.sius.server.pojo;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;

@XmlEnum
public enum OrderState {
	@XmlEnumValue("OPEN")		OPEN("Oczekuje na zamówienia"), 
	@XmlEnumValue("IN_PROGRES")	IN_PROGRES("W trakcie realizacji"), 
	@XmlEnumValue("REALIZED")	REALIZED("Zrealizowane");
	
	private OrderState(String value){
		this.value = value;
	}
	
	private String value;
	public String value() {
        return this.value;
    }
    public static OrderState fromValue(String v) {
        return valueOf(v);
    }
	@Override
	public String toString(){		
		return value;
	}
}
