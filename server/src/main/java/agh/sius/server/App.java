package agh.sius.server;

import org.hibernate.Session;

import agh.sius.server.common.HibernateUtil;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	Session session = HibernateUtil.openSession();
        System.out.println( "Hello World!" );
        
        session.close();
        HibernateUtil.getSessionFactory().close();
        
    }
}
