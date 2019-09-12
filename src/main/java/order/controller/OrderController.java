package order.controller;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import order.dao.OrderRepository;
import order.entity.Order;

@RestController
public class OrderController {
	@Autowired
	private OrderRepository orderRepository;

	@GetMapping(value = "/orderform")
	public String orderform(ModelMap model) {
		model.addAttribute("order", new Order());
		return "orderform";
	}

	@GetMapping(value = "/orderdetails/{orderid}")
	public Order orderDetails(ModelMap model, @PathVariable(name = "orderid") String orderid) {
		return orderRepository.findById(Long.parseLong(orderid)).get();
	}
	@GetMapping(value = "/overdueorders")
	public List<Order> overdueorders(ModelMap model) {
		LocalDate dueDate = LocalDate.now().minusDays(7);
		Date dt = java.util.Date.from(dueDate.atStartOfDay()
			      .atZone(ZoneId.systemDefault())
			      .toInstant());
		return orderRepository.getOverDueOrders(dt);
	}

	@PostMapping(value = "/saveorderdetails")
	@ResponseBody
	public String saveOrderDetails(ModelMap model, @ModelAttribute(name = "order") @Valid Order order,
			BindingResult results) {
		if (results.hasErrors()) {
			model.addAttribute("order", order);
			return "orderform";
		}
		order.setDraftDate(new Date());
		orderRepository.save(order);
		return "success";
	}

	@PutMapping(value = "/changeorderdetails/{orderid}")
	public String changeOrderStatus(ModelMap model, @PathVariable(name = "orderid") String orderid) {
		Order order = orderRepository.findById(Long.parseLong(orderid)).get();
		String status = "";
		if (order.getDraftDate() != null && order.getReadyDate() == null) {
			order.setReadyDate(new Date());
			status = "ready state";
		} else if (order.getReadyDate() != null && order.getInProgressDate() == null) {
			order.setInProgressDate(new Date());
			status = "in progress";
		}else if (order.getInProgressDate() != null && order.getCompletionDate() == null) {
			order.setCompletionDate(new Date());
			status = "completed stage";
		}
		order.setUpdateDate(new Date());
		orderRepository.save(order);
		return "successfully moved to "+status;
	}

}
