package pl.edu.agh.sius.server.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;

import pl.edu.agh.sius.server.common.HibernateUtil;
import pl.edu.agh.sius.server.pojo.Group;
import pl.edu.agh.sius.server.pojo.GroupDetails;
import pl.edu.agh.sius.server.pojo.OrderDetails;
import pl.edu.agh.sius.server.pojo.User;
import pl.edu.agh.sius.server.pojo.UserDetails;

import com.microsoft.sqlserver.jdbc.SQLServerException;


public class DAO {
	private Session session;
	
	public DAO(){
		session = HibernateUtil.openSession();
	}
	
	public void resetSession(){
		session.flush();
		session.clear();
		session.close();
		session = HibernateUtil.openSession();
	}
	
	
	// USER POJO
	public String saveOrUpdate(Object obj){
		session.beginTransaction();
		try {
			
			session.saveOrUpdate(obj);
			session.getTransaction().commit();
			return null;
		} catch (ConstraintViolationException ex){
			session.getTransaction().rollback();
			ex.printStackTrace();
			
			if (ex.getSQLException() != null)
				return ex.getSQLException().getMessage();
			else 
				return ex.getMessage();
		}		
	}
	
	public User getUser(String id){
		if (id == null)
			return null;
		else
			return (User) session.get(User.class, id);
	}	
	public User getUser(String id, String token){
		User user = getUser(id);
		if (user != null && user.getToken().equals(token) )
			return user;
		else
			return null;
	}
		
	public UserDetails getUserDetails(String id){
		if (id == null)
			return null;
		else
			return (UserDetails) session.get(UserDetails.class, id);
	}
	public UserDetails getUserDetails(String id, String token){
		UserDetails userDetails = getUserDetails(id);
		if (userDetails != null && userDetails.getToken().equals(token))
			return userDetails;
		else 
			return null;
	}

	@SuppressWarnings("unchecked")
	public User getUser(String login, String password, String authorizationProvider){
		List<User> list = (List<User>) session.createQuery("from User user where user.login = :login and user.password = :password")
			.setParameter("login", login)
			.setParameter("password", password)
			.list();	
		if (list.size() != 1)
			return null;
		return list.get(0);
	}

	// GROUP POJO
	public Group getGroup(String id){
		if (id == null)
			return null;
		else
			return (Group) session.get(Group.class, id);
	}
	
	public GroupDetails getGroupDetails(String id){
		if (id == null)
			return null;
		else
			return (GroupDetails) session.get(GroupDetails.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Group> getGroups(String namePattern){
		return session.createQuery("from Group where name like :name").setParameter("name", "%" + namePattern + "%").list();	
	}
	

	// ORDER POJO
	public OrderDetails getOrder(String id){
		if (id == null)
			return null;
		else
			return (OrderDetails) session.get(OrderDetails.class, id);
	}
	
	
//	// BILLING POJO
//	public Billing getBilling(String id){
//		return (Billing) session.get(Billing.class, id);
//	}
//	
//	public Billing getBilling(User user1, User user2){
//		@SuppressWarnings("unchecked")
//		List<Billing> list = session.createQuery("from Billing as b where (b.first = :u1 and b.second = :u2) or (b.first = :u2 and b.second = :u1)")
//			.setParameter("u1", user1)
//			.setParameter("u2", user2)
//			.list();
//		
//		if (list.size() != 1)
//			return null;
//		return list.get(0);
//	}
//	
//	@SuppressWarnings("unchecked")
//	public List<Billing> getBillings(User user){
//		return session.createQuery("from Billing as b where b.first = :u1 or b.second = :u1")
//				.setParameter("u1", user).list();
//	}
}
