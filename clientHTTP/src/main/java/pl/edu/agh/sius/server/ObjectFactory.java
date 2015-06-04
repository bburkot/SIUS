
package pl.edu.agh.sius.server;

import java.math.BigDecimal;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the pl.edu.agh.sius package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Group_QNAME = new QName("http://www.agh.edu.pl/sius", "Group");
    private final static QName _GroupDetails_QNAME = new QName("http://www.agh.edu.pl/sius", "GroupDetails");
    private final static QName _OrderDetails_QNAME = new QName("http://www.agh.edu.pl/sius", "OrderDetails");
    private final static QName _Payment_QNAME = new QName("http://www.agh.edu.pl/sius", "Payment");
    private final static QName _Product_QNAME = new QName("http://www.agh.edu.pl/sius", "Product");
    private final static QName _ResponseGroupDetails_QNAME = new QName("http://www.agh.edu.pl/sius", "ResponseGroupDetails");
    private final static QName _ResponseGroups_QNAME = new QName("http://www.agh.edu.pl/sius", "ResponseGroups");
    private final static QName _ResponseOrderDetails_QNAME = new QName("http://www.agh.edu.pl/sius", "ResponseOrderDetails");
    private final static QName _ResponsePayments_QNAME = new QName("http://www.agh.edu.pl/sius", "ResponsePayments");
    private final static QName _ResponseProducts_QNAME = new QName("http://www.agh.edu.pl/sius", "ResponseProducts");
    private final static QName _ResponseSimple_QNAME = new QName("http://www.agh.edu.pl/sius", "ResponseSimple");
    private final static QName _ResponseUser_QNAME = new QName("http://www.agh.edu.pl/sius", "ResponseUser");
    private final static QName _ResponseUserDetails_QNAME = new QName("http://www.agh.edu.pl/sius", "ResponseUserDetails");
    private final static QName _User_QNAME = new QName("http://www.agh.edu.pl/sius", "User");
    private final static QName _UserDetails_QNAME = new QName("http://www.agh.edu.pl/sius", "UserDetails");
    private final static QName _String_QNAME = new QName("http://www.agh.edu.pl/sius", "String");
    private final static QName _UserLogout_QNAME = new QName("http://www.agh.edu.pl/sius", "UserLogout");
    private final static QName _LoggedUser_QNAME = new QName("http://www.agh.edu.pl/sius", "LoggedUser");
    private final static QName _Money_QNAME = new QName("http://www.agh.edu.pl/sius", "Money");
    private final static QName _UserRegister_QNAME = new QName("http://www.agh.edu.pl/sius", "UserRegister");
    private final static QName _UserLogged_QNAME = new QName("http://www.agh.edu.pl/sius", "UserLogged");
    private final static QName _UserProducts_QNAME = new QName("http://www.agh.edu.pl/sius", "UserProducts");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: pl.edu.agh.sius
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link UserDetails }
     * 
     */
    public UserDetails createUserDetails() {
        return new UserDetails();
    }

    /**
     * Create an instance of {@link User }
     * 
     */
    public User createUser() {
        return new User();
    }

    /**
     * Create an instance of {@link Product }
     * 
     */
    public Product createProduct() {
        return new Product();
    }

    /**
     * Create an instance of {@link OrderDetails }
     * 
     */
    public OrderDetails createOrderDetails() {
        return new OrderDetails();
    }

    /**
     * Create an instance of {@link GroupDetails }
     * 
     */
    public GroupDetails createGroupDetails() {
        return new GroupDetails();
    }

    /**
     * Create an instance of {@link Group }
     * 
     */
    public Group createGroup() {
        return new Group();
    }

    /**
     * Create an instance of {@link Payment }
     * 
     */
    public Payment createPayment() {
        return new Payment();
    }

    /**
     * Create an instance of {@link ResponseGroupDetails }
     * 
     */
    public ResponseGroupDetails createResponseGroupDetails() {
        return new ResponseGroupDetails();
    }

    /**
     * Create an instance of {@link ResponseGroups }
     * 
     */
    public ResponseGroups createResponseGroups() {
        return new ResponseGroups();
    }

    /**
     * Create an instance of {@link ResponseOrderDetails }
     * 
     */
    public ResponseOrderDetails createResponseOrderDetails() {
        return new ResponseOrderDetails();
    }

    /**
     * Create an instance of {@link ResponsePayments }
     * 
     */
    public ResponsePayments createResponsePayments() {
        return new ResponsePayments();
    }

    /**
     * Create an instance of {@link ResponseProducts }
     * 
     */
    public ResponseProducts createResponseProducts() {
        return new ResponseProducts();
    }

    /**
     * Create an instance of {@link ResponseSimple }
     * 
     */
    public ResponseSimple createResponseSimple() {
        return new ResponseSimple();
    }

    /**
     * Create an instance of {@link ResponseUser }
     * 
     */
    public ResponseUser createResponseUser() {
        return new ResponseUser();
    }

    /**
     * Create an instance of {@link ResponseUserDetails }
     * 
     */
    public ResponseUserDetails createResponseUserDetails() {
        return new ResponseUserDetails();
    }

    /**
     * Create an instance of {@link UserDetails.Groups }
     * 
     */
    public UserDetails.Groups createUserDetailsGroups() {
        return new UserDetails.Groups();
    }

    /**
     * Create an instance of {@link User.Groups }
     * 
     */
    public User.Groups createUserGroups() {
        return new User.Groups();
    }

    /**
     * Create an instance of {@link Product.Users }
     * 
     */
    public Product.Users createProductUsers() {
        return new Product.Users();
    }

    /**
     * Create an instance of {@link OrderDetails.Products }
     * 
     */
    public OrderDetails.Products createOrderDetailsProducts() {
        return new OrderDetails.Products();
    }

    /**
     * Create an instance of {@link GroupDetails.Orders }
     * 
     */
    public GroupDetails.Orders createGroupDetailsOrders() {
        return new GroupDetails.Orders();
    }

    /**
     * Create an instance of {@link GroupDetails.MemberUsers }
     * 
     */
    public GroupDetails.MemberUsers createGroupDetailsMemberUsers() {
        return new GroupDetails.MemberUsers();
    }

    /**
     * Create an instance of {@link GroupDetails.ApplicantUsers }
     * 
     */
    public GroupDetails.ApplicantUsers createGroupDetailsApplicantUsers() {
        return new GroupDetails.ApplicantUsers();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Group }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.agh.edu.pl/sius", name = "Group")
    public JAXBElement<Group> createGroup(Group value) {
        return new JAXBElement<Group>(_Group_QNAME, Group.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GroupDetails }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.agh.edu.pl/sius", name = "GroupDetails")
    public JAXBElement<GroupDetails> createGroupDetails(GroupDetails value) {
        return new JAXBElement<GroupDetails>(_GroupDetails_QNAME, GroupDetails.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OrderDetails }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.agh.edu.pl/sius", name = "OrderDetails")
    public JAXBElement<OrderDetails> createOrderDetails(OrderDetails value) {
        return new JAXBElement<OrderDetails>(_OrderDetails_QNAME, OrderDetails.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Payment }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.agh.edu.pl/sius", name = "Payment")
    public JAXBElement<Payment> createPayment(Payment value) {
        return new JAXBElement<Payment>(_Payment_QNAME, Payment.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Product }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.agh.edu.pl/sius", name = "Product")
    public JAXBElement<Product> createProduct(Product value) {
        return new JAXBElement<Product>(_Product_QNAME, Product.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResponseGroupDetails }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.agh.edu.pl/sius", name = "ResponseGroupDetails")
    public JAXBElement<ResponseGroupDetails> createResponseGroupDetails(ResponseGroupDetails value) {
        return new JAXBElement<ResponseGroupDetails>(_ResponseGroupDetails_QNAME, ResponseGroupDetails.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResponseGroups }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.agh.edu.pl/sius", name = "ResponseGroups")
    public JAXBElement<ResponseGroups> createResponseGroups(ResponseGroups value) {
        return new JAXBElement<ResponseGroups>(_ResponseGroups_QNAME, ResponseGroups.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResponseOrderDetails }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.agh.edu.pl/sius", name = "ResponseOrderDetails")
    public JAXBElement<ResponseOrderDetails> createResponseOrderDetails(ResponseOrderDetails value) {
        return new JAXBElement<ResponseOrderDetails>(_ResponseOrderDetails_QNAME, ResponseOrderDetails.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResponsePayments }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.agh.edu.pl/sius", name = "ResponsePayments")
    public JAXBElement<ResponsePayments> createResponsePayments(ResponsePayments value) {
        return new JAXBElement<ResponsePayments>(_ResponsePayments_QNAME, ResponsePayments.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResponseProducts }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.agh.edu.pl/sius", name = "ResponseProducts")
    public JAXBElement<ResponseProducts> createResponseProducts(ResponseProducts value) {
        return new JAXBElement<ResponseProducts>(_ResponseProducts_QNAME, ResponseProducts.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResponseSimple }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.agh.edu.pl/sius", name = "ResponseSimple")
    public JAXBElement<ResponseSimple> createResponseSimple(ResponseSimple value) {
        return new JAXBElement<ResponseSimple>(_ResponseSimple_QNAME, ResponseSimple.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResponseUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.agh.edu.pl/sius", name = "ResponseUser")
    public JAXBElement<ResponseUser> createResponseUser(ResponseUser value) {
        return new JAXBElement<ResponseUser>(_ResponseUser_QNAME, ResponseUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResponseUserDetails }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.agh.edu.pl/sius", name = "ResponseUserDetails")
    public JAXBElement<ResponseUserDetails> createResponseUserDetails(ResponseUserDetails value) {
        return new JAXBElement<ResponseUserDetails>(_ResponseUserDetails_QNAME, ResponseUserDetails.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link User }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.agh.edu.pl/sius", name = "User")
    public JAXBElement<User> createUser(User value) {
        return new JAXBElement<User>(_User_QNAME, User.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UserDetails }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.agh.edu.pl/sius", name = "UserDetails")
    public JAXBElement<UserDetails> createUserDetails(UserDetails value) {
        return new JAXBElement<UserDetails>(_UserDetails_QNAME, UserDetails.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.agh.edu.pl/sius", name = "String")
    public JAXBElement<String> createString(String value) {
        return new JAXBElement<String>(_String_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link User }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.agh.edu.pl/sius", name = "UserLogout")
    public JAXBElement<User> createUserLogout(User value) {
        return new JAXBElement<User>(_UserLogout_QNAME, User.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link User }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.agh.edu.pl/sius", name = "LoggedUser")
    public JAXBElement<User> createLoggedUser(User value) {
        return new JAXBElement<User>(_LoggedUser_QNAME, User.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.agh.edu.pl/sius", name = "Money")
    public JAXBElement<BigDecimal> createMoney(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_Money_QNAME, BigDecimal.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link User }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.agh.edu.pl/sius", name = "UserRegister")
    public JAXBElement<User> createUserRegister(User value) {
        return new JAXBElement<User>(_UserRegister_QNAME, User.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link User }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.agh.edu.pl/sius", name = "UserLogged")
    public JAXBElement<User> createUserLogged(User value) {
        return new JAXBElement<User>(_UserLogged_QNAME, User.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link User }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.agh.edu.pl/sius", name = "UserProducts")
    public JAXBElement<User> createUserProducts(User value) {
        return new JAXBElement<User>(_UserProducts_QNAME, User.class, null, value);
    }

}
