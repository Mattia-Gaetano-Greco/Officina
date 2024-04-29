package it.paleocapa.greco.officina.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import it.paleocapa.greco.officina.user_details.ClienteDetails;
import it.paleocapa.greco.officina.utilities.Utilities;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

    @RequestMapping(value="/home", method=RequestMethod.GET)
    public String homeCliente(Model model) {
        ClienteDetails clienteDetails = Utilities.getAuthenticatedUser(ClienteDetails.class);
        model.addAttribute("principal", clienteDetails);
        return "cliente/home";
    }

}