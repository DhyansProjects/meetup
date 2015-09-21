package net.pestov.meetup.users.api.command;

import net.pestov.meetup.core.bus.Command;

/**
 * Created by eugene on 20/09/2015.
 */
public class CreateUser extends Command {

    public String userName;
    public String userFullName;

}
