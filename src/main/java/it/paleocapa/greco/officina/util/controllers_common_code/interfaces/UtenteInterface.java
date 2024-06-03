package it.paleocapa.greco.officina.util.controllers_common_code.interfaces;

import org.apache.catalina.connector.Response;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.view.RedirectView;

public interface UtenteInterface <T extends UserDetails> {
    public RedirectView aggiornaDatiPost(Model model, @ModelAttribute("principal") T user);
    public Response aggiornaPasswordPost(Model model, String json);
}