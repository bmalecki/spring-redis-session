package com.example.one.user;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {

    private Map<String, User> users;

    UserService() {
        users = new HashMap<>();
        users.put("user1", new User("user1", "user1", "{noop}pass"));
        users.put("user2", new User("user2", "user2", "{noop}pass"));
        users.put("user3", new User("user3", "user3", "{noop}pass"));
    }

    public Optional<User> getUserById(String userId) {
        return Optional.of(users.get(userId));
    }
}
