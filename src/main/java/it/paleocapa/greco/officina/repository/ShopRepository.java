package it.paleocapa.greco.officina.repository;
 
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.paleocapa.greco.officina.model.Shop;
 
@Repository
public interface ShopRepository extends CrudRepository<Shop, Integer> {
}