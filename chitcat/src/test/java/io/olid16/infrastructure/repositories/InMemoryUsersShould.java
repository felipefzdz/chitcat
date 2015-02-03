package io.olid16.infrastructure.repositories;

import io.olid16.domain.entities.User;
import io.olid16.domain.values.Username;
import org.junit.Test;

import java.util.Optional;

import static com.google.common.truth.Truth.assertThat;
import static io.olid16.domain.entities.User.*;
import static io.olid16.domain.values.Username.*;

public class InMemoryUsersShould {
    
    @Test public void
    return_a_user_that_has_been_previously_added() {
        InMemoryUsers inMemoryUsers = new InMemoryUsers();
        inMemoryUsers.add(createUser(createUsername("Alice")));
        Optional<User> user = inMemoryUsers.by(createUsername("Alice"));
        assertThat(user.isPresent());
    }


    @Test public void
    return_empty_if_that_user_has_not_been_inserted() {
        InMemoryUsers inMemoryUsers = new InMemoryUsers();
        inMemoryUsers.add(createUser(createUsername("Bob")));
        Optional<User> user = inMemoryUsers.by(createUsername("Alice"));
        assertThat(user.isPresent()).isFalse();
    }

}