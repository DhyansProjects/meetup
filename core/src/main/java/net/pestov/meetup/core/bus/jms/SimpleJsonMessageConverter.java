package net.pestov.meetup.core.bus.jms;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.converter.MessageConversionException;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.support.GenericMessage;

/**
 * Created by eugene on 21/09/2015.
 */
public class SimpleJsonMessageConverter implements MessageConverter {

    ObjectMapper objectMapper;

    public SimpleJsonMessageConverter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public Object fromMessage(org.springframework.messaging.Message<?> message, Class<?> targetClass) {
        return null;
    }

    @Override
    public org.springframework.messaging.Message<?> toMessage(Object payload, MessageHeaders headers) {
        try {
            return new GenericMessage<String>(objectMapper.writeValueAsString(payload));
        } catch (JsonProcessingException e) {
            throw new MessageConversionException("Could not convert action to JSON", e);
        }
    }
}
