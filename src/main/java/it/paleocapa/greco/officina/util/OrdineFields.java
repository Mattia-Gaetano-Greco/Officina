package it.paleocapa.greco.officina.util;

import java.util.HashMap;

public class OrdineFields {
    
    public static HashMap<String, OrderInputField> input_fields = new HashMap<String, OrderInputField>(){{
        put("titolo", new OrderInputField("Titolo ordine", Tipo.text));
        put("targa", new OrderInputField("Targa veicolo", Tipo.dropdown, true));
        put("ricavo", new OrderInputField("Ricavo dell'ordine", Tipo.number));
        put("costo_totale", new OrderInputField("Costo ordine", Tipo.number));
        put("data_scadenza", new OrderInputField("Data di scadenza dell'ordine", Tipo.datetime));
        put("commento_cliente", new OrderInputField("Commento del cliente", Tipo.text));
        put("autorizzato", new OrderInputField("Ordine autorizzato", Tipo.dropdown, true));
        put("raccomandazione", new OrderInputField("Raccomandazione dell'autofficina", Tipo.text));
        put("kanban", new OrderInputField("Kanban in cui inserire l'ordine", Tipo.dropdown, true));
    }};

    public static OrderShowFields[] show_fields = new OrderShowFields[]{
        new OrderShowFields("Titolo", "'(#' + ${ordine.getId_ordine()} + ') ' + ${ordine.getTitolo()}"),
        new OrderShowFields("Informazioni veicolo", "${ordine.getVeicolo().getMarca()} + ' - ' + ${ordine.getVeicolo().getModello()} + ' - ' + ${ordine.getVeicolo().getAnno_costruzione()}"),
        new OrderShowFields("Ricavo", "${ordine.getRicavo()}"),
        new OrderShowFields("Costo totale", "${ordine.getCosto_totale()}"),
        new OrderShowFields("Data di scadenza", "${ordine.getData_scadenza_string()}"),
        new OrderShowFields("Stato pagamento", "${ordine.isPagamento_effettuato()} ? 'Effettuato' : 'Non effettuato'"),
        new OrderShowFields("Commento cliente", "${ordine.getCommento_cliente()}"),
        new OrderShowFields("Data creazione", "${ordine.getData_creazione_string()}"),
        new OrderShowFields("Autrizzato", "${ordine.isAutorizzato()} ? 'Si' : 'No'"),
        new OrderShowFields("Raccomandazione", "${ordine.getRaccomandazione()}")
    };
    
}

class OrderInputField {
    String descrizione;
    String tipo;
    String required = "false";

    public OrderInputField(String descrizione, Enum<Tipo> tipo) {
        this.descrizione = descrizione;
        this.tipo = tipo.toString();
    }

    public OrderInputField(String descrizione, Enum<Tipo> tipo, boolean required) {
        this.descrizione = descrizione;
        this.tipo = tipo.toString();
        this.required = required ? "true" : "false";
    }

    public String getDescrizione() {
        return this.descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getTipo() {
        return this.tipo;
    }

    public void setTipo(Enum<Tipo> tipo) {
        this.tipo = tipo.toString();
    }

    public String isRequired() {
        return this.required;
    }

    public String getRequired() {
        return this.required;
    }

    public void setRequired(boolean required) {
        this.required = required ? "true" : "false";
    }

}

class OrderShowFields {
    String nome;
    String valore_to_parse;

    public OrderShowFields(String nome, String valore_to_parse) {
        this.nome = nome;
        this.valore_to_parse = valore_to_parse;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getValore_to_parse() {
        return this.valore_to_parse;
    }

    public void setValore_to_parse(String valore_to_parse) {
        this.valore_to_parse = valore_to_parse;
    }
    
}

enum Tipo {
    datetime, number, text, dropdown;

    @Override
    public String toString() {
        switch (this.ordinal()) {
            case 0:
                return "date";
            case 1:
                return "number";
            case 2:
                return "text";
            case 3:
                return "dropdown";
            default:
                return null;
        }
    }
}