package it.paleocapa.greco.officina.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "NotaOrdine")
public class NotaOrdine implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_nota_ordine;

    private String testo;

    private int id_ordine;

    @ManyToOne
    @JoinColumn(name = "id_ordine", referencedColumnName = "id_ordine", insertable = false, updatable = false)
    private Ordine ordine;

    public NotaOrdine() {}

    public int getId_nota_ordine() {
        return id_nota_ordine;
    }

    public void setId_nota_ordine(int id_nota_ordine) {
        this.id_nota_ordine = id_nota_ordine;
    }

    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }

    public int getId_ordine() {
        return id_ordine;
    }

    public void setId_ordine(int id_ordine) {
        this.id_ordine = id_ordine;
    }

    public Ordine getOrdine() {
        return ordine;
    }

    public void setOrdine(Ordine ordine) {
        this.ordine = ordine;
    }

}

