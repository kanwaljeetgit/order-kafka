package com.sapient.orderkafka;

import com.sapient.orderkafka.model.Order;
import com.sapient.orderkafka.repository.OrderRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import java.util.List;

@SpringBootApplication
public class OrderKafkaApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(OrderKafkaApplication.class, args);
		OrderRepository bean = run.getBean(OrderRepository.class);
		List<Order> allByOrderByAmountDesc = bean.findAllByOrderByAmountDesc();
		System.out.println(allByOrderByAmountDesc);

	}

	@Configuration
	public static class SecurityPermitAllConfig extends WebSecurityConfigurerAdapter {
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.authorizeRequests().anyRequest().permitAll()
					.and().csrf().disable();
		}
	}



}
