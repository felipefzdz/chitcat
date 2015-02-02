package io.olid16.actions;

import com.google.inject.Inject;
import io.olid16.domain.collections.Timelines;
import io.olid16.domain.collections.Users;
import io.olid16.domain.collections.Walls;
import io.olid16.domain.entities.User;
import io.olid16.domain.values.Chit;
import io.olid16.domain.values.Username;

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
        updateFollowersWall(chit, user);
        updateTimeline(chit);
        updateWall(chit);
    }

    private void updateWall(Chit chit) {
        walls.add(chit.username(), chit);
    }

    private void updateFollowersWall(Chit chit, User user) {
        user.followers().stream()
            .forEach(follower -> walls.add(follower, chit));
    }

    private void updateTimeline(Chit chit) {
        timelines.add(chit);
    }

    private User createUserIfMissingFor(Username username) {
        return users.by(username).orElse(users.add(User.create(username)));
    }
}
