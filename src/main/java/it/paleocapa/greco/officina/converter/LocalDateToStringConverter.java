package it.paleocapa.greco.officina.converter;

import org.springframework.core.convert.converter.Converter;
import java.time.LocalDate;

public class LocalDateToStringConverter implements Converter<LocalDate, String> {

    @Override
    public String convert(@SuppressWarnings("null") LocalDate arg0) {
      return arg0.toString();
    }

}