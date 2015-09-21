package net.pestov.meetup.users.event;

import net.pestov.meetup.core.bus.Event;

/**
 * Created by eugene on 21/09/2015.
 */
public class UserCreated extends Event {

    public String userName;
    public String userFullName;

    public UserCreated() {}

    public UserCreated(String userName, String userFullName) {

        this.userName = userName;
        this.userFullName = userFullName;
    }
}
