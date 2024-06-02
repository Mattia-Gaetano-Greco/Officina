package it.paleocapa.greco.officina.controller;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.LinkedList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

import it.paleocapa.greco.officina.model.Cliente;
import it.paleocapa.greco.officina.model.Ordine;
import it.paleocapa.greco.officina.model.Veicolo;
import it.paleocapa.greco.officina.user_details.ClienteDetails;
import it.paleocapa.greco.officina.util.Utilities;
import it.paleocapa.greco.officina.util.controllers_common_code.implementations.UtenteInterfaceImplementation;
import it.paleocapa.greco.officina.util.controllers_common_code.interfaces.UtenteInterface;

@Controller
@RequestMapping("/cliente")
public class ClienteController implements UtenteInterface<ClienteDetails> {

    @RequestMapping(value="/home", method=RequestMethod.GET)
    public String homeCliente(Model model) {
        Cliente user = Utilities.getAuthenticatedUser(ClienteDetails.class).getUser();
        Long id_cliente = user.getId_cliente();
        LocalDate today = LocalDate.now();
        String get_veicoli = "/api/veicolo/find_by_id_cliente?id_cliente="+id_cliente;
        Veicolo[] veicoli = Utilities.getFromAPI(get_veicoli, Veicolo[].class);
        LinkedList<Ordine> ordini = new LinkedList<Ordine>();
        for (Veicolo v : veicoli) {
            String get_ordini = "/api/ordine/find_by_targa?targa="+v.getTarga();
            Ordine[] ordini_veicolo = Utilities.getFromAPI(get_ordini, Ordine[].class);
            ordini.addAll(Arrays.asList(ordini_veicolo));
        }
        Ordine[] ordini_in_corso = ordini.stream().filter(o -> {
            if (o.getData_scadenza() == null)
                return true;
            else
                return o.getData_scadenza().isAfter(today);
        }).toArray(Ordine[]::new);
        Ordine[] ordini_completati = ordini.stream().filter(o -> {
            if (o.getData_scadenza() != null)
                return o.getData_scadenza().isBefore(today) | o.getData_scadenza().isEqual(today);
            else
                return false;
        }).toArray(Ordine[]::new);;
        /*LoggerFactory.getLogger(ClienteController.class).info("Ordini in corso: "+ordini_in_corso.length);
        LoggerFactory.getLogger(ClienteController.class).info("Ordini completati: "+ordini_completati.length);*/
        Utilities.putPrincipal(model, ClienteDetails.class);
        model.addAttribute("veicoli", veicoli);
        model.addAttribute("ordini_in_corso", ordini_in_corso);
        model.addAttribute("ordini_completati", ordini_completati);
        return "cliente/home";
    }

    @RequestMapping(value="/aggiorna_dati", method=RequestMethod.POST)
    public RedirectView aggiornaDatiPost(Model model, ClienteDetails user) {
        return UtenteInterfaceImplementation.aggiornaDatiPost(model, user);
    }

}