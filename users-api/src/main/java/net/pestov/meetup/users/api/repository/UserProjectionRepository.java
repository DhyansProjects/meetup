package net.pestov.meetup.users.api.repository;

import net.pestov.meetup.users.api.view.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by eugene on 20/09/2015.
 */
@Repository
public interface UserProjectionRepository extends ElasticsearchRepository<User, String> {

    Page<User> findByFriendsContains(String userName, Pageable pagable);

    User findByUserName(String userName);
}
