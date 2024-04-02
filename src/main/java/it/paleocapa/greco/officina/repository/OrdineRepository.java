package it.paleocapa.greco.officina.repository;
 
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.paleocapa.greco.officina.model.Ordine;
import java.util.*;
 
@Repository
public interface OrdineRepository extends CrudRepository<Ordine, Integer> {
    @Query("SELECT s FROM Ordine s WHERE s.id_kanban = :id_kanban")
    List<Ordine> findById_kanban(Long id_kanban);

    @Query("SELECT s FROM Ordine s WHERE s.id_ordine = :id_ordine")
    Optional<Ordine> findById_ordine(Long id_ordine);
}