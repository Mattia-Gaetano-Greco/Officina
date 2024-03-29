package it.paleocapa.greco.officina;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import it.paleocapa.greco.officina.model.Shop;
import it.paleocapa.greco.officina.repository.ShopRepository;
import it.paleocapa.greco.officina.user_details.AdminDetails;
import it.paleocapa.greco.officina.user_details.DipendenteDetails;

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

    @RequestMapping(value = "/dipendente_login", method = RequestMethod.GET)
    public String loginDipendente(Model model) {
        return "dipendente/login";
    }

    @RequestMapping(value = "/admin_login", method = RequestMethod.GET)
    public String loginAdmin(Model model) {
        return "admin/login";
    }

    // dipendente pages
    
    @RequestMapping(value="/dipendente/home", method=RequestMethod.GET)
    public String homeDipendente(Model model) {
        DipendenteDetails dipendenteDetails = (DipendenteDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("principal", dipendenteDetails);
        return "dipendente/home";
    }

    // admin pages

    @RequestMapping(value="/admin/home", method=RequestMethod.GET)
    public String homeAdmin(Model model) {
        AdminDetails adminDetails = (AdminDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("principal", adminDetails);
        model.addAttribute("shops", shopRepository.findById_admin(adminDetails.getId_admin()));
        return "admin/home";
    }

    // pagine officina

    @RequestMapping(value="/admin/officina", method=RequestMethod.GET)
    public String infoOfficina(@RequestParam("id") String itemid, Model model) {
        model.addAttribute("shop", getOfficina(itemid));
        return "admin/officina";
    }

    @RequestMapping(value="/admin/nuova_officina", method=RequestMethod.GET)
    public String nuovaOfficina(Model model) {
        int id_shop = newOfficina();
        model.addAttribute("shop", getOfficina(id_shop+""));
        return "admin/modifica_officina";
    }
    
    @RequestMapping(value="/admin/elimina_officina", method=RequestMethod.GET)
    public RedirectView eliminaOfficina(@RequestParam("id") String itemid, Model model) {
        deleteOfficina(itemid);
        return new RedirectView("/admin/home");
    }
    
    @RequestMapping(value="/admin/modifica_officina", method=RequestMethod.GET)
    public String modificaOfficina(@RequestParam("id") String itemid, Model model) {
        model.addAttribute("shop", getOfficina(itemid));
        return "admin/modifica_officina";
    }

    @RequestMapping(value="/admin/modifica_officina", method=RequestMethod.POST)
    public RedirectView modificaOfficinaPost(@ModelAttribute Shop shop, Model model) {
        updateOfficina(shop);
        return new RedirectView("/admin/home");
    }

    // API - officina

    @RequestMapping(value="/api/officina/get_officina", method=RequestMethod.GET)
    public Shop getOfficina(@RequestParam("id") String itemid) {
        Optional<Shop> shops = shopRepository.findById(Integer.parseInt(itemid));
        if (shops.isPresent())
            return shops.get();
        else
            return new Shop();
    }

    @RequestMapping(value="/api/officina/new_officina", method=RequestMethod.POST)
    public int newOfficina() {
        Shop s = new Shop("New shop", ((AdminDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getAdmin());
        shopRepository.save(s);
        return s.getId_shop();
    }

    @RequestMapping(value="/api/officina/update_officina", method=RequestMethod.POST)
    public void updateOfficina(@ModelAttribute Shop shop) {
        shopRepository.save(shop);
    }

    @RequestMapping(value="/api/officina/delete_officina", method=RequestMethod.GET)
    public void deleteOfficina(@RequestParam("id") String itemid) {
        shopRepository.deleteById(Integer.parseInt(itemid));
    }


}
