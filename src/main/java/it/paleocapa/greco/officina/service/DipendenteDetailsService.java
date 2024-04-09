package it.paleocapa.greco.officina.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import it.paleocapa.greco.officina.model.Dipendente;
import it.paleocapa.greco.officina.repository.DipendenteRepository;
import it.paleocapa.greco.officina.user_details.DipendenteDetails;
 
@Service
public class DipendenteDetailsService implements UserDetailsService {
    @Autowired private DipendenteRepository repo;
 
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Dipendente user = repo.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("No user found with the given email");
        }
         
        return new DipendenteDetails(user);
    }

    public void updateUser(Dipendente dipendente) {
        repo.save(dipendente);
    }
 
}