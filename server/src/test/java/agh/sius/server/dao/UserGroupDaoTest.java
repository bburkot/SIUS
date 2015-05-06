package agh.sius.server.dao;

import static org.junit.Assert.*;

import org.hibernate.Session;
import org.junit.BeforeClass;
import org.junit.Test;

import agh.sius.server.common.HibernateUtil;
import agh.sius.server.pojo.Group;
import agh.sius.server.pojo.User;


public class UserGroupDaoTest{
	private UserGroupDao dao;
	
    public UserGroupDaoTest() {
        dao = new UserGroupDao();
    }
    
    @BeforeClass
    public static void before() {   	
    	Session session = HibernateUtil.openSession();
    	session.beginTransaction();
    	session.createQuery("delete from Billing").executeUpdate();
    	session.createQuery("delete from Group").executeUpdate();
    	session.createQuery("delete from User").executeUpdate();
    	session.getTransaction().commit();

    	UserGroupDao dao = new UserGroupDao();
    	dao.saveOrUpdateUser(new Group("grupa 1"));
    	dao.saveOrUpdateUser(new Group("grupa 2"));
    	dao.saveOrUpdateUser(new Group("agh sius"));
    	
    	dao.saveOrUpdateUser(new User("login", "name", "surname", "email", "password"));
    	dao.saveOrUpdateUser(new User("login1", "name1", "surname1", "email1", "password1"));
    	dao.saveOrUpdateUser(new User("login2", "name2", "surname2", "email2", "password2"));
    	dao.saveOrUpdateUser(new User("login3", "name3", "surname3", "email3", "password3"));
    	dao.saveOrUpdateUser(new User("login4", "name4", "surname4", "email4", "password4")); 	
    	dao.saveOrUpdateUser(new User("user",   "name5", "surname5", "email5", "password5")); 
    }
    
    @Test
    public void searchGet(){
    	assertEquals(dao.getGroups("agh sius2").size(), 0);
    	assertEquals(dao.getGroups("agh sius").size(), 1);   	
    	assertEquals(dao.getGroups("gru").size(), 2);
    	assertEquals(dao.getGroups().size(), 3);   	
    	
    	assertNotNull(dao.getUser("login"));
    	assertNull(dao.getUser("logi"));
    	assertNull(dao.getUser("us%"));
    }
    
    @Test(expected = org.hibernate.exception.ConstraintViolationException.class)
    public void uniqueLogin(){
    	dao.saveOrUpdateUser(new User("login", "name", "surname", "email", "password"));
    }
    
    @Test
    public void addUserToGroup(){
    	Group group = dao.getGroups("agh sius").get(0);
    	Group group2 = dao.getGroups("grupa 2").get(0);
    	User user1 = dao.getUser("login");
    	User user2 = dao.getUser("login2");
    	User user3 = dao.getUser("login3");
    	User user4 = dao.getUser("login4");
    	
    	group.addUser(user1);
    	group.addUser(user2);
    	group.addUser(user3);
    	group.addUser(user3);
    	
    	group2.addUser(user3);
    	group2.addUser(user4);
    
    	dao.saveOrUpdateUser(group);
    	dao.saveOrUpdateUser(group2);
    	dao.resetSession();
    	
    	group = dao.getGroups("agh sius").get(0);
    	group2 = dao.getGroups("grupa 2").get(0);
    	assertEquals(group.getUsers().size(), 3);
    	assertEquals(group2.getUsers().size(), 2);
    }
    

}
