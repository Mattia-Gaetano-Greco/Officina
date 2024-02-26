package it.paleocapa.greco.officina;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.paleocapa.greco.officina.model.Shop;
import it.paleocapa.greco.officina.repository.ShopRepository;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;


@Controller
public class MainController {

    @Autowired private ShopRepository shopRepository;

    // public pages

    @RequestMapping("/")
    public String homePage(Model model) {
        return "index";
    }

    @RequestMapping(value = "/dipendente/login", method = RequestMethod.GET)
    public String loginDipendente(Model model) {
        return "dipendente/login";
    }

    @RequestMapping(value = "/admin/login", method = RequestMethod.GET)
    public String loginAdmin(Model model) {
        return "admin/login";
    }

    @RequestMapping(value = "/admin/login", method = RequestMethod.POST)
    public String loginAdminPost(Model model) {
        return "admin/home";
    }

    // dipendente pages
    
    @RequestMapping(value="/dipendente/home", method=RequestMethod.GET)
    public String homeDipendente(Model model) {
        return "dipendente/home";
    }

    // admin pages

    @RequestMapping(value="/admin/home", method=RequestMethod.GET)
    @Query("SELECT * FROM SHOP")
    public String homeAdmin(Model model) {
        model.addAttribute("principal", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        Iterable<Shop> shops = shopRepository.findAll();
        ArrayList <Shop> shopList = new ArrayList<Shop>();
        for (Shop shop : shops) {
            shopList.add(shop);
        }
        model.addAttribute("principal", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        model.addAttribute("shops", shopList);
        return "admin/home";
    }

    // pagine officina

    @RequestMapping(value="/admin/officina", method=RequestMethod.GET)
    public String infoOfficina(@RequestParam("id") String itemid, Model model) {
        Optional<Shop> shops = shopRepository.findById(Integer.parseInt(itemid));
        if (shops.isPresent()) {
            model.addAttribute("shop", shops.get());
        } else {
            model.addAttribute("shop", new Shop());
        }
        return "admin/officina";
    }

    @RequestMapping(value="/admin/nuova_officina", method=RequestMethod.GET)
    public String nuovaOfficina(Model model) {
        model.addAttribute("shop", new Shop());
        return "admin/nuova_officina";
    }

    @RequestMapping(value="/admin/nuova_officina", method=RequestMethod.POST)
    public RedirectView requestMethodName(@ModelAttribute Shop shop, Model model) {
        shopRepository.save(shop);
        return new RedirectView("/admin/home");
    }
    
    @RequestMapping(value="/admin/elimina_officina", method=RequestMethod.GET)
    public RedirectView eliminaOfficina(@RequestParam("id") String itemid, Model model) {
        shopRepository.deleteById(Integer.parseInt(itemid));
        return new RedirectView("/admin/home");
    }
    
    @RequestMapping(value="/admin/modifica_officina", method=RequestMethod.GET)
    public String modificaOfficina(@RequestParam("id") String itemid, Model model) {
        Optional<Shop> shops = shopRepository.findById(Integer.parseInt(itemid));
        if (shops.isPresent()) {
            model.addAttribute("shop", shops.get());
        } else {
            model.addAttribute("shop", new Shop());
        }
        return "admin/modifica_officina";
    }

    @RequestMapping(value="/admin/modifica_officina", method=RequestMethod.POST)
    public RedirectView modificaOfficinaPost(@ModelAttribute Shop shop, Model model) {
        shopRepository.save(shop);
        return new RedirectView("/admin/home");
    }

}
