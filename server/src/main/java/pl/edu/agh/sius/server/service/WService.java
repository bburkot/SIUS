package pl.edu.agh.sius.server.service;

import java.math.BigDecimal;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;

import pl.edu.agh.sius.server.pojo.Group;
import pl.edu.agh.sius.server.pojo.OrderDetails;
import pl.edu.agh.sius.server.pojo.Product;
import pl.edu.agh.sius.server.pojo.User;
import pl.edu.agh.sius.server.pojo.responses.ResponseBalances;
import pl.edu.agh.sius.server.pojo.responses.ResponseGroupDetails;
import pl.edu.agh.sius.server.pojo.responses.ResponseGroups;
import pl.edu.agh.sius.server.pojo.responses.ResponseOrderDetails;
import pl.edu.agh.sius.server.pojo.responses.ResponsePayments;
import pl.edu.agh.sius.server.pojo.responses.ResponseProduct;
import pl.edu.agh.sius.server.pojo.responses.ResponseProducts;
import pl.edu.agh.sius.server.pojo.responses.ResponseSimple;
import pl.edu.agh.sius.server.pojo.responses.ResponseUser;
import pl.edu.agh.sius.server.pojo.responses.ResponseUserDetails;

@WebService (name="WService", targetNamespace="http://www.agh.edu.pl/sius")
@SOAPBinding(parameterStyle = ParameterStyle.BARE, use=Use.LITERAL, style=Style.DOCUMENT)
public interface WService {
	
// TODO USER OPERATION	
	
	@WebMethod(action="registerUser", operationName="registerUser")
	@WebResult(name = "ResponseSimple")
	public ResponseSimple registerUser (
			@WebParam(name = "UserRegister",   partName = "User") 	User user,
			@WebParam(name = "String", partName = "UserPassword") 	String password
	);
	
	
	@WebMethod(action="login", operationName="login")
	@WebResult(name = "ResponseUser")
	public ResponseUser login(
			@WebParam(name = "String", partName = "UserLogin") 				String login, 
			@WebParam(name = "String", partName = "UserPassword") 			String password, 
			@WebParam(name = "String", partName = "AuthorizationProvider") 	String authorizationProvider
	);
	
	@WebMethod(action="logout", operationName="logout")
	@WebResult(name="ResponseSimple")
	public ResponseSimple logout(
			@WebParam(name = "UserLogout",   partName = "User") 	User user 
	);

	@WebMethod(action="getUserDetails", operationName="getUserDetails")
	@WebResult(name = "ResponseUserDetails")
	public ResponseUserDetails getUserDetails(
			@WebParam(name = "User",   partName = "User") 	User user 
	);
	
	@WebMethod(action="updateUser", operationName="updateUser")
	@WebResult(name="ResponseSimple")
	public ResponseSimple updateUser(
			@WebParam(name = "User",   partName = "User") 			User user, 
			@WebParam(name = "String", partName = "UserPassword")	String userPassword
	);

	@WebMethod(action="applicateToGroup", operationName="applicateToGroup")
	@WebResult(name="ResponseSimple")
	public ResponseSimple applicateToGroup(
			@WebParam(name = "String", partName = "GroupID") 	String groupID,
			@WebParam(name = "User",   partName = "User") 		User user
	);
	
	@WebMethod(action="acceptApplication", operationName="acceptApplication")
	@WebResult(name="ResponseSimple")
	public ResponseSimple acceptApplication(
			@WebParam(name = "String", partName = "acceptUserId") 	String acceptUserId,
			@WebParam(name = "String", partName = "GroupID") 		String groupID,
			@WebParam(name = "User",   partName = "User") 			User user
	);
	
	@WebMethod(action="removeUserFromGroup", operationName="removeUserFromGroup")
	@WebResult(name="ResponseSimple")
	public ResponseSimple removeUserFromGroup(
			@WebParam(name = "String", partName = "removeUserId") 	String removeUserId,
			@WebParam(name = "String", partName = "GroupID") 		String groupID,
			@WebParam(name = "User",   partName = "User") 			User user 
	);
	
	
// TODO GROUP OPERATIONS	
	
	@WebMethod(action="getGroupDetails", operationName="getGroupDetails")
	@WebResult(name="ResponseGroupDetails")
	public ResponseGroupDetails getGroupDetails(
			@WebParam(name = "String", partName = "GroupID") 	String groupID,
			@WebParam(name = "User",   partName = "User") 		User user 
	);
	
	@WebMethod(action="addGroup", operationName="addGroup")
	@WebResult(name="ResponseGroupDetails")
	public ResponseGroupDetails addGroup(
			@WebParam(name = "Group",	partName = "Group") 	Group newGroup,
			@WebParam(name = "User",	partName = "User") 		User user
	);
	
