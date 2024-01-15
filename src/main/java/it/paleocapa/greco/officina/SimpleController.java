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
        return loginDipendente(model);
    }

    @RequestMapping(value = "/login-dipendente", method = RequestMethod.GET)
    public String loginDipendente(Model model) {
        model.addAttribute("dipendente", new Dipendente());
        return "login-dipendente";
    }

    @RequestMapping(value = "/profilo-dipendente", method = RequestMethod.POST)
    public String checkLoginDipendente(@ModelAttribute("dipendente") Dipendente dipendente) {
        return "profilo-dipendente";
        // controlla se il dipendente è presente nel database
        // se è presente, ritorna "profilo-dipendente"
        // se non è presente, ritorna "login-dipendente"
        //@Query(value = "SELECT * FROM Dipendente WHERE username = '"+ dipendente.username +"' AND password = '"+ dipendente.password +"'", nativeQuery = true);
    }
    
}
