package it.paleocapa.greco.officina.utilities;

public class OrdineInputFields {
    public static OrderInputField[] input_fields = new OrderInputField[]{
        new OrderInputField("titolo", "Titolo ordine", Tipo.text, true),
        new OrderInputField("targa", "Targa veicolo", Tipo.dropdown, true),
        new OrderInputField("costo", "Costo ordine", Tipo.number),
        new OrderInputField("data_scadenza", "Data di scadenza dell'ordine", Tipo.datetime),
        new OrderInputField("commento_cliente", "Commento del cliente", Tipo.text),
        new OrderInputField("autorizzato", "Ordine autorizzato", Tipo.dropdown),
        new OrderInputField("raccomandazione", "Raccomandazione dell'autofficina", Tipo.text),
        new OrderInputField("kanban", "Kanban in cui inserire l'ordine", Tipo.dropdown)
    };
}

class OrderInputField {
    String nome;
    String descrizione;
    String tipo;
    boolean required = false;

    public OrderInputField(String nome, String descrizione, Enum<Tipo> tipo) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.tipo = tipo.toString();
    }

    public OrderInputField(String nome, String descrizione, Enum<Tipo> tipo, boolean required) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.tipo = tipo.toString();
        this.required = required;
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

    public boolean isRequired() {
        return this.required;
    }

    public boolean getRequired() {
        return this.required;
    }

    public void setRequired(boolean required) {
        this.required = required;
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