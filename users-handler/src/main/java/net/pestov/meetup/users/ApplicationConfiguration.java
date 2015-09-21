package net.pestov.meetup.users;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.pestov.meetup.core.bus.ActionDispatcher;
import net.pestov.meetup.core.bus.jms.ActionDispatcherImpl;
import net.pestov.meetup.core.bus.jms.SimpleJsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.messaging.converter.MessageConverter;

/**
 * Created by eugene on 21/09/2015.
 */
@Configuration
@EnableJpaRepositories
public class ApplicationConfiguration {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Bean
    public ObjectMapper objectMapper() {
        return objectMapper;
    }

    @Bean
    public ActionDispatcher actionDispatcher() {
        return new ActionDispatcherImpl(jmsMessagingTemplate);
    }

    @Bean MessageConverter messageConverter() {
        SimpleJsonMessageConverter messageConverter = new SimpleJsonMessageConverter(objectMapper);
        jmsMessagingTemplate.setMessageConverter(messageConverter);
        return messageConverter;
    }
}
