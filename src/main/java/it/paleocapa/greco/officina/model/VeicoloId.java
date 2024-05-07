package it.paleocapa.greco.officina.model;

import java.io.Serializable;
import java.util.Objects;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class VeicoloId implements Serializable {

    private String targa;
    private String num_telaio;

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
