package it.paleocapa.greco.officina.converters;

import org.springframework.core.convert.converter.Converter;

import it.paleocapa.greco.officina.model.Cliente;

public class ClienteToStringConverter implements Converter<Cliente, String> {

    @Override
    public String convert(@SuppressWarnings("null") Cliente arg0) {
      return "";
    }

}
