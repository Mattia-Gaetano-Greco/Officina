package it.paleocapa.greco.officina.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.view.RedirectView;

import it.paleocapa.greco.officina.model.Shop;
import it.paleocapa.greco.officina.user_details.AdminDetails;
import it.paleocapa.greco.officina.utilities.Endpoints;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @RequestMapping(value="/home", method=RequestMethod.GET)
    public String homeAdmin(Model model) {
        AdminDetails adminDetails = AdminUtils.getAuthenticatedAdminDetails();
        String getShopsURI = "/api/officina/find_by_admin?id="+adminDetails.getAdmin().getId_admin();
        Shop[] shops = AdminUtils.getFromAPI(getShopsURI, Shop[].class);
        model.addAttribute("principal", adminDetails);
        model.addAttribute("shops", shops);
        return "admin/home";
    }

    @RequestMapping(value="/officina", method=RequestMethod.GET)
    public String infoOfficina(@RequestParam("id") String officinaid, Model model) {
        String getShopURI = "/api/officina/get_officina?id="+officinaid;
        Shop shop = AdminUtils.getFromAPI(getShopURI, Shop.class);
        model.addAttribute("shop", shop);
        return "admin/officina";
    }

    @RequestMapping(value="/nuova_officina", method=RequestMethod.GET)
    public String nuovaOfficina(Model model) {
        String newShopURI = "/api/officina/new_officina";
        AdminDetails adminDetails = AdminUtils.getAuthenticatedAdminDetails();
        Long id_shop = AdminUtils.postToAPI(newShopURI, adminDetails.getAdmin(), Long.class);
        String getShopURI = "/api/officina/get_officina?id="+id_shop;
        Shop shop = AdminUtils.getFromAPI(getShopURI, Shop.class);
        model.addAttribute("shop", shop);
        return "admin/modifica_officina";
    }
    
    @RequestMapping(value="/elimina_officina", method=RequestMethod.GET)
    public RedirectView eliminaOfficina(@RequestParam("id") String itemid, Model model) {
        String deleteShopURI = "/api/officina/delete_officina?id="+itemid;
        AdminUtils.postToAPI(deleteShopURI, null, Void.class);
        return new RedirectView("/admin/home");
    }
    
    @RequestMapping(value="/modifica_officina", method=RequestMethod.GET)
    public String modificaOfficina(@RequestParam("id") String officinaid, Model model) {
        String getShopURI = "/api/officina/get_officina?id="+officinaid;
        Shop shop = AdminUtils.getFromAPI(getShopURI, Shop.class);
        model.addAttribute("shop", shop);
        return "admin/modifica_officina";
    }

    @RequestMapping(value="/modifica_officina", method=RequestMethod.POST)
    public RedirectView modificaOfficinaPost(@ModelAttribute Shop shop, Model model) {
        String updateOfficinaURI = "/api/officina/update_officina";
        AdminUtils.postToAPI(updateOfficinaURI, shop, Void.class);
        return new RedirectView("/admin/home");
    }
}

class AdminUtils {

    static AdminDetails getAuthenticatedAdminDetails() {
        return (AdminDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
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