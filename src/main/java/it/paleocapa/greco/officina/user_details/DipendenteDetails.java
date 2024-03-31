package it.paleocapa.greco.officina.user_details;

import java.util.Collection;
import java.io.Serializable;
 
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import it.paleocapa.greco.officina.model.Dipendente;
 
public class DipendenteDetails implements UserDetails {
    private Dipendente user;
     
    public DipendenteDetails(Dipendente user) {
        this.user = user;
    }
 
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public Dipendente getUser() {
        return user;
    }

    public void setUser(Dipendente user) {
        this.user = user;
    }
    
    @Override
    public String getPassword() {
        return user.getPassword();
    }
 
    @Override
    public String getUsername() {
        return user.getEmail();
    }
 
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
 
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
 
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
 
    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "{" +
            " user='" + getUser().toString() + "'" +
            "}";
    }
 
}