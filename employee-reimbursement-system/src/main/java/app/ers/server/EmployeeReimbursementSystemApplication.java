package app.ers.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("app.ers") // have spring scan in app.ers for Beans (stereotype annotations)
@EntityScan("app.ers.model") // have spring data scan 'model' package for 'entity' annotation classes
@EnableJpaRepositories("app.ers.repository") // have Spring scan 'repository' package for JPA repositories
public class EmployeeReimbursementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeReimbursementSystemApplication.class, args);
	}

}