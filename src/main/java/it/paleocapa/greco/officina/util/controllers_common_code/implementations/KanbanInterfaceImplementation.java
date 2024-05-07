package it.paleocapa.greco.officina.util.controllers_common_code.implementations;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import it.paleocapa.greco.officina.model.Kanban;
import it.paleocapa.greco.officina.model.Ordine;
import it.paleocapa.greco.officina.user_details.AdminDetails;
import it.paleocapa.greco.officina.user_details.DipendenteDetails;
import it.paleocapa.greco.officina.util.OrdineFields;
import it.paleocapa.greco.officina.util.Utilities;

public class KanbanInterfaceImplementation {

    public static <T extends UserDetails> RedirectView home(Model model, RedirectAttributes attributes, Class<T> userDetails) {
        if (userDetails == DipendenteDetails.class)
            return new RedirectView("/dipendente/kanban?pos_kanban=0");
        else if (userDetails == AdminDetails.class)
            return new RedirectView("/admin/kanban?pos_kanban=0");
        return null;
    }

    public static <T extends UserDetails> String vistaKanbanGet(@RequestParam("pos_kanban") String pos_kanban, Model model, Class<T> userDetails) {
        int id_shop = getShopIdFromUserDetails(userDetails);
        String getKanbansFromShopURI = "/api/officina/get_kanbans?shop_id="+id_shop;
        String getOrdersFromShopURI = "/api/officina/get_ordini_kanban?shop_id="+id_shop+"&pos_kanban="+pos_kanban;
        Kanban[] kanbans = Utilities.getFromAPI(getKanbansFromShopURI, Kanban[].class);
        Ordine[] ordini = Utilities.getFromAPI(getOrdersFromShopURI, Ordine[].class);
        model = Utilities.putPrincipal(model, DipendenteDetails.class);
        model.addAttribute("kanbans", kanbans);
        model.addAttribute("ordini", ordini);
        model.addAttribute("show_fields", OrdineFields.show_fields);
        Utilities.addUserRoleToModel(model, userDetails);
        return "kanban_user/home";
    }

    public static <T extends UserDetails> String aggiungiOrdineGet(Model model, Class<T> userDetails) {
        int id_shop = getShopIdFromUserDetails(userDetails);
        String getKanbansFromShopURI = "/api/officina/get_kanbans?shop_id="+id_shop;
        Kanban[] kanbans = Utilities.getFromAPI(getKanbansFromShopURI, Kanban[].class);
        model = Utilities.putPrincipal(model, userDetails);
        model = Utilities.putOrdineInputFields(model, Long.valueOf(id_shop));
        model.addAttribute("kanbans", kanbans);
        Utilities.addUserRoleToModel(model, userDetails);
        return "kanban_user/aggiungi_ordine";
    }

    public static <T extends UserDetails> RedirectView aggiungiOrdinePost(Model model, @ModelAttribute("ordine") Ordine ordine, Class<T> userDetails) {
        String userRole = Utilities.getUserRoleFromUserDetails(userDetails);
        ordine = Utilities.formatInputtedOrder(ordine);
        if (ordine == null)
            return new RedirectView("/"+userRole+"/aggiungi_ordine");
        Utilities.postToAPI("/api/ordine/create", ordine, Ordine.class);
        return new RedirectView("/"+userRole+"/kanban?pos_kanban="+ordine.getKanban().getPosizione());
    }

    public static <T extends UserDetails> String modificaOrdineGet(Model model, @RequestParam("id_ordine") String id_ordine, Class<T> userDetails) {
        int id_shop = getShopIdFromUserDetails(userDetails);
        Ordine ordine = Utilities.getFromAPI("/api/ordine/get?id="+id_ordine, Ordine.class);
        model = Utilities.putPrincipal(model, userDetails);
        model = Utilities.putOrdineInputFields(model, Long.valueOf(id_shop));
        model.addAttribute("ordine", ordine);
        model.addAttribute("is_modify", true);
        Utilities.addUserRoleToModel(model, userDetails);
        return "kanban_user/aggiungi_ordine";
    }

    public static <T extends UserDetails> RedirectView eliminaOrdine(Model model, @RequestParam("id_ordine") String id_ordine, Class<T> userDetails) {
        String userRole = Utilities.getUserRoleFromUserDetails(userDetails);
        Utilities.postToAPI("/api/ordine/delete?id="+id_ordine, null, Void.class);
        return new RedirectView("/"+userRole+"/home");
    }

    private static <T extends UserDetails> int getShopIdFromUserDetails(Class<T> userDetails) {
        if (userDetails == DipendenteDetails.class) {
            return (Utilities.getAuthenticatedUser(DipendenteDetails.class).getUser().getShop().getId_shop()).intValue();
        } else if (userDetails == AdminDetails.class) {
            return 1; // TODO: retrieve shop id from session
        }
        return -1;
    }

}
