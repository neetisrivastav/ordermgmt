package order;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class OrderDemoApplication {
	 private static Logger LOGGER = LogManager.getLogger(OrderDemoApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(OrderDemoApplication.class, args);
		LOGGER.info("Simple log statement with inputs 1, 2 and 3");
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
