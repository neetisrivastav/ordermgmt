package order.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderController {
	@GetMapping(value = "/orderdetails")
	 public String orderDetails() {
		System.out.print("hello");
		return "order";
	}
	
}
