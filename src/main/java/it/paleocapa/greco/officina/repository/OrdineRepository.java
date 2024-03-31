package it.paleocapa.greco.officina.repository;
 
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.paleocapa.greco.officina.model.Ordine;
import java.util.List;
 
@Repository
public interface OrdineRepository extends CrudRepository<Ordine, Integer> {
    @Query("SELECT s FROM Ordine s WHERE s.id_kanban = :id_kanban")
    List<Ordine> findById_kanban(Long id_kanban);
}