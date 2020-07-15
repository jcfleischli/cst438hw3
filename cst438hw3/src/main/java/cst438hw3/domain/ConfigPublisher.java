package cst438hw3.domain;

import org.springframework.amqp.core.FanoutExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// configuration class that will create a message exchange.
@Configuration
public class ConfigPublisher {

	@Bean
	public FanoutExchange fanout() {
		return new FanoutExchange("city-reservation");
	}
}
