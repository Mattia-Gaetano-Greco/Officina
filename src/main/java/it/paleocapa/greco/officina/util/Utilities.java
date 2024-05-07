package it.paleocapa.greco.officina.util;

import java.util.HashMap;

import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;

import it.paleocapa.greco.officina.model.Admin;
import it.paleocapa.greco.officina.model.Cliente;
import it.paleocapa.greco.officina.model.Dipendente;
import it.paleocapa.greco.officina.model.Ordine;
import it.paleocapa.greco.officina.model.Veicolo;
import it.paleocapa.greco.officina.user_details.AdminDetails;
import it.paleocapa.greco.officina.user_details.ClienteDetails;
import it.paleocapa.greco.officina.user_details.DipendenteDetails;

public class Utilities {

    public static <T> T getFromAPI(String uri, Class<T> object_class) {
        uri = Endpoints.toAbsolutePathRequest(uri);
        return (T) new RestTemplate().getForObject(uri+"", object_class);
    }

    public static <T> T postToAPI(String uri, Object object, Class<T> object_class) {
        uri = Endpoints.toAbsolutePathRequest(uri);
        return new RestTemplate().postForObject(uri+"", object, object_class);
    }

    @SuppressWarnings("unchecked")
    public static <T extends UserDetails> T getAuthenticatedUser(Class <T> userClass) {
        return (T) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public static <T extends UserDetails> Model putPrincipal(Model model, Class <T> userClass) {
        if (model.getAttribute("principal") == null && 
            (userClass == DipendenteDetails.class) || (userClass == AdminDetails.class) || (userClass == ClienteDetails.class))
            model.addAttribute("principal", getAuthenticatedUser(userClass));
        return model;
    }

    public static <ST extends UserDetails> void updatePrincipal(ST userDetails) {
        if (userDetails.getClass() == DipendenteDetails.class) {
            Dipendente updatedUser = ((DipendenteDetails)userDetails).getUser();
            Utilities.postToAPI("/api/dipendente/update", updatedUser, Void.class);
            LoggerFactory.getLogger(Utilities.class).info(updatedUser.toString());
            DipendenteDetails principal = (DipendenteDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            principal.setUser(updatedUser);
        } else if (userDetails.getClass() == AdminDetails.class) {
            Admin updatedUser = ((AdminDetails)userDetails).getUser();
            Utilities.postToAPI("/api/admin/update", updatedUser, Void.class);
            LoggerFactory.getLogger(Utilities.class).info(updatedUser.toString());
            AdminDetails principal = (AdminDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            principal.setUser(updatedUser);
        } else if (userDetails.getClass() == ClienteDetails.class) {
            Cliente updatedUser = ((ClienteDetails)userDetails).getUser();
            Utilities.postToAPI("/api/cliente/update", updatedUser, Void.class);
            LoggerFactory.getLogger(Utilities.class).info(updatedUser.toString());
            ClienteDetails principal = (ClienteDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            principal.setUser(updatedUser);
        }
    }

    public static Model putOrdineInputFields(Model model, Long id_shop) {
        HashMap<String, KeyIDPair[]> dropdowns = new HashMap<String, KeyIDPair[]>();
        String id_kanbansURI = "/api/officina/get_kanbans_keyidpairs?shop_id="+id_shop;
        String targheURI = "/api/get_targhe_keyidpairs";
        KeyIDPair[] id_kanbans = Utilities.getFromAPI(id_kanbansURI, KeyIDPair[].class);
        KeyIDPair[] targhe = Utilities.getFromAPI(targheURI, KeyIDPair[].class);
        dropdowns.put("autorizzato", new KeyIDPair[]{new KeyIDPair("No", 0), new KeyIDPair("Si", 1)});
        dropdowns.put("targa", targhe);
        dropdowns.put("kanban", id_kanbans);
        model.addAttribute("dropdowns", dropdowns);
        model.addAttribute("input_fields", OrdineFields.input_fields);
        model.addAttribute("ordine", new Ordine());
        return model;
    }

    public static Ordine formatInputtedOrder(Ordine ordine) {
        Veicolo veicolo = Utilities.getFromAPI("/api/get_veicolo_from_targa?targa="+ordine.getTarga(), Veicolo.class);
        if (ordine.getTitolo() == null)
            ordine.setTitolo("Ordine");
        ordine.setPagamento_effettuato(false);
        ordine.setVeicolo(veicolo);
        ordine.setNum_telaio(ordine.getVeicolo().getNum_telaio());
        ordine.setId_cliente(ordine.getVeicolo().getCliente().getId_cliente());
        ordine.setId_kanban(ordine.getKanban().getId_kanban());
        return ordine;
    }

    public static <T extends UserDetails> String getUserRoleFromUserDetails(Class<T> userDetails) {
        if (userDetails == DipendenteDetails.class)
            return "dipendente";
        else if (userDetails == AdminDetails.class)
            return "admin";
        else if (userDetails == ClienteDetails.class)
            return "cliente";
        return null;
    }

    public static <T extends UserDetails> void addUserRoleToModel(Model model, Class<T> userDetails) {
        if (userDetails == DipendenteDetails.class)
            model.addAttribute("user_role", "dipendente");
        else if (userDetails == AdminDetails.class)
            model.addAttribute("user_role", "admin");
        else if (userDetails == ClienteDetails.class)
            model.addAttribute("user_role", "cliente");
        
    }
    
}
