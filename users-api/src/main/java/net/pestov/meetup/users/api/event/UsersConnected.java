package net.pestov.meetup.users.api.event;

import net.pestov.meetup.core.bus.Event;

/**
 * Created by eugene on 21/09/2015.
 */
public class UsersConnected extends Event {
    public String userName1;
    public String userName2;
}
