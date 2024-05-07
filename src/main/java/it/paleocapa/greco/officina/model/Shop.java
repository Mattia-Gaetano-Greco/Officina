package it.paleocapa.greco.officina.model;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.*;

@Entity
@Table(name = "Shop")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Shop implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_shop;

    private String nome;
    private Long id_admin;

    @ManyToOne
    @JoinColumn(name = "id_admin", referencedColumnName = "id_admin", insertable = false, updatable = false)
    private Admin admin;

    public Shop(String nome, Admin admin) {
        this.nome = nome;
        this.admin = admin;
        this.id_admin = admin.getId_admin();
    }
    
}
