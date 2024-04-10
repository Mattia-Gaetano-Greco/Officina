package it.paleocapa.greco.officina.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "Ordine")
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

    private int id_cliente;

    private Long id_dipendente;

    private String targa;

    private String num_telaio;

    @ManyToOne
    @JoinColumn(name = "id_kanban", referencedColumnName = "id_kanban", insertable = false, updatable = false)
    private Kanban kanban;

    @ManyToOne
    @JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente", insertable = false, updatable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "id_dipendente", referencedColumnName = "id_dipendente", insertable = false, updatable = false)
    private Dipendente dipendente;

    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "targa", referencedColumnName = "targa", insertable = false, updatable = false),
        @JoinColumn(name = "num_telaio", referencedColumnName = "num_telaio", insertable = false, updatable = false)
    })
    private Veicolo veicolo;

    public Ordine() {}

    public int getId_ordine() {
        return id_ordine;
    }

    public void setId_ordine(int id_ordine) {
        this.id_ordine = id_ordine;
    }

    public LocalDate getAppuntamento_fissato() {
        return appuntamento_fissato;
    }

    public String getAppuntamento_fissato_string() {
        if (appuntamento_fissato != null)
            return appuntamento_fissato.format(DateTimeFormatter.ofPattern("dd-MM-uuuu")).toString();
        return "";
    }

    public void setAppuntamento_fissato(LocalDate appuntamento_fissato) {
        this.appuntamento_fissato = appuntamento_fissato;
    }

    public String getCommento_cliente() {
        return commento_cliente;
    }

    public void setCommento_cliente(String commento_cliente) {
        this.commento_cliente = commento_cliente;
    }

    public LocalDate getData_creazione() {
        return data_creazione;
    }

    public String getData_creazione_string() {
        if (data_creazione != null)
            return data_creazione.format(DateTimeFormatter.ofPattern("dd-MM-uuuu")).toString();
        return "";
    }

    public void setData_creazione(LocalDate data_creazione) {
        this.data_creazione = data_creazione;
    }

    public LocalDate getData_scadenza() {
        return data_scadenza;
    }

    public String getData_scadenza_string() {
        if (data_scadenza != null)
            return data_scadenza.format(DateTimeFormatter.ofPattern("dd-MM-uuuu")).toString();
        return "";
    }

    public void setData_scadenza(LocalDate data_scadenza) {
        this.data_scadenza = data_scadenza;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public boolean isAutorizzato() {
        return autorizzato;
    }

    public void setAutorizzato(boolean autorizzato) {
        this.autorizzato = autorizzato;
    }

    public String getRaccomandazione() {
        return raccomandazione;
    }

    public void setRaccomandazione(String raccomandazione) {
        this.raccomandazione = raccomandazione;
    }

    public boolean isPagamento_effettuato() {
        return pagamento_effettuato;
    }

    public void setPagamento_effettuato(boolean pagamento_effettuato) {
        this.pagamento_effettuato = pagamento_effettuato;
    }

    public float getRicavo() {
        return ricavo;
    }

    public void setRicavo(float ricavo) {
        this.ricavo = ricavo;
    }

    public float getCosto_totale() {
        return costo_totale;
    }

    public void setCosto_totale(float costo_totale) {
        this.costo_totale = costo_totale;
    }

    public Long getId_kanban() {
        return id_kanban;
    }

    public void setId_kanban(Long id_kanban) {
        this.id_kanban = id_kanban;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public Long getId_dipendente() {
        return id_dipendente;
    }

    public void setId_dipendente(Long id_dipendente) {
        this.id_dipendente = id_dipendente;
    }

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

    public Kanban getKanban() {
        return kanban;
    }

    public void setKanban(Kanban kanban) {
        this.kanban = kanban;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Dipendente getDipendente() {
        return dipendente;
    }

    public void setDipendente(Dipendente dipendente) {
        this.dipendente = dipendente;
    }

    public Veicolo getVeicolo() {
        return veicolo;
    }

    public void setVeicolo(Veicolo veicolo) {
        this.veicolo = veicolo;
    }


    @Override
    public String toString() {
        return "{" +
            " id_ordine='" + getId_ordine() + "'" +
            ", appuntamento_fissato='" + getAppuntamento_fissato() + "'" +
            ", commento_cliente='" + getCommento_cliente() + "'" +
            ", data_creazione='" + getData_creazione() + "'" +
            ", data_scadenza='" + getData_scadenza() + "'" +
            ", titolo='" + getTitolo() + "'" +
            ", autorizzato='" + isAutorizzato() + "'" +
            ", raccomandazione='" + getRaccomandazione() + "'" +
            ", pagamento_effettuato='" + isPagamento_effettuato() + "'" +
            ", ricavo='" + getRicavo() + "'" +
            ", costo_totale='" + getCosto_totale() + "'" +
            ", id_kanban='" + getId_kanban() + "'" +
            ", id_cliente='" + getId_cliente() + "'" +
            ", id_dipendente='" + getId_dipendente() + "'" +
            ", targa='" + getTarga() + "'" +
            ", num_telaio='" + getNum_telaio() + "'" +
            ", kanban='" + getKanban() + "'" +
            ", cliente='" + getCliente() + "'" +
            ", dipendente='" + getDipendente() + "'" +
            ", veicolo='" + getVeicolo() + "'" +
            "}";
    }


}

