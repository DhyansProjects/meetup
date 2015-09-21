package net.pestov.meetup.users.api.resource;

import net.pestov.meetup.users.api.repository.UserProjectionRepository;
import net.pestov.meetup.users.api.service.UserService;
import net.pestov.meetup.users.api.view.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by eugene on 20/09/2015.
 */
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final UserProjectionRepository userRepository;

    @Autowired
    public UserController(UserService userService, UserProjectionRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public HttpEntity<PagedResources<User>> getUsers(Pageable pageable,
            PagedResourcesAssembler assembler) {

        Page<User> users = userRepository.findAll(pageable);
        return new ResponseEntity<>(assembler.toResource(users), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public void createUser(@RequestBody UserTemplate userTemplate) {
        userService.createUser(userTemplate.userName, userTemplate.userFullName);
    }

    @RequestMapping(path = "/{userName}/connections", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public HttpEntity<PagedResources<User>> getUserConnections(
            @PathVariable("userName") String userName,
            Pageable pageable,
            PagedResourcesAssembler assembler) {

        Page<User> users = userRepository.findByFriendsContains(userName, pageable);
        return new ResponseEntity<>(assembler.toResource(users), HttpStatus.OK);
    }

    @RequestMapping(path = "/{userName}/connections", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public void createConnectionRequest(@PathVariable("userName") String userName,
            @RequestBody UserTemplate user) {
        userService.createConnectionRequest(userName, user.userName);
    }

}
