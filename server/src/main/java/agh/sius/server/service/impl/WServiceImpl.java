package agh.sius.server.service.impl;


import java.math.BigDecimal;
import java.util.List;

import javax.jws.WebService;

import agh.sius.server.pojo.xsd.Group;
import agh.sius.server.pojo.xsd.GroupDetails;
import agh.sius.server.pojo.xsd.OrderDetails;
import agh.sius.server.pojo.xsd.Product;
import agh.sius.server.pojo.xsd.User;
import agh.sius.server.service.WService;


@WebService(endpointInterface = "agh.sius.server.service.WService",
	targetNamespace="http://www.agh.edu.pl/sius", name="WService")
public class WServiceImpl implements WService {

	@Override
	public User login(String login, String password,
			String authorizationProvider) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean logout(String userId, String userToken) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User getUserDetails(String userId, String userToken) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateUser(User user, String userPassword, String userToken) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean joinUserToGroup(String userId, String groupId,
			String userToken) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeUserFromGroup(String userId, String groupId,
			String userToken) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public GroupDetails getGroupDetails(String groupId, String userToken) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addGroup(Group newGroup, String userToken) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Group> findGroup(String namePattern, String userToken) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderDetails getOrderDetails(String orderId, String userToken) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addOrder(OrderDetails newOrder, String userToken) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean closeOrder(String orderId, String userToken) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean changeDept(String whoChangingId, String toWhomId,
			BigDecimal newMaxDept, String userToken) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean payMoney(String whoPayId, String toWhomId,
			BigDecimal amount, String userToken) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean acceptPayment(String paymentId, String userToken) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean newProductInOrder(Product newProduct, String userToken) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean joinToProduct(String productID, String userToken) {
		// TODO Auto-generated method stub
		return false;
	}


}
