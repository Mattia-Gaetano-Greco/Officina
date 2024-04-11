package it.paleocapa.greco.officina.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import it.paleocapa.greco.officina.model.Kanban;
import it.paleocapa.greco.officina.model.Ordine;
import it.paleocapa.greco.officina.user_details.DipendenteDetails;
import it.paleocapa.greco.officina.utilities.OrdineFields;
import it.paleocapa.greco.officina.utilities.Utilities;

@Controller
@RequestMapping("/dipendente")
public class DipendenteController {

    @RequestMapping(value="/home", method=RequestMethod.GET)
    public RedirectView homeDipendente(Model model, RedirectAttributes attributes) {
        return new RedirectView("/dipendente/kanban?pos_kanban=0");
    }

    @RequestMapping(value="/kanban", method=RequestMethod.GET)
    public String vistaKanban(@RequestParam("pos_kanban") String pos_kanban, Model model) {
        int id_shop = (Utilities.getAuthenticatedUser(DipendenteDetails.class).getUser().getShop().getId_shop()).intValue();
        String getKanbansFromShopURI = "/api/officina/get_kanbans?shop_id="+id_shop;
        String getOrdersFromShopURI = "/api/officina/get_ordini_kanban?shop_id="+id_shop+"&pos_kanban="+pos_kanban;
        Kanban[] kanbans = Utilities.getFromAPI(getKanbansFromShopURI, Kanban[].class);
        Ordine[] ordini = Utilities.getFromAPI(getOrdersFromShopURI, Ordine[].class);
        model = Utilities.putPrincipal(model, DipendenteDetails.class);
        model.addAttribute("kanbans", kanbans);
        model.addAttribute("ordini", ordini);
        model.addAttribute("show_fields", OrdineFields.show_fields);
        return "dipendente/home";
    }

    @RequestMapping(value="/aggiungi_ordine", method=RequestMethod.GET)
    public String aggiungiOrdineGet(Model model) {
        int id_shop = (Utilities.getAuthenticatedUser(DipendenteDetails.class).getUser().getShop().getId_shop()).intValue();
        //DipendenteDetails dipendenteDetails = ;
        String getKanbansFromShopURI = "/api/officina/get_kanbans?shop_id="+id_shop;
        Kanban[] kanbans = Utilities.getFromAPI(getKanbansFromShopURI, Kanban[].class);
        model = Utilities.putPrincipal(model, DipendenteDetails.class);
        model = Utilities.putOrdineInputFields(model, Utilities.getAuthenticatedUser(DipendenteDetails.class).getUser().getShop().getId_shop());
        model.addAttribute("kanbans", kanbans);
        return "dipendente/aggiungi_ordine";
    }

    @RequestMapping(value="/aggiungi_ordine", method=RequestMethod.POST)
    public RedirectView aggiungiOrdinePost(Model model, @ModelAttribute("ordine") Ordine ordine) {
        ordine = Utilities.formatInputtedOrder(ordine);
        if (ordine == null)
            return new RedirectView("/dipendente/aggiungi_ordine");
        Utilities.postToAPI("/api/ordine/create", ordine, Ordine.class);
        return new RedirectView("/dipendente/kanban?pos_kanban="+ordine.getKanban().getPosizione());
    }

    @RequestMapping(value="/modifica_ordine", method=RequestMethod.GET)
    public String modificaOrdineGet(Model model, @RequestParam("id_ordine") String id_ordine) {
        Ordine ordine = Utilities.getFromAPI("/api/ordine/get?id="+id_ordine, Ordine.class);
        model = Utilities.putPrincipal(model, DipendenteDetails.class);
        model = Utilities.putOrdineInputFields(model, Utilities.getAuthenticatedUser(DipendenteDetails.class).getUser().getShop().getId_shop());
        model.addAttribute("ordine", ordine);
        model.addAttribute("is_modify", true);
        return "dipendente/aggiungi_ordine";
    }

    @RequestMapping(value="/elimina_ordine", method=RequestMethod.POST)
    public RedirectView eliminaOrdine(Model model, @RequestParam("id_ordine") String id_ordine) {
        Utilities.postToAPI("/api/ordine/delete?id="+id_ordine, null, Void.class);
        return new RedirectView("/dipendente/home");
    }

    @RequestMapping(value="/aggiorna_dati", method=RequestMethod.POST)
    public RedirectView aggiornaDati(Model model, @ModelAttribute("principal") DipendenteDetails dipendente) {
        Utilities.updatePrincipal(dipendente);
        return new RedirectView("/dipendente/home");
    }

}