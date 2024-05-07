package it.paleocapa.greco.officina.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

import it.paleocapa.greco.officina.user_details.ClienteDetails;
import it.paleocapa.greco.officina.util.Utilities;
import it.paleocapa.greco.officina.util.controllers_common_code.implementations.UtenteInterfaceImplementation;
import it.paleocapa.greco.officina.util.controllers_common_code.interfaces.UtenteInterface;

@Controller
@RequestMapping("/cliente")
public class ClienteController implements UtenteInterface<ClienteDetails> {

    @RequestMapping(value="/home", method=RequestMethod.GET)
    public String homeCliente(Model model) {
        Utilities.putPrincipal(model, ClienteDetails.class);
        return "cliente/home";
    }

    @RequestMapping(value="/aggiorna_dati", method=RequestMethod.POST)
    public RedirectView aggiornaDatiPost(Model model, ClienteDetails user) {
        return UtenteInterfaceImplementation.aggiornaDatiPost(model, user);
    }

}