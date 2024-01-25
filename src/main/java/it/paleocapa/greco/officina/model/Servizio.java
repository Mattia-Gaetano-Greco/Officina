package it.paleocapa.greco.officina.model;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Servizio")
public class Servizio implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_servizio;

    private String nome;

    @Enumerated(EnumType.STRING)
    private StatusServizio status;

    private float ore_stimate;

    private float ore_impiegate;

    private boolean completato;

    private int id_ordine;

    @ManyToOne
    @JoinColumn(name = "id_ordine", referencedColumnName = "id_ordine", insertable = false, updatable = false)
    private Ordine ordine;

    public Servizio() {}

    public int getId_servizio() {
        return id_servizio;
    }

    public void setId_servizio(int id_servizio) {
        this.id_servizio = id_servizio;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public StatusServizio getStatus() {
        return status;
    }

    public void setStatus(StatusServizio status) {
        this.status = status;
    }

    public float getOre_stimate() {
        return ore_stimate;
    }

    public void setOre_stimate(float ore_stimate) {
        this.ore_stimate = ore_stimate;
    }

    public float getOre_impiegate() {
        return ore_impiegate;
    }

    public void setOre_impiegate(float ore_impiegate) {
        this.ore_impiegate = ore_impiegate;
    }

    public boolean isCompletato() {
        return completato;
    }

    public void setCompletato(boolean completato) {
        this.completato = completato;
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

