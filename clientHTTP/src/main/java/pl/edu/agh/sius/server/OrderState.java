
package pl.edu.agh.sius.server;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for orderState.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="orderState"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="OPEN"/&gt;
 *     &lt;enumeration value="IN_PROGRES"/&gt;
 *     &lt;enumeration value="REALIZED"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "orderState")
@XmlEnum
public enum OrderState {

    OPEN,
    IN_PROGRES,
    REALIZED;

    public String value() {
        return name();
    }

    public static OrderState fromValue(String v) {
        return valueOf(v);
    }

}
