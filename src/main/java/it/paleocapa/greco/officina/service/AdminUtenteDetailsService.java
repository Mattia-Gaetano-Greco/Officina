package it.paleocapa.greco.officina.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import it.paleocapa.greco.officina.DipendenteDetails;
import it.paleocapa.greco.officina.model.Utente;
import it.paleocapa.greco.officina.repository.UtenteRepository;
 
@Service
public class AdminUtenteDetailsService implements UserDetailsService {
    @Autowired private UtenteRepository repo;
 
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Utente user = repo.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("No user found with the given email");
        }
         
        return new DipendenteDetails(user);
    }
 
}