package net.pestov.meetup.users.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.pestov.meetup.core.bus.ActionHandler;
import net.pestov.meetup.core.bus.jms.ActionJmsListenerEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListenerConfigurer;
import org.springframework.jms.config.JmsListenerEndpointRegistrar;

import java.util.Map;

/**
 * Created by eugene on 21/09/2015.
 */
@Configuration("auditingApiHandlerConfiguration")
@EnableJms
public class EventListenerConfiguration implements JmsListenerConfigurer {

    @Autowired
    private ApplicationContext context;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void configureJmsListeners(JmsListenerEndpointRegistrar registrar) {
        Map<String, Object> commandHandlers = context.getBeansWithAnnotation(ActionHandler.class);
        for (String beanName : commandHandlers.keySet()) {
            registrar.registerEndpoint(
                    new ActionJmsListenerEndpoint(
                            objectMapper,
                            commandHandlers.get(beanName),
                            context.findAnnotationOnBean(beanName, ActionHandler.class)));
        }
    }
}
