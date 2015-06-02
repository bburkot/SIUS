package agh.sius.server.service;

import java.math.BigDecimal;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;

import agh.sius.server.pojo.xsd.Group;
import agh.sius.server.pojo.xsd.OrderDetails;
import agh.sius.server.pojo.xsd.Product;
import agh.sius.server.pojo.xsd.Response;
import agh.sius.server.pojo.xsd.ResponseSimple;
import agh.sius.server.pojo.xsd.User;
import agh.sius.server.pojo.xsd.GroupDetails;
import agh.sius.server.pojo.xsd.UserDetails;

@WebService (name="WService", targetNamespace="http://www.agh.edu.pl/sius")
@SOAPBinding(parameterStyle = ParameterStyle.BARE, use=Use.LITERAL, style=Style.DOCUMENT)
public interface WService {
	
// USER OPERATION	
	
	@WebMethod(action="login", operationName="login")
	@WebResult(name = "ResponseUser")
	public Response<User> login(
			@WebParam(name = "String", partName = "UserLogin") 				String login, 
			@WebParam(name = "String", partName = "UserPassword") 			String password, 
			@WebParam(name = "String", partName = "AuthorizationProvider") 	String authorizationProvider
	);
	
	@WebMethod(action="logout", operationName="logout")
	@WebResult(name="ResponseSimple")
	public ResponseSimple logout(
			@WebParam(name = "String", partName = "UserID") 		String userID,
			@WebParam(name = "String", partName = "UserToken") 		String userToken 
	);

	@WebMethod(action="getUserDetails", operationName="getUserDetails")
	@WebResult(name = "ResponseUserDetails")
	public Response<UserDetails> getUserDetails(
			@WebParam(name = "String", partName = "UserID") 	String userID,
			@WebParam(name = "String", partName = "UserToken") 	String userToken 
	);
	
	@WebMethod(action="updateUser", operationName="updateUser")
	@WebResult(name="ResponseSimple")
	public ResponseSimple updateUser(
			@WebParam(name = "User",   partName = "User") 			User user, 
			@WebParam(name = "String", partName = "UserPassword")	String userPassword,
			@WebParam(name = "String", partName = "UserToken") 		String userToken
	);

	@WebMethod(action="joinUserToGroup", operationName="joinUserToGroup")
	@WebResult(name="ResponseSimple")
	public ResponseSimple joinUserToGroup(
			@WebParam(name = "String", partName = "UserID") 		String userID,
			@WebParam(name = "String", partName = "GroupID") 		String groupID,
			@WebParam(name = "String", partName = "UserToken") 		String userToken 
	);
	
	@WebMethod(action="removeUserFromGroup", operationName="removeUserFromGroup")
	@WebResult(name="ResponseSimple")
	public ResponseSimple removeUserFromGroup(
			@WebParam(name = "String", partName = "UserID") 		String userID,
			@WebParam(name = "String", partName = "GroupID") 		String groupID,
			@WebParam(name = "String", partName = "UserToken") 		String userToken 
	);
	
	
// GROUP OPERATIONS	
	
	@WebMethod(action="getGroupDetails", operationName="getGroupDetails")
	@WebResult(name="ResponseGroupDetails")
	public Response<GroupDetails> getGroupDetails(
			@WebParam(name = "String", partName = "GroupID") 	String groupID,
			@WebParam(name = "String", partName = "UserToken") 	String userToken 
	);
	
	@WebMethod(action="addGroup", operationName="addGroup")
	@WebResult(name="ResponseSimple")
	public ResponseSimple addGroup(
			@WebParam(name = "Group",	 	partName = "Group") 		Group newGroup,
			@WebParam(name = "String", 		partName = "UserToken") 	String userToken
	);
	
	@WebMethod(action="findGroup", operationName="findGroup")
	@WebResult(name="ResponseListOfGroup")
	public Response<List<Group>> findGroup(
			@WebParam(name = "String", partName = "GroupNamePattern")	String namePattern,
			@WebParam(name = "String", partName = "UserToken") 			String userToken
	);
	
// ORDER OPERATIONS
	@WebMethod(action="getOrderDetails", operationName="getOrderDetails")
	@WebResult(name="ResponseOrderDetails", targetNamespace="http://www.agh.edu.pl/sius")
	public Response<OrderDetails> getOrderDetails(
			@WebParam(name = "String", partName = "OrderID") 	String orderID,
			@WebParam(name = "String", partName = "UserToken") 	String userToken
	);

	@WebMethod(action="addOrder", operationName="addOrder")
	@WebResult(name="ResponseSimple")
	public ResponseSimple addOrder(
			@WebParam(name = "OrderDetails", 	partName = "OrderDetails") 		OrderDetails newOrder,
			@WebParam(name = "String", 			partName = "UserToken") 		String userToken
	);
	
	@WebMethod(action="closeOrder", operationName="closeOrder")
	@WebResult(name="ResponseSimple")
	public ResponseSimple closeOrder(
			@WebParam(name = "String", partName = "OrderID") 	String orderID,
			@WebParam(name = "String", partName = "UserToken") 	String userToken
	);
	
// BILING OPERATIONS
	@WebMethod(action="changeDept", operationName="changeDept")
	@WebResult(name="ResponseSimple")
	public ResponseSimple changeDept(
			@WebParam(name = "String", partName = "UserID") 		String whoChangingID, 
			@WebParam(name = "String", partName = "UserID") 		String toWhomID, 
			@WebParam(name = "Money",  partName = "NewUserMaxDept")	BigDecimal newMaxDept,
			@WebParam(name = "String", partName = "UserToken") 		String userToken
	);
	
	@WebMethod(action="payMoney", operationName="payMoney")
	@WebResult(name="ResponseSimple")
	public ResponseSimple payMoney(
			@WebParam(name = "String", partName = "StringID")	String whoPayID, 
			@WebParam(name = "String", partName = "StringID")	String toWhomID, 
			@WebParam(name = "Money",  partName = "Amount")		BigDecimal amount,
			@WebParam(name = "String", partName = "UserToken") 	String userToken
	);
	
	@WebMethod(action="acceptPayment", operationName="acceptPayment")
	@WebResult(name="ResponseSimple")
	public ResponseSimple acceptPayment(
			@WebParam(name = "String", partName = "StringID")	String paymentID,
			@WebParam(name = "String", partName = "UserToken") 	String userToken
	);
	
// PRODUCT OPERATIONS
	@WebMethod(action="newProductInOrder", operationName="newProductInOrder")
	@WebResult(name="ResponseSimple")
	public ResponseSimple newProductInOrder(
			@WebParam(name = "Product", 	partName = "Product")		Product newProduct,
			@WebParam(name = "String", 			partName = "UserToken") 		String userToken
	);
	
	@WebMethod(action="joinToProduct", operationName="joinToProduct")
	@WebResult(name="ResponseSimple")
	public ResponseSimple joinToProduct(
			@WebParam(name = "String", partName = "StringID")	String productID,
			@WebParam(name = "String", partName = "UserToken") 	String userToken
	);
}
