package it.paleocapa.greco.officina.model;

import java.io.Serializable;
import java.util.Set;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Template_Task")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
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

}