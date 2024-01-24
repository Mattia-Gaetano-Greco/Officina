package it.paleocapa.greco.officina.model;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import jakarta.persistence.ManyToMany;

@Entity
@Table(name = "TemplateTask")
public class TemplateTask implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_templ_task;

    @ManyToMany
    Set<TemplateIspezione> templateIspezioni;

    private String nome;

    public TemplateTask() {}

    public int getId_templ_task() {
        return id_templ_task;
    }

    public void setId_templ_task(int id_templ_task) {
        this.id_templ_task = id_templ_task;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}