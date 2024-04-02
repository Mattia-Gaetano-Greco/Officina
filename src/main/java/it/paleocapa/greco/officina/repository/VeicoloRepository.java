package it.paleocapa.greco.officina.repository;
 
import java.util.*;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.paleocapa.greco.officina.model.Veicolo;
import it.paleocapa.greco.officina.model.VeicoloId;
 
@Repository
public interface VeicoloRepository extends CrudRepository<Veicolo, VeicoloId> {
    @Query("SELECT targa FROM Veicolo")
    public List<String> findAll_Targa();

    public Optional<Veicolo> findByTarga(String targa);
}