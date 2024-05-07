package it.paleocapa.greco.officina.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import lombok.*;

@Entity
@Table(name = "Ordine")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class Ordine implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_ordine;

    private LocalDate appuntamento_fissato;
    private String commento_cliente;
    private LocalDate data_creazione;
    private LocalDate data_scadenza;
    private String titolo;
    private boolean autorizzato;
    private String raccomandazione;
    private boolean pagamento_effettuato;
    private float ricavo;
    private float costo_totale;
    private Long id_kanban;
    private Long id_cliente;
    //private Long id_dipendente;
    private String targa;
    private String num_telaio;

    @ManyToOne
    @JoinColumn(name = "id_kanban", referencedColumnName = "id_kanban", insertable = false, updatable = false)
    private Kanban kanban;

    @ManyToOne
    @JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente", insertable = false, updatable = false)
    private Cliente cliente;

    /*@ManyToOne
    @JoinColumn(name = "id_dipendente", referencedColumnName = "id_dipendente", insertable = false, updatable = false)
    private Dipendente dipendente;*/

    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "targa", referencedColumnName = "targa", insertable = false, updatable = false),
        @JoinColumn(name = "num_telaio", referencedColumnName = "num_telaio", insertable = false, updatable = false)
    })
    private Veicolo veicolo;

    public String getAppuntamento_fissato_string() {
        if (appuntamento_fissato != null)
            return appuntamento_fissato.format(DateTimeFormatter.ofPattern("dd-MM-uuuu")).toString();
        return "";
    }

    public String getData_creazione_string() {
        if (data_creazione != null)
            return data_creazione.format(DateTimeFormatter.ofPattern("dd-MM-uuuu")).toString();
        return "";
    }

    public String getData_scadenza_string() {
        if (data_scadenza != null)
            return data_scadenza.format(DateTimeFormatter.ofPattern("dd-MM-uuuu")).toString();
        return "";
    }
}

