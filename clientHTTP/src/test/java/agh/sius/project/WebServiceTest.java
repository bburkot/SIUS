package agh.sius.project;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import pl.edu.agh.sius.Group;
import pl.edu.agh.sius.OperationStatus;
import pl.edu.agh.sius.User;
import pl.edu.agh.sius.WService;
import pl.edu.agh.sius.server.responses.ResponseGroupDetails;
import pl.edu.agh.sius.server.responses.ResponseSimple;
import pl.edu.agh.sius.server.responses.ResponseUser;



public class WebServiceTest {
	private static WService service;
	private String token;
	private String password = "password";
	private User user;
	
	
	@Before
	public void before(){
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.setServiceClass(WService.class);
		factory.setAddress("http://localhost:8080/server/Service.wsdl");
		service = (WService) factory.create();
	}
	
	
	@Test
	public void test1(){
        user = new User();
        user.setEmail("email@com.pl");
        user.setLogin("login");
        user.setFirstname("firstname");
        user.setLastname("lastName");
        
//        ResponseSimple response = service.registerUser(user, password);
//        System.out.println(response.getMsg());
//        System.out.println(response.getUser());
//        Assert.assertNotEquals(null, response.getUser().getToken());
//        user = response.getUser();
//        
//        user.setEmail("email2@com.pl");
//        user.setFirstname("firstname 2");
//        user.setLastname("lastName 2");
//        System.out.println(user);
//        ResponseSimple responseSimple = service.updateUser(user, password);
//
//        Assert.assertEquals(OperationStatus.SUCCEED, responseSimple.getOperationStatus());
//        
//        Group group = new Group();
//        group.setName("Nowa grupa");
//        
//        ResponseGroupDetails responseGroupDetails = service.addGroup(group, user);
//        
//        Assert.assertEquals(OperationStatus.SUCCEED, responseGroupDetails.getOperationStatus());
//        Assert.assertNotEquals(null, responseGroupDetails.getGroupDetails());
//        System.out.println(responseGroupDetails.getGroupDetails());
	}

}
