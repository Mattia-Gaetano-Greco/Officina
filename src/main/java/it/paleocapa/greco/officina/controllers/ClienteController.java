package it.paleocapa.greco.officina.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.paleocapa.greco.officina.user_details.ClienteDetails;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

    @RequestMapping(value="/home", method=RequestMethod.GET)
    public String homeCliente(Model model) {
        ClienteDetails clienteDetails = ClienteUtils.getAuthenticatedClienteDetails();
        model.addAttribute("principal", clienteDetails);
        return "cliente/home";
    }

}

class ClienteUtils {

    static ClienteDetails getAuthenticatedClienteDetails() {
        return (ClienteDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}