package order.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import order.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{ 
	
@Query("from Order where dueDate<:dueDate")
public List<Order> getOverDueOrders(@Param(value = "dueDate") Date dueDate);



}
