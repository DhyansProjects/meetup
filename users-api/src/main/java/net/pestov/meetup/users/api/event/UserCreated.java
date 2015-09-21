package net.pestov.meetup.users.api.event;

import net.pestov.meetup.core.bus.Event;

/**
 * Created by eugene on 21/09/2015.
 */
public class UserCreated extends Event {

    public String userName;
    public String userFullName;

}
