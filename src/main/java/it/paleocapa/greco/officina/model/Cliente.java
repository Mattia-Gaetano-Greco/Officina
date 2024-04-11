package it.paleocapa.greco.officina.model;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Cliente")
public class Cliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_cliente;

    private String nome;

    private String cognome;

    private String email;

    private String numero_telefono;

    private String password;

    public Cliente() {}

    public Cliente(String nome, String cognome, String email, String numero_telefono, String password) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.numero_telefono = numero_telefono;
        this.password = password;
    }

    public Cliente(Long id_cliente, String nome, String cognome, String email, String numero_telefono, String password) {
        this.id_cliente = id_cliente;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.numero_telefono = numero_telefono;
        this.password = password;
    }

    public Long getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(Long id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumero_telefono() {
        return numero_telefono;
    }

    public void setNumero_telefono(String numero_telefono) {
        this.numero_telefono = numero_telefono;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
