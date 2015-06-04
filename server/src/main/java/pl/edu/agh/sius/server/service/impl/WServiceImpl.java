package pl.edu.agh.sius.server.service.impl;


import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import javax.jws.WebService;

import pl.edu.agh.sius.server.dao.DAO;
import pl.edu.agh.sius.server.pojo.Group;
import pl.edu.agh.sius.server.pojo.GroupDetails;
import pl.edu.agh.sius.server.pojo.OrderDetails;
import pl.edu.agh.sius.server.pojo.OrderState;
import pl.edu.agh.sius.server.pojo.Product;
import pl.edu.agh.sius.server.pojo.User;
import pl.edu.agh.sius.server.pojo.UserDetails;
import pl.edu.agh.sius.server.pojo.responses.ResponseGroupDetails;
import pl.edu.agh.sius.server.pojo.responses.ResponseGroups;
import pl.edu.agh.sius.server.pojo.responses.ResponseOrderDetails;
import pl.edu.agh.sius.server.pojo.responses.ResponsePayments;
import pl.edu.agh.sius.server.pojo.responses.ResponseProducts;
import pl.edu.agh.sius.server.pojo.responses.ResponseSimple;
import pl.edu.agh.sius.server.pojo.responses.ResponseUser;
import pl.edu.agh.sius.server.pojo.responses.ResponseUserDetails;
import pl.edu.agh.sius.server.service.WService;

 
@WebService(endpointInterface = "pl.edu.agh.sius.server.service.WService",
	targetNamespace="http://www.agh.edu.pl/sius", name="WService")
public class WServiceImpl implements WService {
	private DAO dao;
	
	public WServiceImpl(){
		dao = new DAO();
	}
	
	private String generateToken(){
		return UUID.randomUUID().toString();
	}

	@Override
	public ResponseUser registerUser(User user, String password) {
		ResponseUser response = new ResponseUser();
		
		user.setPassword(password);
		String err = dao.saveOrUpdate(user);
		
		if (err == null){
			user.setToken( generateToken() );
			response.setUser(user);			
			response.succeed();
		} else {
			response.setMsg(err);
		}		
		 
		return response;
	}
	
	@Override
	public ResponseUser login(String login, String password, String authorizationProvider) {
		User user = dao.getUser(login, password, authorizationProvider);
		ResponseUser response = new ResponseUser();
		if (user != null){
			user.setToken( generateToken() );
			response.setUser(user);	
			response.succeed();
		}
		return response;
	}
	
	@Override
	public ResponseSimple logout(User user) {
		ResponseSimple response = new ResponseSimple();
		
		User dboUser = dao.getUser(user.getId(), user.getToken());
		if ( dboUser == null)
			return response;	
		
		dboUser.setToken(null);
		String err = dao.saveOrUpdate(dboUser);
		if (err == null)
			response.succeed();
		else
			response.setMsg(err);
		return response;
	}
	
	@Override
	public ResponseUserDetails getUserDetails(User user) {
		ResponseUserDetails response = new ResponseUserDetails();
		
		UserDetails dboUserDetails = dao.getUserDetails(user.getId(), user.getToken());		
		response.setUserDetails(dboUserDetails);
		response.succeed();
		
		return response;
	}
	
	@Override
	public ResponseSimple updateUser(User user, String userPassword) {
		ResponseSimple response = new ResponseSimple();
		
		User dboUser = dao.getUser(user.getId(), user.getToken());
		if ( dboUser == null)
			return response;
		
		dboUser.updateDataFrom(user);
		String err = dao.saveOrUpdate(dboUser);
		if (err == null)
			response.succeed();
		else
			response.setMsg(err);
		
		return response;
	}
	
	@Override
	public ResponseSimple joinUserToGroup(String groupID, User user) {
		ResponseSimple response = new ResponseSimple();
		
		User dboUser = dao.getUser(user.getId(), user.getToken());
		if ( dboUser == null)
			return response;
		
		GroupDetails groupDetails = dao.getGroupDetails(groupID);
		if (groupDetails == null)
			return response;
		
		groupDetails.getApplicantUsers().add(dboUser);
		String err = dao.saveOrUpdate(groupDetails);
		
		if (err == null)
			response.succeed();
		else
			response.setMsg(err);
		
		return response;
	}
	
	@Override
	public ResponseSimple removeUserFromGroup(String groupID, User user) {
	ResponseSimple response = new ResponseSimple();
		
		User dboUser = dao.getUser(user.getId(), user.getToken());
		if ( dboUser == null)
			return response;
		
		GroupDetails groupDetails = dao.getGroupDetails(groupID);
		if (groupDetails == null)
			return response;
		
		if (! groupDetails.getApplicantUsers().remove(dboUser))
			return response;
		
		String err = dao.saveOrUpdate(groupDetails);
		
		if (err == null)
			response.succeed();
		else
			response.setMsg(err);
		
		return response;
	}
	
