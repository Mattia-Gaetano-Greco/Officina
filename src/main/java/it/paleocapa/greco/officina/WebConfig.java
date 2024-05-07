package it.paleocapa.greco.officina;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import it.paleocapa.greco.officina.converter.*;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(@SuppressWarnings("null") FormatterRegistry registry) {
        registry.addConverter(new AdminToStringConverter());
        registry.addConverter(new StringToAdminConverter());
        registry.addConverter(new ClienteToStringConverter());
        registry.addConverter(new StringToClienteConverter());
        registry.addConverter(new DipendenteToStringConverter());
        registry.addConverter(new StringToDipendenteConverter());
        registry.addConverter(new StringToVeicoloConverter());
        registry.addConverter(new VeicoloToStringConverter());
        registry.addConverter(new LocalDateToStringConverter());
        registry.addConverter(new StringToLocalDateConverter());
    }
}