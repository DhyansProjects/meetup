package net.pestov.meetup.users.api.command;

import net.pestov.meetup.core.bus.Command;

/**
 * Created by eugene on 20/09/2015.
 */
public class CreateConnectionRequest extends Command {

    public final String originatorUserName;
    public final String targetUserName;

    public CreateConnectionRequest(String originatorUserName, String targetUserName) {
        this.originatorUserName = originatorUserName;
        this.targetUserName = targetUserName;
    }
}
