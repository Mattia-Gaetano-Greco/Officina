package it.paleocapa.greco.officina.entities;

import java.io.Serializable;
import java.util.Objects;

public class VeicoloId implements Serializable {

    private String targa;

    private String num_telaio;

    public VeicoloId() {}

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VeicoloId veicoloId = (VeicoloId) o;
        return Objects.equals(targa, veicoloId.targa) &&
                Objects.equals(num_telaio, veicoloId.num_telaio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(targa, num_telaio);
    }
}
