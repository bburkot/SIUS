//package agh.sius.server.dao;
//
//import org.hibernate.Session;
//
//import agh.sius.server.common.HibernateUtil;
//import agh.sius.server.pojo.Group;
//import agh.sius.server.pojo.User;
//
//public class HelperDAO {
//	private static String[] groupsName = new String[] { "grupa 1", "grupa 2", "agh sius" };	
//	private static String[] userLogins = {"login", "login1", "login2", "login3", "login4", "user"};
//	
//	public static void clearDB(){
//		Session session = HibernateUtil.openSession();
//    	session.beginTransaction();
//    	session.createQuery("delete from Billing").executeUpdate();
//    	session.createQuery("delete from Order").executeUpdate();
//    	session.createQuery("delete from Group").executeUpdate();
//    	session.createQuery("delete from User").executeUpdate();
//    	session.getTransaction().commit();
//    	session.flush();
//    	session.clear();   	
//    	session.close();
//	}
//	
//	public static void createGroups(DAO dao){				
//		for (String groupName : groupsName)
//			dao.saveOrUpdate(new Group(groupName));	
//	}
//	
//	public static void createUsers(DAO dao){		
//		int i = 0;
//		for (String login : userLogins){
//			User user = new User (login , "name" + i, "surname" + i, "email" + i, "password" + i);
//			dao.saveOrUpdate(user);	
//			++i;
//		}
//	}
//	
//	public static void addUsersToGroups(DAO dao, String[][] tab ){
//		for (int i=0; i<tab.length; i++){
//			for (int j=1; j<tab[i].length; j++){
//				addUserToGroup(dao,tab[i][0], tab[i][j]);
//			}
//		}
//	}
//	
//	public static void addUserToGroup(DAO dao, String userLogin, String groupName){
//		User user = dao.getUser(userLogin);
//		Group group = dao.getGroups(groupName).get(0);
//		try{
//			group.addUser(user);
//		} catch (Exception ex){
//			System.err.println(ex.getMessage() + " user login = " + userLogin);
//		}
//		dao.saveOrUpdate(group);
//	}
//}
