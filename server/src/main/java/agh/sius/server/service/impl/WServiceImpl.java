package agh.sius.server.service.impl;


import java.math.BigDecimal;
import java.util.List;

import javax.jws.WebService;

import agh.sius.server.pojo.xsd.Group;
import agh.sius.server.pojo.xsd.GroupDetails;
import agh.sius.server.pojo.xsd.OrderDetails;
import agh.sius.server.pojo.xsd.Product;
import agh.sius.server.pojo.xsd.Response;
import agh.sius.server.pojo.xsd.ResponseSimple;
import agh.sius.server.pojo.xsd.User;
import agh.sius.server.pojo.xsd.UserDetails;
import agh.sius.server.service.WService;


@WebService(endpointInterface = "agh.sius.server.service.WService",
	targetNamespace="http://www.agh.edu.pl/sius", name="WService")
public class WServiceImpl implements WService {

	@Override
	public Response<User> login(String login, String password,
			String authorizationProvider) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseSimple logout(String userId, String userToken) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response<UserDetails> getUserDetails(String userId, String userToken) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseSimple updateUser(User user, String userPassword,
			String userToken) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseSimple joinUserToGroup(String userId, String groupId,
			String userToken) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseSimple removeUserFromGroup(String userId, String groupId,
			String userToken) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response<GroupDetails> getGroupDetails(String groupId,
			String userToken) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseSimple addGroup(Group newGroup, String userToken) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response<List<Group>> findGroup(String namePattern, String userToken) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response<OrderDetails> getOrderDetails(String orderId,
			String userToken) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseSimple addOrder(OrderDetails newOrder, String userToken) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseSimple closeOrder(String orderId, String userToken) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseSimple changeDept(String whoChangingId, String toWhomId,
			BigDecimal newMaxDept, String userToken) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseSimple payMoney(String whoPayId, String toWhomId,
			BigDecimal amount, String userToken) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseSimple acceptPayment(String paymentId, String userToken) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseSimple newProductInOrder(Product newProduct, String userToken) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseSimple joinToProduct(String productID, String userToken) {
		// TODO Auto-generated method stub
		return null;
	}
}
