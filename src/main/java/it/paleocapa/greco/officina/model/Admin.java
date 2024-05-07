package it.paleocapa.greco.officina.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "Admin")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Admin implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_admin;
    
    private String nome;
    private String cognome;
    private String email;
    private String numero_telefono;
    private String password;

    public Admin(String nome, String cognome, String email, String numero_telefono, String password) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.numero_telefono = numero_telefono;
        this.password = password;
    }

}