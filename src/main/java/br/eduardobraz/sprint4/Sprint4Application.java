package br.eduardobraz.sprint4;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Sprint4Application {

	@Bean
	public ModelMapper mapper(){
		return  new ModelMapper();
	}


	public static void main(String[] args) {
		SpringApplication.run(Sprint4Application.class, args);
	}

}
