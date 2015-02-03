package io.olid16.actions;

import com.google.inject.Inject;
import io.olid16.domain.collections.Timelines;
import io.olid16.domain.collections.Users;
import io.olid16.domain.collections.Walls;
import io.olid16.domain.entities.User;
import io.olid16.domain.values.Chit;
import io.olid16.domain.values.Username;

import java.util.Set;

import static io.olid16.domain.entities.User.*;

public class CreateChit {
    private final Users users;
    private final Timelines timelines;
    private final Walls walls;

    @Inject
    public CreateChit(Users users, Timelines timelines, Walls walls) {
        this.users = users;
        this.timelines = timelines;
        this.walls = walls;
    }

    public void with(Chit chit) {
        User user = createUserIfMissingFor(chit.username());
        updateFollowersWallWith(chit, user.followers());
        updateTimelineWith(chit);
        updateWallWith(chit);
    }

    private User createUserIfMissingFor(Username username) {
        return users.by(username).orElse(users.add(createUser(username)));
    }

    private void updateFollowersWallWith(Chit chit, Set<Username> followers) {
        followers.stream()
                .forEach(follower -> walls.add(follower, chit));
    }

    private void updateTimelineWith(Chit chit) {
        timelines.add(chit);
    }

    private void updateWallWith(Chit chit) {
        walls.add(chit.username(), chit);
    }
}
