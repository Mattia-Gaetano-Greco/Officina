package it.paleocapa.greco.officina.repository;
 
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.paleocapa.greco.officina.model.Dipendente;
 
@Repository
public interface DipendenteRepository extends CrudRepository<Dipendente, Integer> {
    public Dipendente findByEmail(String email);
}