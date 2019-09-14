package order;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import order.entity.Order;

/**
 * @author Neeti
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderApplicationTests {
	
	/**
	 * to test addition of new order
	 */
	@Test
	public void testAddSaveOrder() {
		RestTemplate restTemplate = new RestTemplate();
		final String baseUrl = "http://localhost:8081/createorder/";
		Calendar cal = Calendar.getInstance();
		cal.set(2019, 10, 20);
		Order order = new Order(12, cal.getTime(), "T-Shirts", new Date(), "INR");
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<Order> request = new HttpEntity<>(order, headers);
		ResponseEntity<Order> result = restTemplate.postForEntity(baseUrl, request, Order.class);
		Assert.assertEquals(200, result.getStatusCodeValue());
		Assert.assertEquals(true, result.getBody().getId() > 0);
	}

	/**
	 * to test whether we getting list of orders
	 */
	@Test
	public void testGetOrderListSuccess() {
		RestTemplate restTemplate = new RestTemplate();
		final String baseUrl = "http://localhost:8081/orders";
		ResponseEntity<List<Order>> result = restTemplate.exchange(baseUrl, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Order>>() {
				});
		Assert.assertEquals(200, result.getStatusCodeValue());
		Assert.assertEquals(true, result.getBody().size() >= 0);
	}

	/**
	 * to test whether updating order status is working or not
	 */
	@Test
	public void testUpdateStatus() {
		RestTemplate restTemplate = new RestTemplate();
		final String baseUrl = "http://localhost:8081/updatestatus/18";
		ResponseEntity<Order> result = restTemplate.exchange(baseUrl, HttpMethod.PUT,null, Order.class);
		Order order = result.getBody();
		Assert.assertEquals(200, result.getStatusCodeValue());
		if (order == null)
			Assert.fail();
		if (order.getDraftDate() != null && order.getReadyDate() == null) {
			Assert.assertEquals(true, order.getReadyDate() != null);
		} else if (order.getReadyDate() != null && order.getInProgressDate() == null) {
			Assert.assertEquals(true, order.getInProgressDate() != null);
		} else if (order.getInProgressDate() != null && order.getCompletionDate() == null) {
			Assert.assertEquals(true, order.getCompletionDate() != null);
		}
	}

}
