package it.paleocapa.greco.officina.utilities;

import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.client.RestTemplate;

import it.paleocapa.greco.officina.model.Admin;
import it.paleocapa.greco.officina.model.Dipendente;
import it.paleocapa.greco.officina.user_details.AdminDetails;
import it.paleocapa.greco.officina.user_details.DipendenteDetails;

public class Utilities {

    public static <T> T getFromAPI(String uri, Class<T> object_class) {
        uri = Endpoints.toAbsolutePathRequest(uri);
        return (T) new RestTemplate().getForObject(uri+"", object_class);
    }

    public static <T> T postToAPI(String uri, Object object, Class<T> object_class) {
        uri = Endpoints.toAbsolutePathRequest(uri);
        return new RestTemplate().postForObject(uri+"", object, object_class);
    }

    public static <ST extends UserDetails> void updatePrincipal(ST userDetails) {
        if (userDetails.getClass() == DipendenteDetails.class) {
            Dipendente updatedUser = ((DipendenteDetails)userDetails).getUser();
            Utilities.postToAPI("/api/dipendente/update", updatedUser, Void.class);
            LoggerFactory.getLogger(Utilities.class).info(updatedUser.toString());
            DipendenteDetails principal = (DipendenteDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            principal.setUser(updatedUser);
        } else if (userDetails.getClass() == AdminDetails.class) {
            Admin updatedUser = ((AdminDetails)userDetails).getUser();
            Utilities.postToAPI("/api/admin/update", updatedUser, Void.class);
            LoggerFactory.getLogger(Utilities.class).info(updatedUser.toString());
            AdminDetails principal = (AdminDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            principal.setUser(updatedUser);
        }
    }
    
}
