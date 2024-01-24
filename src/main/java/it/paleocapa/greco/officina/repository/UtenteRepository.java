package it.paleocapa.greco.officina.repository;
 
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.paleocapa.greco.officina.model.Utente;
 
@Repository
public interface UtenteRepository extends CrudRepository<Utente, Integer> {
    public Utente findByEmail(String email);
}