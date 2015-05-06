package agh.sius.server.dao;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class OrderTest extends TestCase {

    public OrderTest( String testName ) {
        super( testName );
    }


    public static Test suite() {
        return new TestSuite( OrderTest.class );
    }


    public void testApp() {
        assertTrue( true );
    }
}
