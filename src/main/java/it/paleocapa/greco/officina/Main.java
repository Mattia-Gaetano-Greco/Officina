package it.paleocapa.greco.officina;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("it.paleocapa.greco.officina") 
@EntityScan("it.paleocapa.greco.officina")
public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

}
