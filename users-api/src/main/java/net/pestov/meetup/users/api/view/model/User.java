package net.pestov.meetup.users.api.view.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by eugene on 20/09/2015.
 */
@Document(indexName = "users", type = "user")
public class User {

    @Id
    @Field(type = FieldType.String)
    private String userName;
    private String userFullName;
    private Set<String> friends = new HashSet<>();

    public User() {
    }

    public User(String userName, String userFullName) {
        this.userName = userName;
        this.userFullName = userFullName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public Set<String> getFriends() {
        return friends;
    }

    public void setFriends(Set<String> friends) {
        this.friends = friends;
    }

    public void addFriend(String userName) {
        this.friends.add(userName);
    }
}
