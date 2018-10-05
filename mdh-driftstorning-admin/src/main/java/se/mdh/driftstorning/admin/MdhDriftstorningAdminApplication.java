package se.mdh.driftstorning.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"se.mdh.driftstorning.common"})
public class MdhDriftstorningAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(MdhDriftstorningAdminApplication.class, args);
	}
}
