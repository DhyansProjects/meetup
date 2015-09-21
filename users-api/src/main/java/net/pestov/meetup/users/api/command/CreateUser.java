package net.pestov.meetup.users.api.command;

import net.pestov.meetup.core.bus.Command;

/**
 * Created by eugene on 20/09/2015.
 */
public class CreateUser extends Command {

    public final String userName;
    public final String userFullName;

    public CreateUser(String userName, String userFullName) {
        this.userName = userName;
        this.userFullName = userFullName;
    }
}
