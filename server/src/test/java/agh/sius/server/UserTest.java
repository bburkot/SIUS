package agh.sius.server;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class UserTest extends TestCase {

    public UserTest( String testName ) {
        super( testName );
    }


    public static Test suite() {
        return new TestSuite( UserTest.class );
    }


    public void testApp() {
        assertTrue( true );
    }
}
