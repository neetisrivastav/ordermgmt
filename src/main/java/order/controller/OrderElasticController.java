//package order.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import order.dao.OrderRepositoryElasticSearch;
//import order.entity.OrderElastic;
//public class OrderElasticController {
//	@Autowired
//	private OrderRepositoryElasticSearch orderRepositoryElasticSearch;
//
//		@GetMapping(value = "/getorders")
//		public ResponseEntity<Iterable<OrderElastic>> getOrdersByElastic() {
//			Iterable<OrderElastic> list = orderRepositoryElasticSearch.findAll();
//			
//			return new ResponseEntity<Iterable<OrderElastic>>(list, HttpStatus.OK);
//		}
//		@PostMapping(value = "/createorderelastic")
//		public ResponseEntity<OrderElastic> createorder( @Valid @RequestBody OrderElastic order) {
//			order.setDraftDate(new Date());
//			order.setOrderStatus(Constants.DRAFT);
//			orderRepositoryElasticSearch.save(order);
//			return new ResponseEntity<OrderElastic>(order, HttpStatus.OK);
//		}
//}
