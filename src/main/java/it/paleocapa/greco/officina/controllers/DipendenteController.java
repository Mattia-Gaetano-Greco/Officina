package it.paleocapa.greco.officina.controllers;

import java.util.*;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import it.paleocapa.greco.officina.model.Kanban;
import it.paleocapa.greco.officina.model.Ordine;
import it.paleocapa.greco.officina.model.Veicolo;
import it.paleocapa.greco.officina.user_details.DipendenteDetails;
import it.paleocapa.greco.officina.utilities.Endpoints;
import it.paleocapa.greco.officina.utilities.KeyIDPair;
import it.paleocapa.greco.officina.utilities.OrdineFields;

@Controller
@RequestMapping("/dipendente")
public class DipendenteController {

    @RequestMapping(value="/home", method=RequestMethod.GET)
    public RedirectView homeDipendente(Model model, RedirectAttributes attributes) {
        return new RedirectView("/dipendente/kanban?pos_kanban=0");
    }

    @RequestMapping(value="/kanban", method=RequestMethod.GET)
    public String vistaKanban(@RequestParam("pos_kanban") String pos_kanban, Model model) {
        DipendenteDetails dipendenteDetails = DipendenteUtils.getAuthenticatedDipendente();
        String getKanbansFromShopURI = "/api/officina/get_kanbans?shop_id="+dipendenteDetails.getUser().getShop().getId_shop();
        String getOrdersFromShopURI = "/api/officina/get_ordini_kanban?shop_id="+dipendenteDetails.getUser().getShop().getId_shop()+"&pos_kanban="+pos_kanban;
        Kanban[] kanbans = DipendenteUtils.getFromAPI(getKanbansFromShopURI, Kanban[].class);
        Ordine[] ordini = DipendenteUtils.getFromAPI(getOrdersFromShopURI, Ordine[].class);
        model = DipendenteUtils.putPrincipal(model);
        model.addAttribute("kanbans", kanbans);
        model.addAttribute("ordini", ordini);
        model.addAttribute("show_fields", OrdineFields.show_fields);
        return "dipendente/home";
    }

    @RequestMapping(value="/aggiungi_ordine", method=RequestMethod.GET)
    public String aggiungiOrdineGet(Model model) {
        DipendenteDetails dipendenteDetails = DipendenteUtils.getAuthenticatedDipendente();
        String getKanbansFromShopURI = "/api/officina/get_kanbans?shop_id="+dipendenteDetails.getUser().getShop().getId_shop();
        Kanban[] kanbans = DipendenteUtils.getFromAPI(getKanbansFromShopURI, Kanban[].class);
        model = DipendenteUtils.putPrincipal(model);
        model = DipendenteUtils.putOrdineInputFields(model);
        model.addAttribute("kanbans", kanbans);
        return "dipendente/aggiungi_ordine";
    }

    @RequestMapping(value="/aggiungi_ordine", method=RequestMethod.POST)
    public RedirectView aggiungiOrdinePost(Model model, @ModelAttribute("ordine") Ordine ordine) {
        DipendenteDetails dipendenteDetails = DipendenteUtils.getAuthenticatedDipendente();        
        ordine = DipendenteUtils.prepareOrderFromInput(ordine, dipendenteDetails);
        if (ordine == null)
            return new RedirectView("/dipendente/aggiungi_ordine");
        DipendenteUtils.postToAPI("/api/officina/aggiungi_ordine", ordine, Ordine.class);
        return new RedirectView("/dipendente/kanban?pos_kanban="+ordine.getKanban().getPosizione());
    }

}

class DipendenteUtils {
    
    static Model putPrincipal(Model model) {
        if (model.getAttribute("principal") == null)
            model.addAttribute("principal", getAuthenticatedDipendente());
        return model;
    }

    static Model putOrdineInputFields(Model model) {
        HashMap<String, KeyIDPair[]> dropdowns = new HashMap<String, KeyIDPair[]>();
        String id_kanbansURI = "/api/officina/get_kanbans_keyidpairs?shop_id="+getAuthenticatedDipendente().getUser().getShop().getId_shop();
        String targheURI = "/api/get_targhe_keyidpairs";
        KeyIDPair[] id_kanbans = DipendenteUtils.getFromAPI(id_kanbansURI, KeyIDPair[].class);
        KeyIDPair[] targhe = DipendenteUtils.getFromAPI(targheURI, KeyIDPair[].class);
        dropdowns.put("autorizzato", new KeyIDPair[]{new KeyIDPair("No", 0), new KeyIDPair("Si", 1)});
        dropdowns.put("targa", targhe);
        dropdowns.put("kanban", id_kanbans);
        model.addAttribute("dropdowns", dropdowns);
        model.addAttribute("fields", OrdineFields.input_fields);
        model.addAttribute("ordine", new Ordine());
        return model;
    }

    static Ordine prepareOrderFromInput(Ordine ordine, DipendenteDetails dipendenteDetails) {
        Veicolo veicolo = DipendenteUtils.getFromAPI("/api/get_veicolo_from_targa?targa="+ordine.getTarga(), Veicolo.class);
        if (ordine.getTitolo() == null)
            ordine.setTitolo("Ordine");
        ordine.setPagamento_effettuato(false);
        ordine.setVeicolo(veicolo);
        ordine.setNum_telaio(ordine.getVeicolo().getNum_telaio());
        ordine.setId_cliente(ordine.getVeicolo().getCliente().getId_cliente());
        ordine.setId_dipendente(dipendenteDetails.getUser().getId_dipendente());
        ordine.setId_kanban(ordine.getKanban().getId_kanban());
        return ordine;
    }

    static DipendenteDetails getAuthenticatedDipendente() {
        return (DipendenteDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @SuppressWarnings("null")
    static <T> T getFromAPI(String uri, Class<T> object_class) {
        uri = Endpoints.toAbsolutePathRequest(uri);
        return (T) new RestTemplate().getForObject(uri+"", object_class);
    }

    @SuppressWarnings("null")
    static <T> T postToAPI(String uri, Object object, Class<T> object_class) {
        uri = Endpoints.toAbsolutePathRequest(uri);
        return new RestTemplate().postForObject(uri+"", object, object_class);
    }
    
}