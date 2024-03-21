package it.paleocapa.greco.officina.model;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Shop")
public class Shop implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_shop;

    private String nome;

    private Long id_admin;

    @ManyToOne
    @JoinColumn(name = "id_admin", referencedColumnName = "id_admin", insertable = false, updatable = false)
    private Admin admin;

    public Shop() {}

    public Shop(String nome, Admin admin) {
        this.nome = nome;
        this.admin = admin;
        this.id_admin = admin.getId_admin();
    }

    public int getId_shop() {
        return id_shop;
    }

    public void setId_shop(int id_shop) {
        this.id_shop = id_shop;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getId_admin() {
        return id_admin;
    }

    public void setId_admin(Long id_admin) {
        this.id_admin = id_admin;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

}
