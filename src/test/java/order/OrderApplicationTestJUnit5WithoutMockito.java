package order;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import order.entity.Order;
 
 
//@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class OrderApplicationTestJUnit5WithoutMockito 
{   
    @LocalServerPort
    int randomServerPort;
     
    @Test
    public void testCreateOrderSuccess() throws URISyntaxException 
    {
        RestTemplate restTemplate = new RestTemplate();
        final String baseUrl = "http://localhost:"+randomServerPort+"/createorder/";
        URI uri = new URI(baseUrl);
        Order order = new Order(200, new Date(), "test", new Date(), "INR");
         
        HttpHeaders headers = new HttpHeaders();
        //headers.set("X-COM-PERSIST", "true");      
 
        HttpEntity<Order> request = new HttpEntity<>(order, headers);
         
        ResponseEntity<String> result = restTemplate.postForEntity(uri, request, String.class);
         
        //Verify request succeed
        Assertions.assertEquals(200, result.getStatusCodeValue());
    }
//    @Test
//    public void testAddEmployeeMissingHeader() throws URISyntaxException 
//    {
//        RestTemplate restTemplate = new RestTemplate();
//        final String baseUrl = "http://localhost:"+randomServerPort+"/createorder/";
//        URI uri = new URI(baseUrl);
//        Order order = new Order(300, new Date(), "test", new Date(), "INR");
//         
//        HttpHeaders headers = new HttpHeaders();
// 
//        HttpEntity<Order> request = new HttpEntity<>(order, headers);
//         
//        try
//        {
//            restTemplate.postForEntity(uri, request, String.class);
//            Assertions.fail();
//        }
//        catch(HttpClientErrorException ex) 
//        {
//            //Verify bad request and missing header
//            Assertions.assertEquals(400, ex.getRawStatusCode());
//            Assertions.assertEquals(true, ex.getResponseBodyAsString().contains("Missing request header"));
//        }
//    }
//    @Test
//    public void testGetEmployeeListSuccessWithHeaders() throws URISyntaxException 
//    {
//        RestTemplate restTemplate = new RestTemplate();
//         
//        final String baseUrl = "http://localhost:"+randomServerPort+"/createorder/";
//        URI uri = new URI(baseUrl);
//         
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("X-COM-LOCATION", "USA");
// 
//        HttpEntity<Order> requestEntity = new HttpEntity<>(null, headers);
// 
//        try
//        {
//            restTemplate.exchange(uri, HttpMethod.GET, requestEntity, String.class);
//            Assertions.fail();
//        }
//        catch(HttpClientErrorException ex) 
//        {
//            //Verify bad request and missing header
//            Assertions.assertEquals(400, ex.getRawStatusCode());
//            Assertions.assertEquals(true, ex.getResponseBodyAsString().contains("Missing request header"));
//        }
//    }

}









