package it.paleocapa.greco.officina.model;

import jakarta.persistence.*;
import java.util.Set;
import java.io.Serializable;
import lombok.*;

@Entity
@Table(name = "Template_Ispezione")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class TemplateIspezione implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_templ_ispezione;

    @ManyToMany
    @JoinTable(name="Template_Task_Template_Ispezione",
            joinColumns = @JoinColumn(name = "id_templ_ispezione"),
            inverseJoinColumns = @JoinColumn(name = "id_templ_task"))
    Set<TemplateTask> templateTasks;

    private String nome;
    private int id_shop;

    @ManyToOne
    @JoinColumn(name = "id_shop", referencedColumnName = "id_shop", insertable = false, updatable = false)
    private Shop shop;

}
