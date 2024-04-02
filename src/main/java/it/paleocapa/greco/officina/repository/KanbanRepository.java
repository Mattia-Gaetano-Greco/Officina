package it.paleocapa.greco.officina.repository;
 
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.paleocapa.greco.officina.model.Kanban;
import java.util.*;
 
@Repository
public interface KanbanRepository extends CrudRepository<Kanban, Integer> {
    Optional<Kanban> findByNome(String nome);

    @Query("SELECT s FROM Kanban s WHERE s.id_shop = :id_shop")
    List<Kanban> findById_shop(Long id_shop);

    @Query("SELECT s FROM Kanban s WHERE s.id_shop = :id_shop AND s.posizione = :posizione")
    Optional<Kanban> findById_shopAndPosizione(Long id_shop, int posizione);

    @Query("SELECT nome from Kanban")
    List<String> findAll_Nome();
}