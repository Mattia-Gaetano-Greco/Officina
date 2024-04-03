package it.paleocapa.greco.officina.converters;

import org.springframework.core.convert.converter.Converter;
import it.paleocapa.greco.officina.model.Admin;

public class StringToAdminConverter implements Converter<String, Admin> {

    @Override
    public Admin convert(@SuppressWarnings("null") String value) {
        return null;
    }

}
