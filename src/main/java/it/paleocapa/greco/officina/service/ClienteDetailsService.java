package it.paleocapa.greco.officina.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import it.paleocapa.greco.officina.model.Cliente;
import it.paleocapa.greco.officina.repository.ClienteRepository;
import it.paleocapa.greco.officina.user_details.ClienteDetails;
 
@Service
public class ClienteDetailsService implements UserDetailsService {
    @Autowired private ClienteRepository repo;
 
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Cliente user = repo.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("No user found with the given email");
        }
         
        return new ClienteDetails(user);
    }

    public void updateCliente(Cliente cliente) {
        repo.save(cliente);
    }
 
}