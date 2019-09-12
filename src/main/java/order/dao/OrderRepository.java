package order.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import order.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{ 

}
