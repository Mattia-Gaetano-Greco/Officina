package it.paleocapa.greco.officina.model;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.*;

@Entity
@Table(name = "Task")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Task implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_task;
    
    @Enumerated(EnumType.STRING)
    private StatoPezzo stato_pezzo;
    
    private String nome;
    private boolean completato;
    private int id_ispezione;

    @ManyToOne
    @JoinColumn(name = "id_ispezione", referencedColumnName = "id_ispezione", insertable = false, updatable = false)
    private Ispezione ispezione;

}