package it.paleocapa.greco.officina.model;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.*;

@Entity
@Table(name = "Veicolo")
@IdClass(VeicoloId.class)
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Veicolo implements Serializable {

    @Id
    private String targa;

    @Id
    private String num_telaio;

    private int id_cliente;

    private String modello;

    private String marca;

    private int anno_costruzione;

    @ManyToOne
    @JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente", insertable = false, updatable = false)
    private Cliente cliente;

}