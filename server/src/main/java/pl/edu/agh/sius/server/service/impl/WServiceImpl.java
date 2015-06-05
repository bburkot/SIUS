package pl.edu.agh.sius.server.service.impl;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import javax.jws.WebService;

import pl.edu.agh.sius.server.dao.DAO;
import pl.edu.agh.sius.server.pojo.Billings;
import pl.edu.agh.sius.server.pojo.Group;
import pl.edu.agh.sius.server.pojo.GroupDetails;
import pl.edu.agh.sius.server.pojo.OrderDetails;
import pl.edu.agh.sius.server.pojo.OrderState;
import pl.edu.agh.sius.server.pojo.Payment;
import pl.edu.agh.sius.server.pojo.Product;
import pl.edu.agh.sius.server.pojo.User;
import pl.edu.agh.sius.server.pojo.UserDetails;
import pl.edu.agh.sius.server.pojo.responses.Balance;
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
	public ResponseSimple registerUser(User user, String password) {
		ResponseSimple response = new ResponseSimple();
		
		user.setPassword(password);
		String err = dao.saveOrUpdate(user);
		
		if (err == null){	
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
			System.out.println(user + " group size " + user.getGroups().size());
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
	public ResponseSimple applicateToGroup(String groupID, User user) {
		ResponseSimple response = new ResponseSimple();
		
		User dboUser = dao.getUser(user.getId(), user.getToken());
		if ( dboUser == null)
			return response;
		
		GroupDetails groupDetails = dao.getGroupDetails(groupID);
		if (groupDetails == null)
			return response;
		
		if (groupDetails.getMemberUsers().contains(dboUser)){
			response.setMsg("Urzytkownik już należy do tej grupy");
			return response;
		}
		
		if (groupDetails.getMemberUsers().size() == 0)
			groupDetails.getMemberUsers().add(dboUser);
		else
			groupDetails.getApplicantUsers().add(dboUser);
		
		String err = dao.saveOrUpdate(groupDetails);		
		if (err == null)
			response.succeed();
		else
			response.setMsg(err);
		
		return response;
	}
	
	@Override
	public ResponseSimple acceptApplication(String acceptUserId, String groupID, User user) {
		ResponseSimple response = new ResponseSimple();
		
		User dboUser = dao.getUser(user.getId(), user.getToken());
		if ( dboUser == null)
			return response;
		
		User newUser = dao.getUser(acceptUserId);
		if (newUser == null)
			return response;
		
		GroupDetails groupDetails = dao.getGroupDetails(groupID);
		if (groupDetails == null)
			return response;
		
		System.out.println("accept server " + newUser.getId());
		for (User u : groupDetails.getApplicantUsers()){
			System.out.println("applicant " + u.getId());
		}
		
		if ( !groupDetails.getApplicantUsers().contains(newUser)){
			response.setMsg("Dany urzytkownik nie aplikuje do danej grupy");
			return response;
		}
		
		if (groupDetails.getMemberUsers().contains(newUser)){
			response.setMsg("Dany urzytkonik już należy do danej grupy");
			return response;
		}
		
		groupDetails.getApplicantUsers().remove(newUser);
		groupDetails.getMemberUsers().add(newUser);
		
		String err = dao.saveOrUpdate(groupDetails);
		if (err == null)
			response.succeed();
		else 
			response.setMsg(err);
		
		return response;
	}
	
	@Override
	public ResponseSimple removeUserFromGroup(String removeUserId, String groupID, User user) {
		ResponseSimple response = new ResponseSimple();
		
		User dboUser = dao.getUser(user.getId(), user.getToken());
		if ( dboUser == null)
			return response;
		
		GroupDetails groupDetails = dao.getGroupDetails(groupID);
		if (groupDetails == null)
			return response;
		
		if (groupDetails.getMemberUsers().contains(dboUser)){
			groupDetails.getMemberUsers().remove(dboUser);
		} else if (groupDetails.getApplicantUsers().contains(dboUser)){
			groupDetails.getApplicantUsers().remove(dboUser);
		} else {
			return response;
		}
		
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
			for (User u : groupDetails.getMemberUsers())
				u.setMaxDept( dao.getMaxDept(u, user) );
			
			response.setGroupDetails(groupDetails);
			response.succeed();
			for (OrderDetails o : groupDetails.getOrders())
				System.out.print(o.getId() + " ");
			System.out.println("send " + groupDetails.getOrders().size());
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
		
		if (dao.getGroupByName(newGroup.getName()) != null){
			response.setMsg("Grupa o takiej nazwie już istnieje");
			return response;
		}
		
		GroupDetails groupDetails = new GroupDetails(newGroup, dboUser);
		System.out.println("server " + groupDetails);
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
		System.out.println("wysłano");
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
	public ResponseOrderDetails addOrder(String groupId, OrderDetails newOrder, User user) {
		ResponseOrderDetails response = new ResponseOrderDetails();
		
		User dboUser = dao.getUser(user.getId(), user.getToken());
		GroupDetails group = dao.getGroupDetails(groupId);
		if ( dboUser == null || group == null)
			return response;
		
		newOrder.setState(OrderState.OPEN);
		newOrder.setRealizedBy(dboUser);
		newOrder.setDate(new Date());
		newOrder.setGroupDetails(group);
		
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
		OrderDetails order = dao.getOrder(orderID);
		if ( dboUser == null || order == null || order.getState() != OrderState.OPEN 
				|| !order.getRealizedBy().getId().equals(user.getId()) )
			return response;

				
		order.setState(OrderState.REALIZED);
				
		String err = dao.saveOrUpdate(order);		
		if (err == null) {
			for(Product product : order.getProducts()){
				BigDecimal costPerPerson = product.getCost()
						.divide(new BigDecimal(product.getUsers().size()), 2, RoundingMode.UP);
				
				for (User u : product.getUsers()){
					if (! u.getId().equals(user.getId())){
						Billings billing = dao.getOrCreateBillings(u, dboUser);
						if (billing.getFirst().getId().equals(user.getId())){
							billing.setBalance(
									billing.getBalance().add(costPerPerson)
									.setScale(2, RoundingMode.HALF_UP) ); 
						} else {
							billing.setBalance(
									billing.getBalance().subtract(costPerPerson)
									.setScale(2, RoundingMode.HALF_UP) ); 
						}
						dao.saveOrUpdate(billing);
					}
				}
			}
						
			response.succeed();
		}  else 
			response.setMsg(err);
		
		return response;
	}
	
	@Override
	public ResponseSimple changeDept(String toWhomID, BigDecimal newMaxDept, User user) {
		ResponseSimple response = new ResponseSimple();
		
		User dboUser = dao.getUser(user.getId(), user.getToken());
		if ( dboUser == null)
			return response;
		
		User secondUser = dao.getUser(toWhomID);
		if (secondUser == null)
			return response;
		
		Billings usersBillings = dao.getOrCreateBillings(dboUser, secondUser);
		newMaxDept = newMaxDept.setScale(2, RoundingMode.HALF_UP);
		usersBillings.setMaxSecondUserDebtToFirstUser(newMaxDept);
		
		String err = dao.saveOrUpdate(usersBillings);
		
		if (err == null)
			response.succeed();
		else 
			response.setMsg(err);
		
		return response;
	}
	
	@Override
	public ResponseSimple payMoney(String toWhomID, BigDecimal amount, User user) {
		ResponseSimple response = new ResponseSimple();
		
		if (amount.compareTo(BigDecimal.ZERO) <= 0){
			response.setMsg("Kwota przekazana powina być większa od 0");
			return response;
		}
		
		User dboUser = dao.getUser(user.getId(), user.getToken());
		if ( dboUser == null)
			return response;
		
		User second = dao.getUser(toWhomID);
		if (second == null)
			return response;
		
		Billings billing = dao.getOrCreateBillings(user, second);
		amount = amount.setScale(2, RoundingMode.HALF_UP);
		billing.addPayment(amount, user);
	
		String err = dao.saveOrUpdate(billing);	
		if (err == null)
			response.succeed();
		else 
			response.setMsg(err);
		return response;
	}
	
	@Override
	public ResponseSimple acceptPayment(String paymentID, User user) {
		ResponseSimple response = new ResponseSimple();
		
		User dboUser = dao.getUser(user.getId(), user.getToken());
		if ( dboUser == null)
			return response;
		
		Payment payment = dao.getPayment(paymentID);
		if (payment == null)
			return response;		
		Billings billing = payment.getBilling();
		
		if (
			(payment.getAmount().compareTo(BigDecimal.ZERO) > 0 
				&& user.getId().equals(billing.getSecond().getId())) ||
			(payment.getAmount().compareTo(BigDecimal.ZERO) < 0 
				&& user.getId().equals(billing.getFirst().getId()))					
		){
			payment.setDate(new Date());
			BigDecimal newBalane = billing.getBalance().add(payment.getAmount());
			billing.setBalance( newBalane );
		} else {
			response.setMsg("Użytkonik nie ma prawa potwierdzić tej płatności");
			return response;
		}
				
		String err = dao.saveOrUpdate( billing );
		if (err == null)
			response.succeed();
		else 
			response.setMsg(err);
		return response;
	}
	
	@Override
	public ResponseProduct newProductInOrder(String orderId, Product newProduct, User user) {
		ResponseProduct response = new ResponseProduct();
		
		User dboUser = dao.getUser(user.getId(), user.getToken());
		OrderDetails order = dao.getOrder(orderId);
		if ( dboUser == null || order == null)
			return response;
		
		if (newProduct.getMaxUserPerProduct() <= 0){
			response.setMsg("Maksymalna liczba osób na zamówienie musi być >= 1");
			return response;
		}
		
		if (order.getState() != OrderState.OPEN){
			response.setMsg("Zamówienie zostało zamknięte");
			return response;
		}
		
		Billings billing = dao.getBillings(user, order.getRealizedBy());
		if (billing != null){
			BigDecimal maxDept, balance ;	
			if (billing.getFirst().getId().equals(user.getId())){
				 maxDept = billing.getMaxFirstUserDebtToSecondUser();
				 balance = billing.getBalance().negate();				
			} else {
				maxDept = billing.getMaxSecondUserDebtToFirstUser();
				balance = billing.getBalance();
			}
			
			if (maxDept != null && maxDept.compareTo(balance) <= 0){
				response.setMsg("Przekroczony limit, proszę uregulować płatność");
				return response;
			}			
		}
		
		
		newProduct.setOrder(order);
		newProduct.setCost(newProduct.getCost().setScale(2, RoundingMode.HALF_UP));
		newProduct.setUsers(new HashSet<>(Arrays.asList(user)));
				
		String err = dao.saveOrUpdate(newProduct);		
		if (err == null){
			response.setProducts(newProduct);
			response.succeed();
		}else 
			response.setMsg(err);
		
		return response;
	}
	
	@Override
	public ResponseSimple joinToProduct(String productID, User user) {
		ResponseSimple response = new ResponseSimple();
		
		User dboUser = dao.getUser(user.getId(), user.getToken());
		Product product = dao.getProduct(productID);
		if ( dboUser == null || product == null)
			return response;
		
		if (product.getUsers().size() >= product.getMaxUserPerProduct()){
			response.setMsg("Produkt osiągnął już max liczbę konsumentów");
			return response;
		}
		
		Billings billing = dao.getBillings(user, product.getOrder().getRealizedBy());
		if (billing != null){
			BigDecimal maxDept, balance ;	
			if (billing.getFirst().getId().equals(user.getId())){
				 maxDept = billing.getMaxFirstUserDebtToSecondUser();
				 balance = billing.getBalance().negate();				
			} else {
				maxDept = billing.getMaxSecondUserDebtToFirstUser();
				balance = billing.getBalance();
			}
			
			if (maxDept != null && maxDept.compareTo(balance) <= 0){
				response.setMsg("Przekroczony limit, proszę uregulować płatność");
				return response;
			}			
		}
		
		product.getUsers().add(dboUser);
		String err = dao.saveOrUpdate(product);		
		if (err == null)
			response.succeed();
		else 
			response.setMsg(err);
		
		return response;
	}
	
	@Override
	public ResponseSimple cancelUserFromProduct(String userID, String productID, User user) {
		ResponseSimple response = new ResponseSimple();
		
		User dboUser = dao.getUser(user.getId(), user.getToken());
		User delUser = dao.getUser(userID);
		Product product = dao.getProduct(productID);
		if ( dboUser == null || product == null || delUser == null
				|| !product.getUsers().contains(delUser) )
			return response;
		
		if (  !delUser.getId().equals(user.getId())
				&& !product.getOrder().getRealizedBy().getId().equals(user.getId())){
			response.setMsg("Dany uzytkonik nie ma prawa anulować tego zamówienia");
			return response;
		}
		
		String err;
		if (product.getUsers().size() > 1){
			product.getUsers().remove(dboUser);
			err = dao.saveOrUpdate(product);		
		} else {
			product.getOrder().getProducts().remove(product);
			product.setOrder(null);
			err = dao.delete(product);
		}
		if (err == null)
			response.succeed();
		else 
			response.setMsg(err);		
		return response;
	}

	@Override
	public ResponsePayments getUserPayments(User user) {
		ResponsePayments response = new ResponsePayments();
		
		User dboUser = dao.getUser(user.getId(), user.getToken());
		if ( dboUser == null)
			return response;
		
		List<Billings> billings = dao.getUserBillings(dboUser);
		List<Payment> payments = new ArrayList<>();
		for (Billings b : billings){
			for (Payment p : b.getPayments()){
				if (p.getAmount().compareTo(BigDecimal.ZERO) > 0)
					payments.add(new Payment(b.getFirst(), b.getSecond(), 
							p.getAmount(), p));
				else
					payments.add(new Payment(b.getSecond(), b.getFirst(), 
							p.getAmount().negate(), p));
			}
		}
			
		response.setPayments(payments);
		response.succeed();		
		return response;
	}


	@Override
	public ResponseProducts getProductsOrderedByUser(User user) {
		ResponseProducts response = new ResponseProducts();
		
		UserDetails dboUser = dao.getUserDetails(user.getId(), user.getToken());
		if ( dboUser == null)
			return response;
		
		response.setProducts(new ArrayList<Product>(dboUser.getProducts()));
		response.succeed();
		return response;
	}

	@Override
	public ResponseSimple cancelPayment(String paymentID, User user) {
		ResponseSimple response = new ResponseSimple();
		
		User dboUser = dao.getUser(user.getId(), user.getToken());
		if ( dboUser == null)
			return response;
		
		Payment payment = dao.getPayment(paymentID);
		if (payment == null)
			return response;
		
		String err = dao.delete(payment);
		if (err == null)
			response.succeed();
		else 
			response.setMsg(err);
		return response;
	}

	@Override
	public ResponseBalances getUsersBalances(User user) {
		ResponseBalances response = new ResponseBalances();
		
		User dboUser = dao.getUser(user.getId(), user.getToken());
		if ( dboUser == null)
			return response;
		
		List<Billings> billings = dao.getUserBillings(dboUser);
		List<Balance> balances = new ArrayList<>();
		System.out.println("get balance for " + dboUser.getId() );
		for (Billings b : billings)
			if (b.getBalance().compareTo(BigDecimal.ZERO) != 0){
				System.out.println(b.getFirst().getId() + " " + b.getSecond().getId() + " " + b.getBalance());
				if ( b.getFirst().getId().equals(dboUser.getId()) )
					balances.add(new Balance(b.getSecond(), b.getBalance()));
				else
					balances.add(new Balance(b.getFirst(), b.getBalance().negate()));
			}
		response.setBalances(balances);
		response.succeed();
		return response;
	}
}
