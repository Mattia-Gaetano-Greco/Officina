package it.paleocapa.greco.officina.model;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Dipendente")
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

    public Dipendente() {}

    public Dipendente(String nome, String cognome, String email, String numero_telefono, String password, Shop shop) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.numero_telefono = numero_telefono;
        this.password = password;
        this.shop = shop;
    }

    public Dipendente(Long id, String nome, String cognome, String email, String numero_telefono, String password, Shop shop) {
        this.id_dipendente = id;
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

    public Long getId_utente() {
        return id_dipendente;
    }

    public void setId_utente(Long id_dipendente) {
        this.id_dipendente = id_dipendente;
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

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

}
