package it.paleocapa.greco.officina;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.paleocapa.greco.officina.model.Admin;
import it.paleocapa.greco.officina.model.Kanban;
import it.paleocapa.greco.officina.model.Ordine;
import it.paleocapa.greco.officina.model.Shop;
import it.paleocapa.greco.officina.repository.KanbanRepository;
import it.paleocapa.greco.officina.repository.OrdineRepository;
import it.paleocapa.greco.officina.repository.ShopRepository;
import it.paleocapa.greco.officina.user_details.AdminDetails;
import it.paleocapa.greco.officina.user_details.ClienteDetails;
import it.paleocapa.greco.officina.user_details.DipendenteDetails;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class MainController {

    @Autowired private ShopRepository shopRepository;
    @Autowired private OrdineRepository ordineRepository;
    @Autowired private KanbanRepository kanbanRepository;
    org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(MainController.class);

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

    @RequestMapping(value = "/cliente_login", method = RequestMethod.GET)
    public String loginCliente(Model model) {
        return "cliente/login";
    }

    // admin pages

    @RequestMapping(value="/admin/home", method=RequestMethod.GET)
    public String homeAdmin(Model model) {
        AdminDetails adminDetails = (AdminDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("principal", adminDetails);
        model.addAttribute("shops", shopRepository.findById_admin(adminDetails.getAdmin().getId_admin()));
        return "admin/home";
    }

    @RequestMapping(value="/admin/officina", method=RequestMethod.GET)
    public String infoOfficina(@RequestParam("id") String itemid, Model model) {
        model.addAttribute("shop", getOfficina(itemid));
        return "admin/officina";
    }

    @RequestMapping(value="/admin/nuova_officina", method=RequestMethod.GET)
    public String nuovaOfficina(Model model) {
        Long id_shop = newOfficina(((AdminDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getAdmin());
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

    // cliente pages

    @RequestMapping(value="/cliente/home", method=RequestMethod.GET)
    public String homeCliente(Model model) {
        ClienteDetails clienteDetails = (ClienteDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("principal", clienteDetails);
        return "cliente/home";
    }

    // dipendente pages
    
    // ...

    @RequestMapping(value="/dipendente/home", method=RequestMethod.GET)
    public RedirectView homeDipendente(Model model, RedirectAttributes attributes) {
        return new RedirectView("/dipendente/kanban?pos_kanban=0");
    }

    @RequestMapping(value="/dipendente/kanban", method=RequestMethod.GET)
    public String vistaKanban(@RequestParam("pos_kanban") String pos_kanban, Model model) {
        DipendenteDetails dipendenteDetails = (DipendenteDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model = putPrincipalIfNull(model);
        model.addAttribute("kanbans", getKanbans(dipendenteDetails.getUser().getShop().getId_shop()+""));
        model.addAttribute("ordini", getOrdini(dipendenteDetails.getUser().getShop().getId_shop()+"", pos_kanban));
        return "dipendente/home";
    }

    private Model putPrincipalIfNull(Model model) {
        if (model.getAttribute("principal") == null) {
            DipendenteDetails dipendenteDetails = (DipendenteDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            model.addAttribute("principal", dipendenteDetails);
        }
        return model;
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
    public Long newOfficina(Admin admin) {
        Shop s = new Shop("New shop", admin);
        shopRepository.save(s);
        return s.getId_shop();
    }

    @RequestMapping(value="/api/officina/update_officina", method=RequestMethod.POST)
    public void updateOfficina(@ModelAttribute Shop shop) {
        shopRepository.save(shop);
    }

    @RequestMapping(value="/api/officina/delete_officina", method=RequestMethod.GET)
    public void deleteOfficina(@RequestParam("shop_id") String shop_id) {
        shopRepository.deleteById(Integer.parseInt(shop_id));
    }

    @RequestMapping(value="/api/officina/get_kanbans", method=RequestMethod.GET)
    public List<Kanban> getKanbans(@RequestParam("shop_id") String shop_id) {
        return kanbanRepository.findById_shop(Long.parseLong(shop_id));
    }

    @RequestMapping(value="/api/officina/get_ordini_kanban", method=RequestMethod.GET)
    public List<Ordine> getOrdini(@RequestParam("shop_id") String shop_id, @RequestParam("pos_kanban") String pos_kanban) {
        Optional<Kanban> kanban = kanbanRepository.findById_shopAndPosizione(Long.parseLong(shop_id), Integer.valueOf(pos_kanban));
        if (kanban.isPresent())
            return ordineRepository.findById_kanban(kanban.get().getId_kanban());
        return null;
    }

}
