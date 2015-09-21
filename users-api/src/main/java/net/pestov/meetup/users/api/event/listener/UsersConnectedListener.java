package net.pestov.meetup.users.api.event.listener;

import net.pestov.meetup.core.bus.ActionHandler;
import net.pestov.meetup.users.api.event.UsersConnected;
import net.pestov.meetup.users.api.repository.UserProjectionRepository;
import net.pestov.meetup.users.api.view.model.User;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by eugene on 21/09/2015.
 */
@ActionHandler(command = UsersConnected.class)
public class UsersConnectedListener {

    private UserProjectionRepository userProjectionRepository;

    @Autowired
    public UsersConnectedListener(UserProjectionRepository userProjectionRepository) {
        this.userProjectionRepository = userProjectionRepository;
    }

    public void handle(UsersConnected event) {
        User user1 = userProjectionRepository.findOne(event.userName1);
        user1.addFriend(event.userName2);
        userProjectionRepository.save(user1);
        User user2 = userProjectionRepository.findOne(event.userName2);
        user2.addFriend(event.userName1);
        userProjectionRepository.save(user2);
    }
}
