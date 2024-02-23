package it.paleocapa.greco.officina.model;

import java.io.Serializable;
import java.util.Set;
import jakarta.persistence.*;

@Entity
@Table(name = "Template_Task")
public class TemplateTask implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_templ_task;

    @ManyToMany
    @JoinTable(name="Template_Task_Template_Ispezione",
            joinColumns = @JoinColumn(name = "id_templ_task"),
            inverseJoinColumns = @JoinColumn(name = "id_templ_ispezione"))
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