package net.pestov.meetup.core.bus.jms;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.pestov.meetup.core.bus.Action;
import net.pestov.meetup.core.bus.ActionHandler;
import org.springframework.jms.config.AbstractJmsListenerEndpoint;
import org.springframework.jms.listener.MessageListenerContainer;

import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.lang.reflect.Method;

import static net.pestov.meetup.core.bus.jms.ActionDestinationHelper.resolveDestinationName;

/**
 * Created by eugene on 21/08/2015.
 */
public class ActionJmsListenerEndpoint extends AbstractJmsListenerEndpoint {

    private final ObjectMapper mapper;

    private final Class<? extends Action> actionClass;
    private Object handler;
    private final Method method;

    public ActionJmsListenerEndpoint(ObjectMapper mapper, Object handler, ActionHandler annotation) {
        this.mapper = mapper;

        this.actionClass = annotation.command();
        this.handler = handler;

        try {
            method = this.handler.getClass().getMethod(annotation.method(), this.actionClass);
        } catch (NoSuchMethodException e) {
            throw new ConfigurationException(handler.getClass().getCanonicalName() +
                    " does not define corresponding '" + annotation.method() + "' method");
        }

        setId(this.actionClass.getSimpleName() + ".endpoint");
        setDestination(resolveDestinationName(actionClass));
    }

    @Override
    protected MessageListener createMessageListener(MessageListenerContainer container) {
        return (message) -> {
            if (message instanceof TextMessage) {
                try {
                    String body = ((TextMessage)message).getText();
                    method.invoke(handler, mapper.readValue(body, actionClass));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
    }
}
