package net.pestov.meetup.users.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.pestov.meetup.core.bus.ActionDispatcher;
import net.pestov.meetup.core.bus.jms.ActionDispatcherImpl;
import net.pestov.meetup.core.bus.jms.SimpleJsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.messaging.converter.MessageConverter;

import static org.elasticsearch.node.NodeBuilder.nodeBuilder;

/**
 * Created by eugene on 20/09/2015.
 */
@Configuration
@EnableElasticsearchRepositories
public class ApplicationConfiguration {

    @Autowired
    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Bean
    public ElasticsearchOperations elasticsearchTemplate() {
        return new ElasticsearchTemplate(nodeBuilder().local(true).node().client());
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
