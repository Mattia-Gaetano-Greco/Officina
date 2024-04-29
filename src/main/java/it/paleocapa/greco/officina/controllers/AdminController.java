package it.paleocapa.greco.officina.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import it.paleocapa.greco.officina.controllers_shared_interfaces.implementations.KanbanInterfaceImplementation;
import it.paleocapa.greco.officina.controllers_shared_interfaces.interfaces.KanbanInterface;
import it.paleocapa.greco.officina.model.Ordine;
import it.paleocapa.greco.officina.user_details.AdminDetails;
import it.paleocapa.greco.officina.utilities.Utilities;

@Controller
@RequestMapping("/admin")
public class AdminController implements KanbanInterface {

    @RequestMapping(value="/home", method=RequestMethod.GET)
    public RedirectView homeAdmin(Model model) {
        return new RedirectView("/admin/kanban?pos_kanban=0");
    }

    @RequestMapping(value="/kanban", method=RequestMethod.GET)
    public String vistaKanbanGet(@RequestParam("pos_kanban") String pos_kanban, Model model) {
        return KanbanInterfaceImplementation.vistaKanbanGet(pos_kanban, model, AdminDetails.class);
    }

    @RequestMapping(value="/aggiungi_ordine", method=RequestMethod.GET)
    public String aggiungiOrdineGet(Model model) {
        return KanbanInterfaceImplementation.aggiungiOrdineGet(model, AdminDetails.class);
    }

    @RequestMapping(value="/aggiungi_ordine", method=RequestMethod.POST)
    public RedirectView aggiungiOrdinePost(Model model, @ModelAttribute("ordine") Ordine ordine) {
        return KanbanInterfaceImplementation.aggiungiOrdinePost(model, ordine, AdminDetails.class);
    }

    @RequestMapping(value="/modifica_ordine", method=RequestMethod.GET)
    public String modificaOrdineGet(Model model, @RequestParam("id_ordine") String id_ordine) {
        return KanbanInterfaceImplementation.modificaOrdineGet(model, id_ordine, AdminDetails.class);
    }

    @RequestMapping(value="/elimina_ordine", method=RequestMethod.POST)
    public RedirectView eliminaOrdine(Model model, @RequestParam("id_ordine") String id_ordine) {
        return KanbanInterfaceImplementation.eliminaOrdine(model, id_ordine, AdminDetails.class);
    }

    @RequestMapping(value="visualizza_officine", method=RequestMethod.GET)
    public String viewShops(Model model) {
        return "admin/visualizza_officine";
    }

    /*@RequestMapping(value="/officina", method=RequestMethod.GET)
    public String infoOfficina(@RequestParam("id") String officinaid, Model model) {
        String getShopURI = "/api/officina/get?id="+officinaid;
        Shop shop = AdminUtils.getFromAPI(getShopURI, Shop.class);
        model.addAttribute("shop", shop);
        return "admin/officina";
    }

    @RequestMapping(value="/nuova_officina", method=RequestMethod.GET)
    public String nuovaOfficina(Model model) {
        String newShopURI = "/api/officina/create";
        AdminDetails adminDetails = AdminUtils.getAuthenticatedAdminDetails();
        Long id_shop = AdminUtils.postToAPI(newShopURI, adminDetails.getUser(), Long.class);
        String getShopURI = "/api/officina/get?id="+id_shop;
        Shop shop = AdminUtils.getFromAPI(getShopURI, Shop.class);
        model.addAttribute("shop", shop);
        return "admin/modifica_officina";
    }
    
    @RequestMapping(value="/elimina_officina", method=RequestMethod.GET)
    public RedirectView eliminaOfficina(@RequestParam("id") String itemid, Model model) {
        String deleteShopURI = "/api/officina/delete?id="+itemid;
        AdminUtils.postToAPI(deleteShopURI, null, Void.class);
        return new RedirectView("/admin/home");
    }
    
    @RequestMapping(value="/modifica_officina", method=RequestMethod.GET)
    public String modificaOfficina(@RequestParam("id") String officinaid, Model model) {
        String getShopURI = "/api/officina/get?id="+officinaid;
        Shop shop = AdminUtils.getFromAPI(getShopURI, Shop.class);
        model.addAttribute("shop", shop);
        return "admin/modifica_officina";
    }

    @RequestMapping(value="/modifica_officina", method=RequestMethod.POST)
    public RedirectView modificaOfficinaPost(@ModelAttribute Shop shop, Model model) {
        String updateOfficinaURI = "/api/officina/update";
        AdminUtils.postToAPI(updateOfficinaURI, shop, Void.class);
        return new RedirectView("/admin/home");
    }*/

    @RequestMapping(value="/aggiorna_dati", method=RequestMethod.POST)
    public RedirectView aggiornaDati(Model model, @ModelAttribute("principal") AdminDetails dipendente) {
        Utilities.updatePrincipal(dipendente);
        return new RedirectView("/dipendente/home");
    }

}

class AdminUtils {

    static AdminDetails getAuthenticatedAdminDetails() {
        return (AdminDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}