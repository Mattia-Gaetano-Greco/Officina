package it.paleocapa.greco.officina.model;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.*;

@Entity
@Table(name = "Parte")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Parte implements Serializable {

    @Id
    private String id_parte;
    
    @Enumerated(EnumType.STRING)
    private StatoParte stato;
    
    private String nome;
    private String descrizione;
    private float costo_acquisto;
    private int id_shop;
    private Integer id_servizio;

    @ManyToOne
    @JoinColumn(name = "id_shop", referencedColumnName = "id_shop", insertable = false, updatable = false)
    private Shop shop;

    @ManyToOne
    @JoinColumn(name = "id_servizio", referencedColumnName = "id_servizio", insertable = false, updatable = false)
    private Servizio servizio;

}

