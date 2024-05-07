package it.paleocapa.greco.officina.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import it.paleocapa.greco.officina.model.Admin;
import it.paleocapa.greco.officina.repository.AdminRepository;
import it.paleocapa.greco.officina.user_details.AdminDetails;
 
@Service
public class AdminDetailsService implements UserDetailsService {
    @Autowired private AdminRepository repo;
 
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Admin user = repo.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("No user found with the given email");
        }
         
        return new AdminDetails(user);
    }

    public void updateAdmin(Admin admin) {
        repo.save(admin);
    }
 
}