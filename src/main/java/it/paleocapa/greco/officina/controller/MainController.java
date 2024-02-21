package it.paleocapa.greco.officina.controller;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

    // public pages

    @RequestMapping("/")
    public String homePage(Model model) {
        return "index";
    }

    @RequestMapping(value = "/dipendente/login", method = RequestMethod.GET)
    public String loginDipendente(Model model) {
        
        //model.addAttribute("dipendente", new Utente());
        return "dipendente/login";
    }

    @RequestMapping(value = "/admin/login", method = RequestMethod.GET)
    public String loginAdmin(Model model) {
        return "admin/login";
    }

    // dipendente pages

    /*@RequestMapping(value = "/profilo-dipendente", method = RequestMethod.POST)
    public String checkLoginDipendente(@ModelAttribute("dipendente") Utente dipendente) {
        return "profilo-dipendente";
    }*/
    @RequestMapping(value="/dipendente/home", method=RequestMethod.GET)
    public String homeDipendente(Model model) {
        model.addAttribute("principal", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return "dipendente/home";
    }

    // admin pages

    @RequestMapping(value="/admin/home", method=RequestMethod.GET)
    public String homeAdmin(Model model) {
        model.addAttribute("principal", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return "admin/home";
    }
    
}
