package it.paleocapa.greco.officina.converters;

import org.springframework.core.convert.converter.Converter;
import it.paleocapa.greco.officina.model.Veicolo;

public class StringToVeicoloConverter implements Converter<String, Veicolo> {

    @Override
    public Veicolo convert(@SuppressWarnings("null") String value) {
        return null;
    }

}