	@WebMethod(action="findGroup", operationName="findGroup")
	@WebResult(name="ResponseGroups")
	public ResponseGroups findGroup(
			@WebParam(name = "String", partName = "GroupNamePattern")	String namePattern,
			@WebParam(name = "User",   partName = "User") 				User user
	);
	
// TODO ORDER OPERATIONS
	@WebMethod(action="getOrderDetails", operationName="getOrderDetails")
	@WebResult(name="ResponseOrderDetails", targetNamespace="http://www.agh.edu.pl/sius")
	public ResponseOrderDetails getOrderDetails(
			@WebParam(name = "String", partName = "OrderID") 	String orderID,
			@WebParam(name = "User",   partName = "User") 		User user
	);

	@WebMethod(action="addOrder", operationName="addOrder")
	@WebResult(name="ResponseOrderDetails")
	public ResponseOrderDetails addOrder(
			@WebParam(name = "String", 			partName = "GroupID")  			String groupId,
			@WebParam(name = "OrderDetails", 	partName = "OrderDetails") 		OrderDetails newOrder,
			@WebParam(name = "User",   			partName = "User") 				User user
	);
	
	@WebMethod(action="closeOrder", operationName="closeOrder")
	@WebResult(name="ResponseSimple")
	public ResponseSimple closeOrder(
			@WebParam(name = "String", partName = "OrderID") 	String orderID,
			@WebParam(name = "User",   partName = "User") 		User user
	);
	
// TODO BILING OPERATIONS
	@WebMethod(action="changeDept", operationName="changeDept")
	@WebResult(name="ResponseSimple")
	public ResponseSimple changeDept(
			@WebParam(name = "String", partName = "UserID") 		String toWhomID, 
			@WebParam(name = "Money",  partName = "NewUserMaxDept")	BigDecimal newMaxDept,
			@WebParam(name = "User",   partName = "User") 			User user
	);
	
	@WebMethod(action="payMoney", operationName="payMoney")
	@WebResult(name="ResponseSimple")
	public ResponseSimple payMoney (
			@WebParam(name = "String", partName = "StringID")	String toWhomID, 
			@WebParam(name = "Money",  partName = "Amount")		BigDecimal amount,
			@WebParam(name = "User",   partName = "User") 		User user
	);
	
	@WebMethod(action="acceptPayment", operationName="acceptPayment")
	@WebResult(name="ResponseSimple")
	public ResponseSimple acceptPayment(
			@WebParam(name = "String", partName = "StringID")	String paymentID,
			@WebParam(name = "User",   partName = "User") 		User user
	);
	
	@WebMethod(action="cancelPayment", operationName="cancelPayment")
	@WebResult(name="ResponseSimple")
	public ResponseSimple cancelPayment (
			@WebParam(name = "String", partName = "StringID")	String paymentID,
			@WebParam(name = "User",   partName = "User") 		User user
	);
	
	
	@WebMethod(action="getUserPayments", operationName="getUserPayments")
	@WebResult(name="ResponsePayments")
	public ResponsePayments getUserPayments(
			@WebParam(name = "UserPayments",   partName = "User") 	User user
	);
	
	@WebMethod(action="getUsersBalances", operationName="getUsersBalances")
	@WebResult(name="ResponseBalances")
	public ResponseBalances getUsersBalances (
			@WebParam(name = "UserBalances",   partName = "User") 	User user
	);
	
// TODO PRODUCT OPERATIONS
	@WebMethod(action="newProductInOrder", operationName="newProductInOrder")
	@WebResult(name="ResponseProduct")
	public ResponseProduct newProductInOrder(
			@WebParam(name = "String", 		partName = "OrderID") 	String orderId,
			@WebParam(name = "Product", 	partName = "Product")	Product newProduct,
			@WebParam(name = "User",   		partName = "User") 		User user
	);
	
	@WebMethod(action="joinToProduct", operationName="joinToProduct")
	@WebResult(name="ResponseSimple")
	public ResponseSimple joinToProduct(
			@WebParam(name = "String", partName = "ProductID")	String productID,
			@WebParam(name = "User",   partName = "User") 		User user
	);
	
	@WebMethod(action="getProductsOrderedByUser", operationName="getProductsOrderedByUser")
	@WebResult(name="ResponseProducts")
	public ResponseProducts getProductsOrderedByUser(
			@WebParam(name = "UserProducts",   partName = "User") 		User user
	);
	
	@WebMethod(action="cancelUserFromProduct", operationName="cancelUserFromProduct")
	@WebResult(name="ResponseSimple")
	public ResponseSimple cancelUserFromProduct(
			@WebParam(name = "String", partName = "UserID")		String userID,
			@WebParam(name = "String", partName = "ProductID")	String productID,
			@WebParam(name = "User",   partName = "User") 		User user
	);
}
