package it.paleocapa.greco.officina;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SimpleController {

    @RequestMapping("/")
    public String homePage(Model model) {
        return loginPage(model);
    }

    @RequestMapping(value = "/login-dipendente", method = RequestMethod.GET)
    public String loginPage(Model model) {
        model.addAttribute("dipendente", new Dipendente());
        return "login-dipendente";
    }

    @RequestMapping(value = "/check-login-dipendente", method = RequestMethod.POST)
    public String loginPost(@ModelAttribute("dipendente") Dipendente dipendente) {
        return "profilo-dipendente";
    }
    
}
