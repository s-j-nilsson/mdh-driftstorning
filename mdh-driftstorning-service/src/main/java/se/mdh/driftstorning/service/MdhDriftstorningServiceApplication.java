package se.mdh.driftstorning.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"se.mdh.driftstorning"})
public class MdhDriftstorningServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MdhDriftstorningServiceApplication.class, args);
	}
}
