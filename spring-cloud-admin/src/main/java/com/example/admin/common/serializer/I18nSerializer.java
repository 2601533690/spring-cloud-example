package com.example.admin.common.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class I18nSerializer extends JsonSerializer<String> {

    private final Logger logger = LoggerFactory.getLogger(I18nSerializer.class);

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private HttpServletRequest request;

    @Override
    public void serialize(String string, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException {
        try {
            string = messageSource.getMessage(string, null, request.getLocale());
        } catch (NoSuchMessageException ex) {
            logger.error(ex.getMessage());
        } finally {
            jsonGenerator.writeString(string);
        }
    }
}
