package order.dao;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import order.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{ 
	User findByEmail(String email);
	User save(User user);
	User findByUsername(String username);
	List<User> findByUsernameContainingIgnoreCase(String username);
}
