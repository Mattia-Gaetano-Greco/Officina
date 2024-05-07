package it.paleocapa.greco.officina.util.controllers_common_code.implementations;

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
}
