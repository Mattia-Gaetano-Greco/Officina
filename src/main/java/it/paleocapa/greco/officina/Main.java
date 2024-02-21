package it.paleocapa.greco.officina;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan
@EnableJpaRepositories("it.paleocapa.greco.officina.repository") 
@EntityScan("it.paleocapa.greco.officina.model")
public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

}
