package it.paleocapa.greco.officina.model;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.*;

@Entity
@Table(name = "Cliente")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Cliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_cliente;

    private String nome;
    private String cognome;
    private String email;
    private String numero_telefono;
    private String password;

    public Cliente(String nome, String cognome, String email, String numero_telefono, String password) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.numero_telefono = numero_telefono;
        this.password = password;
    }

}
