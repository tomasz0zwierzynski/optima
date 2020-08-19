package pl.tomzwi.optima;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class OptimaApplication {

	public static void main(String[] args) {
		SpringApplication.run(OptimaApplication.class, args);
	}

}
