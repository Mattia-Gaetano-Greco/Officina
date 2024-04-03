package it.paleocapa.greco.officina.converters;

import java.time.DateTimeException;
import java.time.LocalDate;

import org.springframework.core.convert.converter.Converter;

public class StringToLocalDateConverter implements Converter<String, LocalDate> {

    @Override
    public LocalDate convert(@SuppressWarnings("null") String value) {
        try {
            LocalDate d = LocalDate.parse(value);
            return d;
        } catch (DateTimeException e) {
            return null;
        }
    }

}
