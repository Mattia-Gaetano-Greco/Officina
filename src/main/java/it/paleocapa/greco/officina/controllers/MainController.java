package it.paleocapa.greco.officina.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

    org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(MainController.class);

    @RequestMapping("/")
    public String homePage(Model model) {
        return "index";
    }

    @RequestMapping(value = "/dipendente_login", method = RequestMethod.GET)
    public String loginDipendente(Model model) {
        return "dipendente/login";
    }

    @RequestMapping(value = "/admin_login", method = RequestMethod.GET)
    public String loginAdmin(Model model) {
        return "admin/login";
    }

    @RequestMapping(value = "/cliente_login", method = RequestMethod.GET)
    public String loginCliente(Model model) {
        return "cliente/login";
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDenied(Model mode) {
        return "403";
    }

}
