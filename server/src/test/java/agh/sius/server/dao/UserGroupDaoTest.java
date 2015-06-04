//package agh.sius.server.dao;
//
//import static org.junit.Assert.*;
//
//import javax.naming.directory.AttributeInUseException;
//
//import org.hibernate.Session;
//import org.junit.BeforeClass;
//import org.junit.Test;
//
//import agh.sius.server.common.HibernateUtil;
//import agh.sius.server.pojo.Group;
//import agh.sius.server.pojo.User;
//
//
//public class UserGroupDaoTest{
//	private DAO dao;
//	
//    public UserGroupDaoTest() {
//        dao = new DAO();
//    }
//    
////    @BeforeClass
//    public static void before() {  
//    	DAO dao = new DAO();
//    	HelperDAO.clearDB();
//    	HelperDAO.createUsers(dao);  
//    	HelperDAO.createGroups(dao);  
//    	dao.resetSession();
//    }
//    
////    @Test
//    public void searchGet(){
//    	assertEquals(dao.getGroups("agh sius2").size(), 0);
//    	assertEquals(dao.getGroups("agh sius").size(), 1);   	
//    	assertEquals(dao.getGroups("gru").size(), 2);
//    	assertEquals(dao.getGroups().size(), 3);   	
//    	
//    	assertNotNull(dao.getUser("login"));
//    	assertNull(dao.getUser("logi"));
//    	assertNull(dao.getUser("us%"));
//    }
//    
////    @Test(expected = org.hibernate.exception.ConstraintViolationException.class)
//    public void uniqueLogin(){
//    	dao.saveOrUpdate(new User("login", "name", "surname", "email", "password"));
//    }
//    
////    @Test
//    public void addUserToGroup() throws AttributeInUseException{
//    	Group group = dao.getGroups("agh sius").get(0);
//    	Group group2 = dao.getGroups("grupa 2").get(0);
//    	User user1 = dao.getUser("login");
//    	User user2 = dao.getUser("login2");
//    	User user3 = dao.getUser("login3");
//    	User user4 = dao.getUser("login4");
//    	
//    	group.addUser(user1);
//    	group.addUser(user2);
//    	group.addUser(user3);
//    	group.addUser(user3);
//    	
//    	group2.addUser(user3);
//    	group2.addUser(user4);
//    
//    	dao.saveOrUpdate(group);
//    	dao.saveOrUpdate(group2);
//    	dao.resetSession();
//    	
//    	group = dao.getGroups("agh sius").get(0);
//    	group2 = dao.getGroups("grupa 2").get(0);
//    	assertEquals(group.getUsers().size(), 3);
//    	assertEquals(group2.getUsers().size(), 2);
//    }
//    
//
//}
