package cst438hw3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("cst438hw3.service.CityService.java")
public class Cst438hw3Application {

	public static void main(String[] args) {
		SpringApplication.run(Cst438hw3Application.class, args);
	}

}
