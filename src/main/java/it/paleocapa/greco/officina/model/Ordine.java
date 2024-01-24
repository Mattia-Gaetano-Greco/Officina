package it.paleocapa.greco.officina.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Ordine")
public class Ordine implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_ordine;

    private Date appuntamento_fissato;

    private String commento_cliente;

    private Date data_creazione;

    private Date data_scadenza;

    private String titolo;

    private boolean autorizzato;

    private String raccomandazione;

    private boolean pagamento_effettuato;

    private float ricavo;

    private int id_kanban;

    private int id_cliente;

    private int id_dipendente;

    private String targa;

    private String num_telaio;

    @ManyToOne
    @JoinColumn(name = "id_kanban", referencedColumnName = "id_kanban", insertable = false, updatable = false)
    private Kanban kanban;

    @ManyToOne
    @JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente", insertable = false, updatable = false)
    private Utente cliente;

    @ManyToOne
    @JoinColumn(name = "id_dipendente", referencedColumnName = "id_dipendente", insertable = false, updatable = false)
    private Utente dipendente;

    @ManyToOne
    @JoinColumn(name = "targa, num_telaio", referencedColumnName = "targa, num_telaio", insertable = false, updatable = false) // TODO: ricontrollare che sia corretto
    private Veicolo veicolo;

    public Ordine() {}

    public int getId_ordine() {
        return id_ordine;
    }

    public void setId_ordine(int id_ordine) {
        this.id_ordine = id_ordine;
    }

    public Date getAppuntamento_fissato() {
        return appuntamento_fissato;
    }

    public void setAppuntamento_fissato(Date appuntamento_fissato) {
        this.appuntamento_fissato = appuntamento_fissato;
    }

    public String getCommento_cliente() {
        return commento_cliente;
    }

    public void setCommento_cliente(String commento_cliente) {
        this.commento_cliente = commento_cliente;
    }

    public Date getData_creazione() {
        return data_creazione;
    }

    public void setData_creazione(Date data_creazione) {
        this.data_creazione = data_creazione;
    }

    public Date getData_scadenza() {
        return data_scadenza;
    }

    public void setData_scadenza(Date data_scadenza) {
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

    public int getId_kanban() {
        return id_kanban;
    }

    public void setId_kanban(int id_kanban) {
        this.id_kanban = id_kanban;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public int getId_dipendente() {
        return id_dipendente;
    }

    public void setId_dipendente(int id_dipendente) {
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

    public Utente getCliente() {
        return cliente;
    }

    public void setCliente(Utente cliente) {
        this.cliente = cliente;
    }

    public Utente getDipendente() {
        return dipendente;
    }

    public void setDipendente(Utente dipendente) {
        this.dipendente = dipendente;
    }

    public Veicolo getVeicolo() {
        return veicolo;
    }

    public void setVeicolo(Veicolo veicolo) {
        this.veicolo = veicolo;
    }

}

