package com.pm.ProcessPensionModule.Service;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.pm.ProcessPensionModule.Entity.Pensioner;

import junit.framework.Assert;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ProcessPensionServiceImplementationTest {
	
	@Mock
	private ProcessPensionServiceImplementation processPensionServiceImplementation;
	
	@LocalServerPort
	private int randomPort;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Before
	public void initMocks() {
	       MockitoAnnotations.initMocks(this);
	   }
	@Test
	public void test() throws URISyntaxException{
		final String baseURL = "http://localhost:" + randomPort + "/Process/PensionerDetailByAadhaar/10";
		URI uri = new URI(baseURL);
	
		Pensioner pensioner = new Pensioner();
		pensioner.setAadharNumber(10l);
		pensioner.setName("Sankalp");
		pensioner.setAllowance(5000D);
		pensioner.setSalaryEarned(50000);
		pensioner.setPensionType("self");
		pensioner.setBankAccountType("Private");
		
		Mockito.when(restTemplate.getForEntity(uri,Pensioner.class)).thenReturn(pensioner);
		
		ResponseEntity<Pensioner> responseEntity = this.restTemplate.getForEntity(uri,Pensioner.class);
		
		Assert.assertEquals(200, responseEntity.getStatusCodeValue());
		
//		Mockito.when().thenReturn(pensioner);
//		
//		processPensionServiceImplementation.getPensionDetail(10l)
		
		
	}

}