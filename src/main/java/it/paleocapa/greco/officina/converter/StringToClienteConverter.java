package it.paleocapa.greco.officina.converter;

import org.springframework.core.convert.converter.Converter;
import it.paleocapa.greco.officina.model.Cliente;

public class StringToClienteConverter implements Converter<String, Cliente> {

    @Override
    public Cliente convert(@SuppressWarnings("null") String value) {
        return null;
    }

}
