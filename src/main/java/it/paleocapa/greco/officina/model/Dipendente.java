package it.paleocapa.greco.officina.model;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.*;

@Entity
@Table(name = "Dipendente")
@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor
public class Dipendente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_dipendente;

    private String nome;
    private String cognome;
    private String email;
    private String numero_telefono;
    private String password;

    @ManyToOne
    @JoinColumn(name = "id_shop", referencedColumnName = "id_shop", insertable = false, updatable = false)
    private Shop shop;

    public Dipendente(String nome, String cognome, String email, String numero_telefono, String password, Shop shop) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.numero_telefono = numero_telefono;
        this.password = password;
        this.shop = shop;
    }

    public Dipendente(Long id, String nome, String cognome, String email, String numero_telefono, String password) {
        this.id_dipendente = id;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.numero_telefono = numero_telefono;
        this.password = password;
    }

    public Dipendente(String nome, String cognome, String email, String numero_telefono, String password) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.numero_telefono = numero_telefono;
        this.password = password;
    }
}
