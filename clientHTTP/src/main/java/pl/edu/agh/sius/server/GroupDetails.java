
package pl.edu.agh.sius.server;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for groupDetails complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="groupDetails"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}ID" minOccurs="0"/&gt;
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="orders" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="orders" type="{http://www.agh.edu.pl/sius}orderDetails" maxOccurs="unbounded" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="memberUsers" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="memberUsers" type="{http://www.agh.edu.pl/sius}user" maxOccurs="unbounded" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="applicantUsers" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="applicantUsers" type="{http://www.agh.edu.pl/sius}user" maxOccurs="unbounded" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "groupDetails", propOrder = {
    "id",
    "name",
    "orders",
    "memberUsers",
    "applicantUsers"
})
public class GroupDetails {

    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;
    protected String name;
    protected GroupDetails.Orders orders;
    protected GroupDetails.MemberUsers memberUsers;
    protected GroupDetails.ApplicantUsers applicantUsers;

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the orders property.
     * 
     * @return
     *     possible object is
     *     {@link GroupDetails.Orders }
     *     
     */
    public GroupDetails.Orders getOrders() {
        return orders;
    }

    /**
     * Sets the value of the orders property.
     * 
     * @param value
     *     allowed object is
     *     {@link GroupDetails.Orders }
     *     
     */
    public void setOrders(GroupDetails.Orders value) {
        this.orders = value;
    }

    /**
     * Gets the value of the memberUsers property.
     * 
     * @return
     *     possible object is
     *     {@link GroupDetails.MemberUsers }
     *     
     */
    public GroupDetails.MemberUsers getMemberUsers() {
        return memberUsers;
    }

    /**
     * Sets the value of the memberUsers property.
     * 
     * @param value
     *     allowed object is
     *     {@link GroupDetails.MemberUsers }
     *     
     */
    public void setMemberUsers(GroupDetails.MemberUsers value) {
        this.memberUsers = value;
    }

    /**
     * Gets the value of the applicantUsers property.
     * 
     * @return
     *     possible object is
     *     {@link GroupDetails.ApplicantUsers }
     *     
     */
    public GroupDetails.ApplicantUsers getApplicantUsers() {
        return applicantUsers;
    }

    /**
     * Sets the value of the applicantUsers property.
     * 
     * @param value
     *     allowed object is
     *     {@link GroupDetails.ApplicantUsers }
     *     
     */
    public void setApplicantUsers(GroupDetails.ApplicantUsers value) {
        this.applicantUsers = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="applicantUsers" type="{http://www.agh.edu.pl/sius}user" maxOccurs="unbounded" minOccurs="0"/&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "applicantUsers"
    })
    public static class ApplicantUsers {

        protected List<User> applicantUsers;

        /**
         * Gets the value of the applicantUsers property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the applicantUsers property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getApplicantUsers().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link User }
         * 
         * 
         */
        public List<User> getApplicantUsers() {
            if (applicantUsers == null) {
                applicantUsers = new ArrayList<User>();
            }
            return this.applicantUsers;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="memberUsers" type="{http://www.agh.edu.pl/sius}user" maxOccurs="unbounded" minOccurs="0"/&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "memberUsers"
    })
    public static class MemberUsers {

        protected List<User> memberUsers;

        /**
         * Gets the value of the memberUsers property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the memberUsers property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getMemberUsers().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link User }
         * 
         * 
         */
        public List<User> getMemberUsers() {
            if (memberUsers == null) {
                memberUsers = new ArrayList<User>();
            }
            return this.memberUsers;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="orders" type="{http://www.agh.edu.pl/sius}orderDetails" maxOccurs="unbounded" minOccurs="0"/&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "orders"
    })
    public static class Orders {

        protected List<OrderDetails> orders;

        /**
         * Gets the value of the orders property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the orders property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getOrders().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link OrderDetails }
         * 
         * 
         */
        public List<OrderDetails> getOrders() {
            if (orders == null) {
                orders = new ArrayList<OrderDetails>();
            }
            return this.orders;
        }

    }

}
