package agh.sius.server.dao;

import java.util.List;

import org.hibernate.Session;

import agh.sius.server.common.HibernateUtil;
import agh.sius.server.pojo.Billing;
import agh.sius.server.pojo.Group;
import agh.sius.server.pojo.Order;
import agh.sius.server.pojo.User;

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
	public void saveOrUpdate(Object obj){
		session.beginTransaction();
		try {
			session.saveOrUpdate(obj);
			session.getTransaction().commit();
		} catch (Exception ex){
			session.getTransaction().rollback();
			throw ex;
		}		
	}
	
	public User getUser(long id){
		return (User) session.get(User.class, id);
	}	

	public User getUser(String login){
		@SuppressWarnings("unchecked")
		List<User> list = session.createQuery("from User user where user.login = :login").setParameter("login", login).list();	
		if (list.size() != 1)
			return null;
		return list.get(0);
	}

	// GROUP POJO
	public Group getGroup(long id){
		return (Group) session.get(Group.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Group> getGroups(String namePattern){
		return session.createQuery("from Group where name like :name").setParameter("name", "%" + namePattern + "%").list();	
	}
	
	@SuppressWarnings("unchecked")
	public List<Group> getGroups(){
		return session.createQuery("from Group").list();	
	}
	
	// ORDER POJO
	public Order getOrder(long id){
		return (Order) session.get(Order.class, id);
	}
	
	
	// BILLING POJO
	public Billing getBilling(long id){
		return (Billing) session.get(Billing.class, id);
	}
	
	public Billing getBilling(User user1, User user2){
		@SuppressWarnings("unchecked")
		List<Billing> list = session.createQuery("from Billing as b where (b.first = :u1 and b.second = :u2) or (b.first = :u2 and b.second = :u1)")
			.setParameter("u1", user1)
			.setParameter("u2", user2)
			.list();
		
		if (list.size() != 1)
			return null;
		return list.get(0);
	}
	
	@SuppressWarnings("unchecked")
	public List<Billing> getBillings(User user){
		return session.createQuery("from Billing as b where b.first = :u1 or b.second = :u1")
				.setParameter("u1", user).list();
	}
}
