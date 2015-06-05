package pl.edu.agh.sius.server.dao;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.type.BigDecimalType;
import org.hibernate.type.StringType;

import pl.edu.agh.sius.server.common.HibernateUtil;
import pl.edu.agh.sius.server.pojo.Billings;
import pl.edu.agh.sius.server.pojo.Group;
import pl.edu.agh.sius.server.pojo.GroupDetails;
import pl.edu.agh.sius.server.pojo.OrderDetails;
import pl.edu.agh.sius.server.pojo.Payment;
import pl.edu.agh.sius.server.pojo.Product;
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
	
	
	
	public String saveOrUpdate(Object obj){
		session.beginTransaction();
		try {			
			session.saveOrUpdate(obj);
			session.getTransaction().commit();
			return null;
		} catch (ConstraintViolationException ex){	
			System.out.println("rollback");
			session.getTransaction().rollback();
			session = HibernateUtil.openSession();
			if (ex.getSQLException() != null){
				System.err.println(ex.getSQLException().getMessage());
				return ex.getSQLException().getMessage();
			} else { 
				System.err.println(ex.getMessage());
				return ex.getMessage();
			}
		} catch (Throwable ex){
			session.getTransaction().rollback();
			session = HibernateUtil.openSession(); 
			ex.printStackTrace();
			System.out.println("rollback");
			return ex.getMessage();
		}		
	}
	
	public String delete(Object obj) {
		session.beginTransaction();
		try {
			session.delete(obj);
			session.getTransaction().commit();
			return null;
		} catch (Throwable ex){
			session.getTransaction().rollback();
			session = HibernateUtil.openSession(); 
			ex.printStackTrace();
			System.out.println("rollback");
			return ex.getMessage();
		}			
	}
	
	// USER POJO
	public User getUser(String id){
		if (id == null)
			return null;
		else
			return (User) session.get(User.class, id);
	}	
	public User getUser(String id, String token){
		User user = getUser(id);
		if (user != null && user.getToken() != null && user.getToken().equals(token) )
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
	
	@SuppressWarnings("unchecked")
	public Group getGroupByName(String name){
		List<Group> groups = session.createQuery("from Group where name = :name").setParameter("name", name).list();	
		if (groups.size() != 1)
			return null;
		return groups.get(0);
	}
	

	// ORDER POJO
	public OrderDetails getOrder(String id){
		if (id == null)
			return null;
		else
			return (OrderDetails) session.get(OrderDetails.class, id);
	}

	
	@SuppressWarnings("unchecked")
	public BigDecimal getMaxDept(User other, User logged) {	// logged asking of max debt

		List<BigDecimal> list = 
				session.createSQLQuery("SELECT [max_debt_to_first_user] FROM billing" +
				" where user_first_id = :first and user_second_id = :second")
				.addScalar("[max_debt_to_first_user]", new BigDecimalType())
				.setString("first", logged.getId())
				.setString("second", other.getId())				
				.list();
		if (list.size() == 1)
			return list.get(0);
		
		list = session.createSQLQuery("SELECT [max_debt_to_second_user] FROM billing" +
				" where user_first_id = :first and user_second_id = :second")
				.addScalar("[max_debt_to_second_user]", new BigDecimalType())
				.setString("first", other.getId())	
				.setString("second", logged.getId())							
				.list();
		
		if (list.size() == 1)
			return list.get(0);
		
		return null;
	}
	
//	// BILLING POJO
	public Billings getBillings(String id){
		if (id == null)
			return null;
		return (Billings) session.get(Billings.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public Billings getBillings(User user1, User user2) {
		if (user1.getId().equals(user2.getId()))
			return null;
		
		List<Billings> list = session.createQuery("from Billings as b where (b.first = :u1 and b.second = :u2) or (b.first = :u2 and b.second = :u1)")
			.setParameter("u1", user1)
			.setParameter("u2", user2)
			.list();
		if (list.size() == 1)
			return list.get(0);
		return null;
	}
	
	public Billings getOrCreateBillings(User user1, User user2){
		if (user1.getId().equals(user2.getId()))
			return null;
		
		Billings b = getBillings(user1, user2);
		if (b == null)
			return new Billings(user1, user2);
		return b;
	}
	
	@SuppressWarnings("unchecked")
	public List<Billings> getUserBillings(User user){
		return session.createQuery("from Billings as b where b.first = :u1 or b.second = :u1")
				.setParameter("u1", user).list();
	}

	public Product getProduct(String id) {
		if (id == null)
			return null;
		return (Product) session.get(Product.class, id);
	}

	public Payment getPayment(String id) {
		if (id == null)
			return null;
		return (Payment) session.get(Payment.class, id);
	}
	
}
