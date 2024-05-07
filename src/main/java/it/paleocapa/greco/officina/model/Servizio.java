package it.paleocapa.greco.officina.model;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.*;

@Entity
@Table(name = "Servizio")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Servizio implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_servizio;
    
    @Enumerated(EnumType.STRING)
    private StatusServizio status;

    private String nome;
    private float ore_stimate;
    private float ore_impiegate;
    private boolean completato;
    private int id_ordine;

    @ManyToOne
    @JoinColumn(name = "id_ordine", referencedColumnName = "id_ordine", insertable = false, updatable = false)
    private Ordine ordine;

}

