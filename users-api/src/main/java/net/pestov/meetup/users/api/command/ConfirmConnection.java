package net.pestov.meetup.users.api.command;

import net.pestov.meetup.core.bus.Command;

/**
 * Created by eugene on 20/09/2015.
 */
public class ConfirmConnection extends Command {

    public final String requesterUserName;
    public final String confirmingUserName;

    public ConfirmConnection(String requesterUserName, String confirmingUserName) {
        this.requesterUserName = requesterUserName;
        this.confirmingUserName = confirmingUserName;
    }
}
