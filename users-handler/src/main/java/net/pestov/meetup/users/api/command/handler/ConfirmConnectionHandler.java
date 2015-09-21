package net.pestov.meetup.users.api.command.handler;

import net.pestov.meetup.core.bus.ActionHandler;
import net.pestov.meetup.users.api.command.ConfirmConnection;
import net.pestov.meetup.users.domain.User;
import net.pestov.meetup.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;

/**
 * Created by eugene on 20/09/2015.
 */
@ActionHandler(command = ConfirmConnection.class)
public class ConfirmConnectionHandler {

    private UserRepository userRepository;

    @Autowired
    public ConfirmConnectionHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void handle(ConfirmConnection command) {
        User confirmingUser = userRepository.findByUserName(command.confirmingUserName);
        User requester = userRepository.findByUserName(command.requesterUserName);
        confirmingUser.getFriends().add(requester);
        userRepository.save(confirmingUser);
    }
}
