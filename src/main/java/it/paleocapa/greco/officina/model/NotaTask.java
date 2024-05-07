package it.paleocapa.greco.officina.model;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.*;

@Entity
@Table(name = "NotaTask")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class NotaTask implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_nota_task;

    private String testo;
    private int id_task;

    @ManyToOne
    @JoinColumn(name = "id_task", referencedColumnName = "id_task", insertable = false, updatable = false)
    private Task task;

}

