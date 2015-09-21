package net.pestov.meetup.users.api.command.handler;

import net.pestov.meetup.core.bus.ActionDispatcher;
import net.pestov.meetup.core.bus.ActionHandler;
import net.pestov.meetup.users.api.command.CreateConnectionRequest;
import net.pestov.meetup.users.domain.User;
import net.pestov.meetup.users.event.UsersConnected;
import net.pestov.meetup.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;

/**
 * Created by eugene on 20/09/2015.
 */
@ActionHandler(command = CreateConnectionRequest.class)
public class CreateConnectionRequestHandler {

    private UserRepository userRepository;
    private ActionDispatcher actionDispatcher;

    @Autowired
    public CreateConnectionRequestHandler(UserRepository userRepository, ActionDispatcher actionDispatcher) {
        this.userRepository = userRepository;
        this.actionDispatcher = actionDispatcher;
    }

    @Transactional
    public void handle(CreateConnectionRequest command) {
        User originator = userRepository.findByUserName(command.originatorUserName);
        User target = userRepository.findByUserName(command.targetUserName);
        originator.getFriends().add(target);
        target.getFriends().add(originator);
        userRepository.save(originator);
        userRepository.save(target);

        actionDispatcher.dispatch(new UsersConnected(command.originatorUserName, command.targetUserName));
    }
}
