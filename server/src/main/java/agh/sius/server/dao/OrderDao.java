package agh.sius.server.dao;

import org.hibernate.Session;

import agh.sius.server.common.HibernateUtil;

public class OrderDao {
	private Session session;
	
	public OrderDao(){
		session = HibernateUtil.openSession();
	}
	
	public void resetSession(){
		session.flush();
		session.clear();
		session.close();
		session = HibernateUtil.openSession();
	}
	
	public void saveOrUpdateUser(Object obj){
		session.beginTransaction();
		try {
			session.saveOrUpdate(obj);
			session.getTransaction().commit();
		} catch (Exception ex){
			session.getTransaction().rollback();
			throw ex;
		}		
	}
	
}
