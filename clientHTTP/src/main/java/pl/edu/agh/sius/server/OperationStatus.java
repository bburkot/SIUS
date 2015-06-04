
package pl.edu.agh.sius.server;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for operationStatus.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="operationStatus"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="SUCCEED"/&gt;
 *     &lt;enumeration value="WARN"/&gt;
 *     &lt;enumeration value="ERROR"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "operationStatus")
@XmlEnum
public enum OperationStatus {

    SUCCEED,
    WARN,
    ERROR;

    public String value() {
        return name();
    }

    public static OperationStatus fromValue(String v) {
        return valueOf(v);
    }

}
