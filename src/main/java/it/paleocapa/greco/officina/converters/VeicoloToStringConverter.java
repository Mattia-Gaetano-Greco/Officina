package it.paleocapa.greco.officina.converters;

import org.springframework.core.convert.converter.Converter;
import it.paleocapa.greco.officina.model.Veicolo;

public class VeicoloToStringConverter implements Converter<Veicolo, String> {

    @Override
    public String convert(@SuppressWarnings("null") Veicolo arg0) {
      return "";
    }

}
