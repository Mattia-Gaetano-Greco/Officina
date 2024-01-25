package it.paleocapa.greco.officina.model;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Ispezione")
public class Ispezione implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_ispezione;

    private String nome;

    private int id_ordine;

    @ManyToOne
    @JoinColumn(name = "id_ordine", referencedColumnName = "id_ordine", insertable = false, updatable = false)
    private Ordine ordine;

    public Ispezione() {}

    public int getId_ispezione() {
        return id_ispezione;
    }

    public void setId_ispezione(int id_ispezione) {
        this.id_ispezione = id_ispezione;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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
