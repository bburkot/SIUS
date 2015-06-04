package pl.edu.agh.sius.server.common;

import java.util.Set;

import javax.persistence.Entity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.reflections.Reflections;

import pl.edu.agh.sius.server.dao.DAO;
import pl.edu.agh.sius.server.pojo.User;

public class HibernateUtil {
	private static final SessionFactory sessionFactory;
	
    static {
        try {  
        	 	       
        	Configuration conf = new Configuration().configure("hibernate.cfg.xml");                
            Reflections reflections = new Reflections(User.class.getPackage().getName());
            
            Set<Class<?>> classes = reflections.getTypesAnnotatedWith(Entity.class);
            for (final Class<?> clazz : classes) {
                conf.addAnnotatedClass(clazz);
            }

        	StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().applySettings(conf.getProperties()).build();
            sessionFactory = conf.buildSessionFactory( ssr );	
              
        } catch (Throwable e) {
            System.err.println("Error in creating SessionFactory object: "  + e.getMessage());
            throw new ExceptionInInitializerError(e);
        }
    }
    
    
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
	
    public static Session openSession(){
    	return sessionFactory.openSession();
    }
}
