package net.pestov.meetup.users.event;

import net.pestov.meetup.core.bus.Event;

/**
 * Created by eugene on 21/09/2015.
 */
public class UsersConnected extends Event {
    public String userName1;
    public String userName2;

    public UsersConnected() {}

    public UsersConnected(String userName1, String userName2) {
        this.userName1 = userName1;
        this.userName2 = userName2;
    }
}
