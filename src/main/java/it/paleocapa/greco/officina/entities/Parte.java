package it.paleocapa.greco.officina.entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "Parte")
public class Parte implements Serializable {

    @Id
    private String id_parte;

    private String nome;

    @Enumerated(EnumType.STRING)
    private StatoParte stato;

    private String descrizione;

    private float costo_acquisto;

    private int id_shop;

    private Integer id_servizio;

    @ManyToOne
    @JoinColumn(name = "id_shop", referencedColumnName = "id_shop", insertable = false, updatable = false)
    private Shop shop;

    @ManyToOne
    @JoinColumn(name = "id_servizio", referencedColumnName = "id_servizio", insertable = false, updatable = false)
    private Servizio servizio;

    public Parte() {}

    public String getId_parte() {
        return id_parte;
    }

    public void setId_parte(String id_parte) {
        this.id_parte = id_parte;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public StatoParte getStato() {
        return stato;
    }

    public void setStato(StatoParte stato) {
        this.stato = stato;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public float getCosto_acquisto() {
        return costo_acquisto;
    }

    public void setCosto_acquisto(float costo_acquisto) {
        this.costo_acquisto = costo_acquisto;
    }

    public int getId_shop() {
        return id_shop;
    }

    public void setId_shop(int id_shop) {
        this.id_shop = id_shop;
    }

    public Integer getId_servizio() {
        return id_servizio;
    }

    public void setId_servizio(Integer id_servizio) {
        this.id_servizio = id_servizio;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public Servizio getServizio() {
        return servizio;
    }

    public void setServizio(Servizio servizio) {
        this.servizio = servizio;
    }
    
}

