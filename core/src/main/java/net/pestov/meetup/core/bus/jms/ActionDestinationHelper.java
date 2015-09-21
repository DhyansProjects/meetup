package net.pestov.meetup.core.bus.jms;

import net.pestov.meetup.core.bus.Action;
import net.pestov.meetup.core.bus.Command;

/**
 * Created by eugene on 21/09/2015.
 */
public class ActionDestinationHelper {

    public static final String COMMAND_DESTINATION_PREFIX = "activemq:queue:net.pestov.meetup.command.";
    public static final String EVENT_DESTINATION_PREFIX = "activemq:topic:net.pestov.meetup.event.";

    public static String resolveDestinationName(Class<? extends Action> action) {
        if (Command.class.isAssignableFrom(action)) {
            return COMMAND_DESTINATION_PREFIX + action.getSimpleName();
        } else {
            return EVENT_DESTINATION_PREFIX + action.getSimpleName();
        }
    }
}
