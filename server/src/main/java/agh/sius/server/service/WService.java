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
import agh.sius.server.pojo.xsd.User;
import agh.sius.server.pojo.xsd.GroupDetails;

@WebService (name="WService", targetNamespace="http://www.agh.edu.pl/sius")
@SOAPBinding(parameterStyle = ParameterStyle.BARE, use=Use.LITERAL, style=Style.DOCUMENT)
public interface WService {
	
// USER OPERATION	
	
	@WebMethod(action="login", operationName="login")
	@WebResult(name = "UserClass")
	public User login(
			@WebParam(name = "UserLogin") 				String login, 
			@WebParam(name = "UserPassword") 			String password, 
			@WebParam(name = "AuthorizationProvider") 	String authorizationProvider
	);
	
	@WebMethod(action="logout", operationName="logout")
	public boolean logout(
			@WebParam(name = "UserID") 		String userId,
			@WebParam(name = "UserToken") 	String userToken 
	);

	@WebMethod(action="getUserDetails", operationName="getUserDetails")
	@WebResult(name = "UserClass")
	public User getUserDetails(
			@WebParam(name = "UserID") 		String userId,
			@WebParam(name = "UserToken") 	String userToken 
	);
	
	@WebMethod(action="updateUser", operationName="updateUser")
	public boolean updateUser(
			@WebParam(name = "UserClass") 		User user, 
			@WebParam(name = "UserPassword")	String userPassword,
			@WebParam(name = "UserToken") 		String userToken
	);

	@WebMethod(action="joinUserToGroup", operationName="joinUserToGroup")
	public boolean joinUserToGroup(
			@WebParam(name = "UserID") 		String userId,
			@WebParam(name = "GroupID") 	String groupId,
			@WebParam(name = "UserToken") 	String userToken 
	);
	
	@WebMethod(action="removeUserFromGroup", operationName="removeUserFromGroup")
	public boolean removeUserFromGroup(
			@WebParam(name = "UserID") 		String userId,
			@WebParam(name = "GroupID") 	String groupId,
			@WebParam(name = "UserToken") 	String userToken 
	);
	
	
// GROUP OPERATIONS	
	
	@WebMethod(action="getGroupDetails", operationName="getGroupDetails")
	@WebResult(name="GroupDetailsClass")
	public GroupDetails getGroupDetails(
			@WebParam(name = "GroupID") 	String groupId,
			@WebParam(name = "UserToken") 	String userToken 
	);
	
	@WebMethod(action="addGroup", operationName="addGroup")
	public boolean addGroup(
			@WebParam(name = "GroupClass") 	Group newGroup,
			@WebParam(name = "UserToken") 	String userToken
	);
	
	@WebMethod(action="findGroup", operationName="findGroup")
	@WebResult(partName="List[GroupClass]")
	public List<Group> findGroup(
			@WebParam(name = "GroupNamePattern")	String namePattern,
			@WebParam(name = "UserToken") 			String userToken
	);
	
// ORDER OPERATIONS
	@WebMethod(action="getOrderDetails", operationName="getOrderDetails")
	@WebResult(name="OrderDetailsClass", targetNamespace="http://www.agh.edu.pl/sius")
	public OrderDetails getOrderDetails(
			@WebParam(name = "OrderID") 	String orderId,
			@WebParam(name = "UserToken") 	String userToken
	);

	@WebMethod(action="addOrder", operationName="addOrder")
	public boolean addOrder(
			@WebParam(name = "OrderClass") OrderDetails newOrder,
			@WebParam(name = "UserToken") 	String userToken
	);
	
	@WebMethod(action="closeOrder", operationName="closeOrder")
	public boolean closeOrder(
			@WebParam(name = "OrderID") String orderId,
			@WebParam(name = "UserToken") 	String userToken
	);
	
// BILING OPERATIONS
	@WebMethod(action="changeDept", operationName="changeDept")
	public boolean changeDept(
			@WebParam(name = "UserID") 	String whoChangingId, 
			@WebParam(name = "UserID") 	String toWhomId, 
			@WebParam(name = "Money")	BigDecimal newMaxDept,
			@WebParam(name = "UserToken") 	String userToken
	);
	
	@WebMethod(action="payMoney", operationName="payMoney")
	public boolean payMoney(
			@WebParam(name = "UserID")	String whoPayId, 
			@WebParam(name = "UserID")	String toWhomId, 
			@WebParam(name = "Money")	BigDecimal amount,
			@WebParam(name = "UserToken") 	String userToken
	);
	
	@WebMethod(action="acceptPayment", operationName="acceptPayment")
	public boolean acceptPayment(
			@WebParam(name = "PaymentId")	String paymentId,
			@WebParam(name = "UserToken") 	String userToken
	);
	
// PRODUCT OPERATIONS
	@WebMethod(action="newProductInOrder", operationName="newProductInOrder")
	public boolean newProductInOrder(
			@WebParam(name = "ProductClass")	Product newProduct,
			@WebParam(name = "UserToken") 		String userToken
	);
	
	@WebMethod(action="joinToProduct", operationName="joinToProduct")
	public boolean joinToProduct(
			@WebParam(name = "ProductID")	String productID,
			@WebParam(name = "UserToken") 	String userToken
	);
}
