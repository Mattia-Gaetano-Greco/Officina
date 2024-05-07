package it.paleocapa.greco.officina.converter;

import org.springframework.core.convert.converter.Converter;
import it.paleocapa.greco.officina.model.Dipendente;

public class StringToDipendenteConverter implements Converter<String, Dipendente> {

    @Override
    public Dipendente convert(@SuppressWarnings("null") String value) {
        return null;
    }

}
