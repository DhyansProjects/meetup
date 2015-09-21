package net.pestov.meetup.core.bus.jms;

import net.pestov.meetup.core.bus.Action;
import net.pestov.meetup.core.bus.ActionDispatcher;
import net.pestov.meetup.core.bus.Command;
import org.springframework.jms.core.JmsMessagingTemplate;

import static net.pestov.meetup.core.bus.jms.ActionDestinationHelper.resolveDestinationName;

/**
 * Created by eugene on 20/09/2015.
 */
public class ActionDispatcherImpl implements ActionDispatcher {

    JmsMessagingTemplate jmsMessagingTemplate;

    public ActionDispatcherImpl(JmsMessagingTemplate jmsMessagingTemplate) {
        this.jmsMessagingTemplate = jmsMessagingTemplate;
    }

    @Override
    public <T extends Action> void dispatch(T action) {
        String destination = resolveDestinationName(action.getClass());
        jmsMessagingTemplate.convertAndSend(destination, action);
    }
}
