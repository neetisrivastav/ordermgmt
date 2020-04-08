package order;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import order.controller.OrderController;
import order.dao.OrderRepository;
import order.entity.Order;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class OrederApplicationTestJUnit5MockitoAndSpringBoot
{
	  	@InjectMocks
	    OrderController orderController;
	     
	    @Mock
	    OrderRepository orderRepository;
	     
	    @Test
	    public void testAddEmployee() 
	    {
	        MockHttpServletRequest request = new MockHttpServletRequest();
	        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
	        Order order = new Order(900, new Date(), "test", new Date(), "INR");
	        Order order1 = new Order(600, new Date(), "test", new Date(), "INR");
	        List<Order> list = new ArrayList<>();
	        list.add(order1);
	        when(orderRepository.getOverDueOrders(new Date())).thenReturn(list);
	         
	        
	        ResponseEntity<List<Order>> responseEntity = orderController.getorders();
	         
	        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
	      //  assertThat(responseEntity.getHeaders().getLocation().getPath()).isEqualTo("/1");
	    }
    
}



