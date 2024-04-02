package it.paleocapa.greco.officina.utilities;

public class OrdineFields {
    public static OrderInputField[] input_fields = new OrderInputField[]{
        new OrderInputField("titolo", "Titolo ordine", Tipo.text),
        new OrderInputField("targa", "Targa veicolo", Tipo.dropdown, true),
        new OrderInputField("ricavo", "Costo ordine", Tipo.number),
        new OrderInputField("data_scadenza", "Data di scadenza dell'ordine", Tipo.datetime),
        new OrderInputField("commento_cliente", "Commento del cliente", Tipo.text),
        new OrderInputField("autorizzato", "Ordine autorizzato", Tipo.dropdown, true),
        new OrderInputField("raccomandazione", "Raccomandazione dell'autofficina", Tipo.text),
        new OrderInputField("kanban", "Kanban in cui inserire l'ordine", Tipo.dropdown, true)
    };

    public static OrderShowFields[] show_fields = new OrderShowFields[]{
        new OrderShowFields("Titolo", "'(#' + ${ordine.getId_ordine()} + ') ' + ${ordine.getTitolo()}"),
        new OrderShowFields("Informazioni veicolo", "${ordine.getVeicolo().getMarca()} + ' - ' + ${ordine.getVeicolo().getModello()} + ' - ' + ${ordine.getVeicolo().getAnno_costruzione()}"),
        new OrderShowFields("Costo totale", "${ordine.getRicavo()}"),
        new OrderShowFields("Data di scadenza", "${ordine.getData_scadenza()}"),
        new OrderShowFields("Stato pagamento", "${ordine.isPagamento_effettuato()} ? 'Si' : 'No'"),
        new OrderShowFields("Commento cliente", "${ordine.getCommento_cliente()}"),
        new OrderShowFields("Data creazione", "${ordine.getData_creazione()}"),
        new OrderShowFields("Autrizzato", "${ordine.isAutorizzato()} ? 'Si' : 'No'"),
        new OrderShowFields("Raccomandazione", "${ordine.getRaccomandazione()}")
    };
}

class OrderInputField {
    String nome;
    String descrizione;
    String tipo;
    String required = "false";

    public OrderInputField(String nome, String descrizione, Enum<Tipo> tipo) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.tipo = tipo.toString();
    }

    public OrderInputField(String nome, String descrizione, Enum<Tipo> tipo, boolean required) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.tipo = tipo.toString();
        this.required = required ? "true" : "false";
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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
                return "datetime";
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