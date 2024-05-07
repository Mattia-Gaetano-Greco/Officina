package it.paleocapa.greco.officina.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import it.paleocapa.greco.officina.model.Ordine;
import it.paleocapa.greco.officina.user_details.DipendenteDetails;
import it.paleocapa.greco.officina.util.controllers_common_code.implementations.KanbanInterfaceImplementation;
import it.paleocapa.greco.officina.util.controllers_common_code.implementations.UtenteInterfaceImplementation;
import it.paleocapa.greco.officina.util.controllers_common_code.interfaces.KanbanInterface;
import it.paleocapa.greco.officina.util.controllers_common_code.interfaces.UtenteInterface;

@Controller
@RequestMapping("/dipendente")
public class DipendenteController implements KanbanInterface, UtenteInterface<DipendenteDetails> {

    @RequestMapping(value="/home", method=RequestMethod.GET)
    public RedirectView home(Model model, RedirectAttributes attributes) {
        return KanbanInterfaceImplementation.home(model, attributes, DipendenteDetails.class);
    }

    @RequestMapping(value="/kanban", method=RequestMethod.GET)
    public String vistaKanbanGet(@RequestParam("pos_kanban") String pos_kanban, Model model) {
        return KanbanInterfaceImplementation.vistaKanbanGet(pos_kanban, model, DipendenteDetails.class);
    }

    @RequestMapping(value="/aggiungi_ordine", method=RequestMethod.GET)
    public String aggiungiOrdineGet(Model model) {
        return KanbanInterfaceImplementation.aggiungiOrdineGet(model, DipendenteDetails.class);
    }

    @RequestMapping(value="/aggiungi_ordine", method=RequestMethod.POST)
    public RedirectView aggiungiOrdinePost(Model model, @ModelAttribute("ordine") Ordine ordine) {
        return KanbanInterfaceImplementation.aggiungiOrdinePost(model, ordine, DipendenteDetails.class);
    }

    @RequestMapping(value="/modifica_ordine", method=RequestMethod.GET)
    public String modificaOrdineGet(Model model, @RequestParam("id_ordine") String id_ordine) {
        return KanbanInterfaceImplementation.modificaOrdineGet(model, id_ordine, DipendenteDetails.class);
    }

    @RequestMapping(value="/elimina_ordine", method=RequestMethod.POST)
    public RedirectView eliminaOrdine(Model model, @RequestParam("id_ordine") String id_ordine) {
        return KanbanInterfaceImplementation.eliminaOrdine(model, id_ordine, DipendenteDetails.class);
    }

    @RequestMapping(value="/aggiorna_dati", method=RequestMethod.POST)
    public RedirectView aggiornaDatiPost(Model model, DipendenteDetails user) {
        return UtenteInterfaceImplementation.aggiornaDatiPost(model, user);
    }

}