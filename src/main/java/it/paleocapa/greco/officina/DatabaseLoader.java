package it.paleocapa.greco.officina;
 /* 
import java.util.List;
 
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.*;

import it.paleocapa.greco.officina.model.Dipendente;
import it.paleocapa.greco.officina.model.Utente;
import it.paleocapa.greco.officina.repository.DipendenteRepository;
import it.paleocapa.greco.officina.repository.UtenteRepository;
 
@Configuration
public class DatabaseLoader {
 
    private UtenteRepository userRepo;
    private DipendenteRepository customerRepo;
 
    public DatabaseLoader(UtenteRepository userRepo, DipendenteRepository customerRepo) {
        this.userRepo = userRepo;
        this.customerRepo = customerRepo;
    }
 
    @Bean
    public CommandLineRunner initializeDatabase() {
        return args -> {
            Utente user1 = new Utente("david", "123", "david@gmail.com", "213131311", "david123");
            Utente user2 = new Utente("john", "2020" ,"john@yahoo.com", "1234567890", "john2020");


            userRepo.saveAll(List.of(user1, user2));
             
            Dipendente customer1 = new Dipendente("nam", "codejava", "nam@codejava.net", "1234567654", "nam2022");
            Dipendente customer2 = new Dipendente("ravi", "2121", "ravi@gmail.com", "1234547890", "ravi2121");
             
            customerRepo.saveAll(List.of(customer1, customer2));
             
            System.out.println("Database initialized");
        };
    }
}*/