package pl.edu.agh.sius.client.http.viewmodel;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import pl.edu.agh.sius.WService;

public class ServiceUtils {
	
	private static WService service;
	
	public static WService getService(){
		if (service == null){
			JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
			factory.setServiceClass(WService.class);
			factory.setAddress("http://localhost:8080/server/Service.wsdl");
			service = (WService) factory.create();
		} 
		
		
		return service;
	}
	
}
