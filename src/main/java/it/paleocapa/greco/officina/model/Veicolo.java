package it.paleocapa.greco.officina.model;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Veicolo")
@IdClass(VeicoloId.class)
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

    public Veicolo() {}


    public String getTarga() {
        return this.targa;
    }

    public void setTarga(String targa) {
        this.targa = targa;
    }

    public String getNum_telaio() {
        return this.num_telaio;
    }

    public void setNum_telaio(String num_telaio) {
        this.num_telaio = num_telaio;
    }

    public int getId_cliente() {
        return this.id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getModello() {
        return this.modello;
    }

    public void setModello(String modello) {
        this.modello = modello;
    }

    public String getMarca() {
        return this.marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getAnno_costruzione() {
        return this.anno_costruzione;
    }

    public void setAnno_costruzione(int anno_costruzione) {
        this.anno_costruzione = anno_costruzione;
    }

    public Cliente getCliente() {
        return this.cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

}

