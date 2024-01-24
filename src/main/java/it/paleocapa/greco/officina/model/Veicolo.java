package it.paleocapa.greco.officina.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "Veicolo")
@IdClass(VeicoloId.class)
public class Veicolo implements Serializable {

    @Id
    private String targa;

    @Id
    private String num_telaio;

    private String modello;

    private String marca;

    private int anno_costruzione;

    public Veicolo() {}

    public String getTarga() {
        return targa;
    }

    public void setTarga(String targa) {
        this.targa = targa;
    }

    public String getNum_telaio() {
        return num_telaio;
    }

    public void setNum_telaio(String num_telaio) {
        this.num_telaio = num_telaio;
    }

    public String getModello() {
        return modello;
    }

    public void setModello(String modello) {
        this.modello = modello;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getAnno_costruzione() {
        return anno_costruzione;
    }

    public void setAnno_costruzione(int anno_costruzione) {
        this.anno_costruzione = anno_costruzione;
    }

}

