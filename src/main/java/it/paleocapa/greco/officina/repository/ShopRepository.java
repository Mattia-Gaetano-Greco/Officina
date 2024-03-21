package it.paleocapa.greco.officina.repository;
 
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.paleocapa.greco.officina.model.Shop;
import java.util.List;
 
@Repository
public interface ShopRepository extends CrudRepository<Shop, Integer> {
    @Query("SELECT s FROM Shop s WHERE s.id_admin = :id_admin")
    List<Shop> findById_admin(Long id_admin);
}