	@Override
	public ResponseGroupDetails getGroupDetails(String groupID, User user) {
		ResponseGroupDetails response = new ResponseGroupDetails();
		
		User dboUser = dao.getUser(user.getId(), user.getToken());
		if ( dboUser == null)
			return response;
		
		GroupDetails groupDetails = dao.getGroupDetails(groupID);
		if (groupDetails != null){
			response.setGroupDetails(groupDetails);
			response.succeed();
		} else
			response.setMsg("Group with id= " + groupID + " not exists");
		return response;
	}
	
	@Override
	public ResponseGroupDetails addGroup(Group newGroup, User user) {
		ResponseGroupDetails response = new ResponseGroupDetails();
		
		User dboUser = dao.getUser(user.getId(), user.getToken());
		if ( dboUser == null)
			return response;
		
		GroupDetails groupDetails = new GroupDetails(newGroup, dboUser);
		String err = dao.saveOrUpdate(groupDetails);
		
		if (err == null){
			response.setGroupDetails(groupDetails);
			response.succeed();
		} else
			response.setMsg(err);
						
		return response;
	}
	
	@Override
	public ResponseGroups findGroup(String namePattern, User user) {
		ResponseGroups response = new ResponseGroups();
		
		User dboUser = dao.getUser(user.getId(), user.getToken());
		if ( dboUser == null)
			return response;
		
		response.setGroups( dao.getGroups(namePattern));
		response.succeed();
		
		return response;
	}
	
	@Override
	public ResponseOrderDetails getOrderDetails(String orderID, User user) {
		ResponseOrderDetails response = new ResponseOrderDetails();
		
		User dboUser = dao.getUser(user.getId(), user.getToken());
		if ( dboUser == null)
			return response;
		
		response.setOrderDetails( dao.getOrder(orderID) );
		if ( response.getOrderDetails() != null)
			response.succeed();
		
		return response;
	}
	
	@Override
	public ResponseOrderDetails addOrder(OrderDetails newOrder, User user) {
		ResponseOrderDetails response = new ResponseOrderDetails();
		
		User dboUser = dao.getUser(user.getId(), user.getToken());
		if ( dboUser == null)
			return response;
		
		newOrder.setState(OrderState.OPEN);
		newOrder.setRealizedBy(dboUser);
		newOrder.setDate(new Date());
		
		String err = dao.saveOrUpdate(newOrder);
		if (err == null){
			response.setOrderDetails(newOrder);
			response.succeed();
		} else
			response.setMsg(err);
		
		return response;
	}
	
	@Override
	public ResponseSimple closeOrder(String orderID, User user) {
		ResponseSimple response = new ResponseSimple();
		
		User dboUser = dao.getUser(user.getId(), user.getToken());
		if ( dboUser == null)
			return response;
		return null;
	}
	
	@Override
	public ResponseSimple changeDept(String toWhomID, BigDecimal newMaxDept, User user) {
		ResponseSimple response = new ResponseSimple();
		
		User dboUser = dao.getUser(user.getId(), user.getToken());
		if ( dboUser == null)
			return response;
		return null;
	}
	
	@Override
	public ResponseSimple payMoney(String toWhomID, BigDecimal amount, User user) {
		ResponseSimple response = new ResponseSimple();
		
		User dboUser = dao.getUser(user.getId(), user.getToken());
		if ( dboUser == null)
			return response;
		return null;
	}
	
	@Override
	public ResponseSimple acceptPayment(String paymentID, User user) {
		ResponseSimple response = new ResponseSimple();
		
		User dboUser = dao.getUser(user.getId(), user.getToken());
		if ( dboUser == null)
			return response;
		return null;
	}
	
	@Override
	public ResponseSimple newProductInOrder(Product newProduct, User user) {
		ResponseSimple response = new ResponseSimple();
		
		User dboUser = dao.getUser(user.getId(), user.getToken());
		if ( dboUser == null)
			return response;
		return null;
	}
	
	@Override
	public ResponseSimple joinToProduct(String productID, User user) {
		ResponseSimple response = new ResponseSimple();
		
		User dboUser = dao.getUser(user.getId(), user.getToken());
		if ( dboUser == null)
			return response;
		return null;
	}

	@Override
	public ResponsePayments getUserPayments(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponsePayments getUsersPayments(User userOther, User userLogged) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseProducts getProductsOrderedByUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
