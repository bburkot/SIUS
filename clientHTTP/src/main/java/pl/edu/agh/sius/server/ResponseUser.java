
package pl.edu.agh.sius.server;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for responseUser complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="responseUser"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="operationStatus" type="{http://www.agh.edu.pl/sius}operationStatus" minOccurs="0"/&gt;
 *         &lt;element name="msg" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="user" type="{http://www.agh.edu.pl/sius}user" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "responseUser", propOrder = {
    "operationStatus",
    "msg",
    "user"
})
public class ResponseUser {

    @XmlSchemaType(name = "string")
    protected OperationStatus operationStatus;
    protected String msg;
    protected User user;

    /**
     * Gets the value of the operationStatus property.
     * 
     * @return
     *     possible object is
     *     {@link OperationStatus }
     *     
     */
    public OperationStatus getOperationStatus() {
        return operationStatus;
    }

    /**
     * Sets the value of the operationStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link OperationStatus }
     *     
     */
    public void setOperationStatus(OperationStatus value) {
        this.operationStatus = value;
    }

    /**
     * Gets the value of the msg property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMsg() {
        return msg;
    }

    /**
     * Sets the value of the msg property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMsg(String value) {
        this.msg = value;
    }

    /**
     * Gets the value of the user property.
     * 
     * @return
     *     possible object is
     *     {@link User }
     *     
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the value of the user property.
     * 
     * @param value
     *     allowed object is
     *     {@link User }
     *     
     */
    public void setUser(User value) {
        this.user = value;
    }

}
