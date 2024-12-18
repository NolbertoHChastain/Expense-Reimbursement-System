package app.ers.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EntityScan("app.ers.model") // have spring data scan 'model' package for 'entity' annotation classes
@SpringBootApplication
public class EmployeeReimbursementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeReimbursementSystemApplication.class, args);
	}

}
