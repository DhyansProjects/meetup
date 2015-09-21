package net.pestov.meetup.users.api.command.handler;

import net.pestov.meetup.core.bus.ActionDispatcher;
import net.pestov.meetup.core.bus.ActionHandler;
import net.pestov.meetup.users.api.command.CreateUser;
import net.pestov.meetup.users.domain.User;
import net.pestov.meetup.users.event.UserCreated;
import net.pestov.meetup.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;

/**
 * Created by eugene on 20/09/2015.
 */
@ActionHandler(command = CreateUser.class)
public class CreateUserHandler {

    private UserRepository userRepository;
    private ActionDispatcher actionDispatcher;

    @Autowired
    public CreateUserHandler(UserRepository userRepository, ActionDispatcher actionDispatcher) {
        this.userRepository = userRepository;
        this.actionDispatcher = actionDispatcher;
    }

    @Transactional
    public void handle(CreateUser command) {
        User user = new User();
        user.setUserName(command.userName);
        user.setUserFullName(command.userFullName);
        userRepository.save(user);

        actionDispatcher.dispatch(new UserCreated(user.getUserName(), user.getUserFullName()));
    }
}
