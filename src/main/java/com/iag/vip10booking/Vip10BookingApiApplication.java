package com.iag.vip10booking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableAutoConfiguration
@EnableScheduling
@EnableJpaRepositories("com.iag.vip10booking.infraestructura.persistencia.repository")
public class Vip10BookingApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(Vip10BookingApiApplication.class, args);
	}

}
