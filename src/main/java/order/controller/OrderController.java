package order.controller;

import java.time.LocalDate;


import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import order.dao.OrderRepository;

import order.entity.Order;

import order.exceptionhandler.OrderNotFoundException;
import order.util.Constants;

/**
 * @author Neeti
 *
 */
@RestController
public class OrderController {
	@Autowired
	private OrderRepository orderRepository;

//	@Autowired
//	private OrderRepositoryElasticSearch orderRepositoryElasticSearch;
	/**
	 * @param orderid, to get the order details for specific order id
	 * @return return order details corresponding to order id 
	 */
	@GetMapping(value = "/orderdetails/{orderid}")
	public ResponseEntity<Order> orderDetails(@PathVariable(name = "orderid") String orderid) {
		Optional<Order> order = orderRepository.findById(Long.parseLong(orderid));
		if(!order.isPresent()) {
			throw new OrderNotFoundException("invalid order id : "+orderid);
			}
		return new ResponseEntity<Order>(order.get(),HttpStatus.OK);
	}
	/**
	 * @return return all over due orders till now
	 */
	@GetMapping(value = "/overdueorders")
	public ResponseEntity<List<Order>> overdueorders() {
		LocalDate dueDate = LocalDate.now().minusDays(7);
		Date dt = java.util.Date.from(dueDate.atStartOfDay()
			      .atZone(ZoneId.systemDefault())
			      .toInstant());
		return new ResponseEntity<List<Order>>(orderRepository.getOverDueOrders(dt), HttpStatus.OK);
	}
	/**
	 * @return return all orders till now
	 */
	@GetMapping(value = "/orders")
	public ResponseEntity<List<Order>> getorders() {
		
		return new ResponseEntity<List<Order>>(orderRepository.findAll(), HttpStatus.OK);
	}
	/**
	 * @param order take order details to add into database
	 * @return saved order details
	 */
	@PostMapping(value = "/createorder")
	public ResponseEntity<Order> createorder( @Valid @RequestBody Order order) {
		order.setDraftDate(new Date());
		order.setOrderStatus(Constants.DRAFT);
		orderRepository.save(order);
		return new ResponseEntity<Order>(order, HttpStatus.OK);
	}

	/**
	 * @param orderid to update status of order
	 * @return updated order details
	 */
	@PutMapping(value = "/updatestatus/{orderid}")
	public ResponseEntity<Order> updatestatus( @PathVariable(name = "orderid") String orderid) {
		Order order = orderRepository.findById(Long.parseLong(orderid)).get();
		if(order==null)
			throw new OrderNotFoundException("invalid order id : "+orderid);
		if (order.getDraftDate() != null && order.getReadyDate() == null) {
			order.setReadyDate(new Date());
			order.setOrderStatus(Constants.READY);
		} else if (order.getReadyDate() != null && order.getInProgressDate() == null) {
			order.setInProgressDate(new Date());
			order.setOrderStatus(Constants.INPROGRESS);
		}else if (order.getInProgressDate() != null && order.getCompletionDate() == null) {
			order.setCompletionDate(new Date());
			order.setOrderStatus(Constants.COMPLETED);
		}
		order.setUpdateDate(new Date());
		orderRepository.save(order);
		return new ResponseEntity<Order>(order, HttpStatus.OK);
	}

}
