package it.paleocapa.greco.officina.util.controllers_common_code.implementations;

import org.apache.catalina.connector.Response;
import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.view.RedirectView;

import it.paleocapa.greco.officina.util.Utilities;

public class UtenteInterfaceImplementation {
    public static <T extends UserDetails> RedirectView aggiornaDatiPost(Model model, @ModelAttribute("principal") T userDetails) {
        String userRole = Utilities.getUserRoleFromUserDetails(userDetails.getClass());
        Utilities.updatePrincipal(userDetails);
        return new RedirectView("/"+userRole+"/home");
    }

    public static <T extends UserDetails> Response aggiornaPasswordPost(Model model, String json) {
        Response r = new Response();
        try {
            String old_password = new JSONParser(json).parseObject().get("old_password").toString();
            String new_password = new JSONParser(json).parseObject().get("new_password").toString();    
            @SuppressWarnings("unchecked")
            T userDetails = (T) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (new_password == null || old_password == null || new_password.isEmpty() || old_password.isEmpty())
                r.setStatus(400);
            else if (userDetails.getPassword().equals(old_password) && !new_password.equals(old_password))
                r.setStatus(409);
            else {
                r.setStatus(200);
                Utilities.updatePrincipal(userDetails, new_password);
            }
        } catch (ParseException e) {
            r.setStatus(500);
        }
        return r;
    }
}
