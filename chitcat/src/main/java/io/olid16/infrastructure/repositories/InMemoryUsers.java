package io.olid16.infrastructure.repositories;

import io.olid16.domain.collections.Users;
import io.olid16.domain.entities.User;
import io.olid16.domain.values.Username;

import java.util.Map;
import java.util.Optional;

import static com.google.common.collect.Maps.newHashMap;
import static java.util.Optional.ofNullable;

public class InMemoryUsers implements Users {
    private Map<Username, User> users = newHashMap();

    @Override
    public User add(User user) {
        users.put(user.username(), user);
        return user;
    }

    @Override
    public Optional<User> by(Username username) {
        return ofNullable(users.get(username));
    }
}
