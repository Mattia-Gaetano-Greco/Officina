package it.paleocapa.greco.officina.repository;
 
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.paleocapa.greco.officina.model.Admin;
 
@Repository
public interface AdminRepository extends CrudRepository<Admin, Long> {
    public Admin findByEmail(String email);
}