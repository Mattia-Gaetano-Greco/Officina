package it.paleocapa.greco.officina.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Set;

import java.io.Serializable;

@Entity
@Table(name = "TemplateIspezione")
public class TemplateIspezione implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_templ_ispezione;

    @ManyToMany
    Set<TemplateTask> templateTasks;

    private String nome;

    private int id_shop;

    @ManyToOne
    @JoinColumn(name = "id_shop", referencedColumnName = "id_shop", insertable = false, updatable = false)
    private Shop shop;

    public TemplateIspezione() {}

    public int getId_templ_ispezione() {
        return id_templ_ispezione;
    }

    public void setId_templ_ispezione(int id_templ_ispezione) {
        this.id_templ_ispezione = id_templ_ispezione;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId_shop() {
        return id_shop;
    }

    public void setId_shop(int id_shop) {
        this.id_shop = id_shop;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }
    
}
