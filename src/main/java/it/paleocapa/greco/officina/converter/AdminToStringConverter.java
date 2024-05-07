package it.paleocapa.greco.officina.converter;

import org.springframework.core.convert.converter.Converter;
import it.paleocapa.greco.officina.model.Admin;

public class AdminToStringConverter implements Converter<Admin, String> {

    @Override
    public String convert(@SuppressWarnings("null") Admin arg0) {
      return "";
    }

}
