package pl.edu.agh.sius.server;

import org.hibernate.Session;

import pl.edu.agh.sius.server.common.HibernateUtil;
import pl.edu.agh.sius.server.dao.DAO;
import pl.edu.agh.sius.server.pojo.User;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        
        
        DAO dao = new DAO();
    	User user1 = new User("user", "name", "surname", "email@m.com", "password");
    	User user2 = new User("user2", "name 2", "surname 2", "email2@m.com", "password");
    	
    	dao.saveOrUpdate(user1);
    	dao.saveOrUpdate(user2);
        
    	dao.resetSession();
    	
        HibernateUtil.getSessionFactory().close();
    }
}

