package net.pestov.meetup.users.api.event.listener;

import net.pestov.meetup.core.bus.ActionHandler;
import net.pestov.meetup.users.api.event.UserCreated;
import net.pestov.meetup.users.api.repository.UserProjectionRepository;
import net.pestov.meetup.users.api.view.model.User;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by eugene on 21/09/2015.
 */
@ActionHandler(command = UserCreated.class)
public class UserCreatedListener {

    private UserProjectionRepository userProjectionRepository;

    @Autowired
    public UserCreatedListener(UserProjectionRepository userProjectionRepository) {
        this.userProjectionRepository = userProjectionRepository;
    }

    public void handle(UserCreated event) {
        User user = new User(event.userName, event.userFullName);
        userProjectionRepository.save(user);
    }
}
