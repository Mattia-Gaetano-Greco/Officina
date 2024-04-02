package it.paleocapa.greco.officina.controllers;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import it.paleocapa.greco.officina.model.Admin;
import it.paleocapa.greco.officina.model.Kanban;
import it.paleocapa.greco.officina.model.Ordine;
import it.paleocapa.greco.officina.model.Shop;
import it.paleocapa.greco.officina.model.Veicolo;
import it.paleocapa.greco.officina.repository.KanbanRepository;
import it.paleocapa.greco.officina.repository.OrdineRepository;
import it.paleocapa.greco.officina.repository.ShopRepository;
import it.paleocapa.greco.officina.repository.VeicoloRepository;
import it.paleocapa.greco.officina.utilities.KeyIDPair;

@Controller
@RequestMapping("/api")
public class APIController {

    @Autowired public ShopRepository shopRepository;
    @Autowired public OrdineRepository ordineRepository;
    @Autowired public KanbanRepository kanbanRepository;
    @Autowired public VeicoloRepository veicoloRepository;

    @RequestMapping(value="/get_targhe_keyidpairs", method=RequestMethod.GET)
    @ResponseBody
    public KeyIDPair[] getVeicoliKeyIDPairs() {
        Iterator<Veicolo> veicoli = veicoloRepository.findAll().iterator();
        List<KeyIDPair> targhe = new ArrayList<KeyIDPair>();
        while (veicoli.hasNext()) {
            Veicolo v = veicoli.next();
            targhe.add(new KeyIDPair(v.getTarga(), v.getTarga()));
        }
        return targhe.toArray(new KeyIDPair[targhe.size()]);
    }

    @RequestMapping(value="/get_veicolo_from_targa", method=RequestMethod.GET)
    @ResponseBody
    public Veicolo getVeicoloFromTarga(@RequestParam("targa") String targa) {
        Optional<Veicolo> veicolo = veicoloRepository.findByTarga(targa);
        if (veicolo.isPresent())
            return veicolo.get();
        return null;
    }

    @RequestMapping(value="/officina/find_by_admin", method=RequestMethod.GET)
    @ResponseBody
    public Shop[] findByAdmin(@RequestParam("id") String id) {
        List<Shop> shops = shopRepository.findById_admin(Long.parseLong(id));
        return shops.toArray(new Shop[shops.size()]);
    }
    
    @RequestMapping(value="/officina/aggiungi_ordine", method=RequestMethod.POST)
    @ResponseBody
    public Ordine aggiungiOrdine(@RequestBody Ordine ordine) {
        if (ordine == null)
            return null;
        return ordineRepository.save(ordine);
    }

    @RequestMapping(value="/officina/get_officina", method=RequestMethod.GET)
    @ResponseBody
    public Shop getOfficina(@RequestParam("id") String itemid) {
        Optional<Shop> shops = shopRepository.findById(Integer.parseInt(itemid));
        if (shops.isPresent())
            return shops.get();
        return null;
    }

    @RequestMapping(value="/officina/new_officina", method=RequestMethod.POST)
    @ResponseBody
    public Long newOfficina(@RequestBody Admin admin) {
        Shop s = new Shop("New shop", admin);
        shopRepository.save(s);
        return s.getId_shop();
    }

    @RequestMapping(value="/officina/update_officina", method=RequestMethod.POST)
    @ResponseBody
    public void updateOfficina(@RequestBody Shop shop) {
        if (shop == null)
            return;
        shopRepository.save(shop);
    }

    @RequestMapping(value="/officina/delete_officina", method=RequestMethod.POST)
    @ResponseBody
    public void deleteOfficina(@RequestParam("id") String shop_id) {
        shopRepository.deleteById(Integer.parseInt(shop_id));
    }

    @RequestMapping(value="/officina/get_kanbans", method=RequestMethod.GET)
    @ResponseBody
    public Kanban[] getKanbans(@RequestParam("shop_id") String shop_id) {
        List<Kanban> kanbans = kanbanRepository.findById_shop(Long.parseLong(shop_id));
        return kanbans.toArray(new Kanban[kanbans.size()]);
    }

    @RequestMapping(value="/officina/get_ordini_kanban", method=RequestMethod.GET)
    @ResponseBody
    public Ordine[] getOrdini(@RequestParam("shop_id") String shop_id, @RequestParam("pos_kanban") String pos_kanban) {
        Optional<Kanban> kanban = kanbanRepository.findById_shopAndPosizione(Long.parseLong(shop_id), Integer.valueOf(pos_kanban));
        if (!kanban.isPresent())
            return null;
        List<Ordine> ordini = ordineRepository.findById_kanban(kanban.get().getId_kanban());
        return ordini.toArray(new Ordine[ordini.size()]);
    }

    @RequestMapping(value="/officina/get_kanbans_keyidpairs", method=RequestMethod.GET)
    @ResponseBody
    public KeyIDPair[] getKanbansKeyIDPairs(@RequestParam("shop_id") String shop_id) {
        Iterator<Kanban> kanbans = kanbanRepository.findAll().iterator();
        List<KeyIDPair> id_kanbans = new ArrayList<KeyIDPair>();
        while (kanbans.hasNext()) {
            Kanban k = kanbans.next();
            id_kanbans.add(new KeyIDPair(k.getNome(), k.getId_kanban().intValue()));
        }
        return id_kanbans.toArray(new KeyIDPair[id_kanbans.size()]);
    }

}