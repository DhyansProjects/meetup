package net.pestov.meetup.users.api.service;

import net.pestov.meetup.core.bus.ActionDispatcher;
import net.pestov.meetup.users.api.command.CreateConnectionRequest;
import net.pestov.meetup.users.api.command.CreateUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by eugene on 20/09/2015.
 */
@Service
public class UserService {

    private ActionDispatcher actionDispatcher;

    @Autowired
    public UserService(ActionDispatcher actionDispatcher) {
        this.actionDispatcher = actionDispatcher;
    }

    public void createUser(String userName, String userFullName) {
        actionDispatcher.dispatch(new CreateUser(userName, userFullName));
    }

    public void createConnectionRequest(String originatorUserName, String targetUserName) {
        actionDispatcher.dispatch(new CreateConnectionRequest(originatorUserName, targetUserName));
    }
}
