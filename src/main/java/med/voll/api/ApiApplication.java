package med.voll.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//usuario por defecto user
//localhost:8080/v3/api-docs
//localhost:8080/swagger-ui.html
//para documentacion

@SpringBootApplication
public class ApiApplication {

	public static void main(String[] args) {

		SpringApplication.run(ApiApplication.class, args);
	}

}
