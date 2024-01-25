package it.paleocapa.greco.officina.model;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Task")
public class Task implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_task;

    private String nome;

    @Enumerated(EnumType.STRING)
    private StatoPezzo stato_pezzo;

    private boolean completato;

    private int id_ispezione;

    @ManyToOne
    @JoinColumn(name = "id_ispezione", referencedColumnName = "id_ispezione", insertable = false, updatable = false)
    private Ispezione ispezione;

    public Task() {}

    public int getId_task() {
        return id_task;
    }

    public void setId_task(int id_task) {
        this.id_task = id_task;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public StatoPezzo getStato_pezzo() {
        return stato_pezzo;
    }

    public void setStato_pezzo(StatoPezzo stato_pezzo) {
        this.stato_pezzo = stato_pezzo;
    }

    public boolean isCompletato() {
        return completato;
    }

    public void setCompletato(boolean completato) {
        this.completato = completato;
    }

    public int getId_ispezione() {
        return id_ispezione;
    }

    public void setId_ispezione(int id_ispezione) {
        this.id_ispezione = id_ispezione;
    }

    public Ispezione getIspezione() {
        return ispezione;
    }

    public void setIspezione(Ispezione ispezione) {
        this.ispezione = ispezione;
    }

}