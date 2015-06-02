package agh.sius.server.dao;

import java.util.Date;

import javax.naming.directory.AttributeInUseException;

import org.hibernate.Session;
import org.junit.BeforeClass;
import org.junit.Test;

import agh.sius.server.common.HibernateUtil;
import agh.sius.server.pojo.Group;
import agh.sius.server.pojo.Order;
import agh.sius.server.pojo.User;
import static org.junit.Assert.*;

public class OrderDaoTest{
	
	private DAO dao;
	
    public OrderDaoTest() {
        dao = new DAO();
    }
	
	@BeforeClass
    public static void before() {   	
		DAO dao = new DAO();
    	HelperDAO.clearDB();
    	HelperDAO.createUsers(dao);  
    	HelperDAO.createGroups(dao);   
    	    	
    	HelperDAO.addUsersToGroups(dao, new String[][]{
			{"login",  "grupa 1", "grupa 2", "agh sius"},
			{"login1", "grupa 1", "grupa 2", "agh sius"}, 
			{"user",   "grupa 1", "grupa 2" }
    	});   	
    	dao.resetSession();
    }
	
	@Test
	public void addOrders() throws AttributeInUseException{
		User user1 = dao.getUser("login");
		User user2 = dao.getUser("login1");
		User user3 = dao.getUser("user");
		
		Group group1 = dao.getGroups("grupa 1").get(0);
    	Group group2 = dao.getGroups("grupa 2").get(0);
    	Group group3 = dao.getGroups("agh sius").get(0);
    	
    	Order order1 = new Order("Order 1 gr1", new Date(), user1);
    	Order order2 = new Order("Order 2 gr1", new Date(), user1);
    	Order order3 = new Order("Order 3 gr1", new Date(), user2);
    	Order order4 = new Order("Order 4 gr1", new Date(), user3);    	
    	group1.addOrder(order1).addOrder(order2).addOrder(order3).addOrder(order4);
    	
    	order3 = new Order("Order 3 gr2", new Date(), user2);
    	order4 = new Order("Order 4 gr2", new Date(), user3);    	
    	group2.addOrder(order3).addOrder(order4);
    	
    	order1 = new Order("Order 1 gr3", new Date(), user1);   	
    	group3.addOrder(order1);
    	
    	dao.saveOrUpdate(group1);
    	dao.saveOrUpdate(group2);
    	dao.saveOrUpdate(group3); 
    	
    	dao.resetSession();
    	
    	group1 = dao.getGroups("grupa 1").get(0);
    	group2 = dao.getGroups("grupa 2").get(0);
    	group3 = dao.getGroups("agh sius").get(0);
    	
    	assertEquals(group1.getOrders().size(), 4);
    	assertEquals(group2.getOrders().size(), 2);
    	assertEquals(group3.getOrders().size(), 1);
	}
	
	@Test(expected = AttributeInUseException.class)
	public void addOrderToWrongGroup() throws AttributeInUseException{
		User user = dao.getUser("user");
		Group group = dao.getGroups("agh sius").get(0); 
		Order order = new Order("msg", new Date(), user);
		group.addOrder(order);
	}
	
	@Test(expected = AttributeInUseException.class)
	public void addOrderToTwoDiffrentGroup() throws AttributeInUseException{
		User user = dao.getUser("login");
		Group group = dao.getGroups("agh sius").get(0);
		Group group1 = dao.getGroups("grupa 1").get(0);
		Order order = new Order("Error order", new Date(), user);
		group.addOrder(order);
		group1.addOrder(order);
	}
}
