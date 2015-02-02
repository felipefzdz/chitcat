package io.olid16.domain.collections;

import io.olid16.domain.entities.User;
import io.olid16.domain.values.Username;

import java.util.Optional;

public interface Users {
    User add(User user);

    Optional<User> by(Username username);
}
