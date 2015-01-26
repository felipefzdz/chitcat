package io.olid16.actions;

import com.google.inject.Inject;
import io.olid16.domain.collections.Timelines;
import io.olid16.domain.collections.Users;
import io.olid16.domain.entities.User;
import io.olid16.domain.values.Chit;
import io.olid16.domain.values.Username;

import java.util.Optional;

public class CreateChit {
    private final Users users;
    private final Timelines timelines;

    @Inject
    public CreateChit(Users users, Timelines timelines) {
        this.users = users;
        this.timelines = timelines;
    }

    public void with(Username username, Chit chit) {
        createUserIfMissingWith(username);
        timelines.add(username, chit);
    }

    private void createUserIfMissingWith(Username username) {
        Optional<User> user = users.by(username);
        if (!user.isPresent()) {
            users.add(User.create(username));
        }
    }
}
