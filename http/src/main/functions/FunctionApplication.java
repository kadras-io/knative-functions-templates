package functions;

import java.util.function.Function;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FunctionApplication {

	public static void main(String[] args) {
		SpringApplication.run(FunctionApplication.class, args);
	}

	@Bean
	Function<String, String> echo() {
		return message -> message;
	}

}
