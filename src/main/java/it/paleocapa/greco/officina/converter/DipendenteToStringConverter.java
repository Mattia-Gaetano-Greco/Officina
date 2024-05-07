package it.paleocapa.greco.officina.converter;

import org.springframework.core.convert.converter.Converter;
import it.paleocapa.greco.officina.model.Dipendente;

public class DipendenteToStringConverter implements Converter<Dipendente, String> {

    @Override
    public String convert(@SuppressWarnings("null") Dipendente arg0) {
      return "";
    }

}
