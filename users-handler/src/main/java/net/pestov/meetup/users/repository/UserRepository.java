package net.pestov.meetup.users.repository;

import net.pestov.meetup.users.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by eugene on 21/09/2015.
 */
@Repository
public interface UserRepository extends JpaRepository <User, Long> {

    User findByUserName(String userName);
}
