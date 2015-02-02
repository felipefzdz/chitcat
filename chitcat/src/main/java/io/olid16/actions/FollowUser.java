package io.olid16.actions;

import com.google.inject.Inject;
import io.olid16.domain.collections.Timelines;
import io.olid16.domain.collections.Users;
import io.olid16.domain.collections.Walls;
import io.olid16.domain.values.Username;

public class FollowUser {
    private final Users users;
    private final Timelines timelines;
    private final Walls walls;

    @Inject
    public FollowUser(Users users, Timelines timelines, Walls walls) {
        this.users = users;
        this.timelines = timelines;
        this.walls = walls;
    }

    public void with(Username follower, Username followeeUsername) {
        follow(follower, followeeUsername);
        updateFollowerWall(follower, followeeUsername);
    }

    private void follow(Username follower, Username followeeUsername) {
        users.by(followeeUsername).orElseThrow(IllegalArgumentException::new).addFollower(follower);
    }

    private void updateFollowerWall(Username follower, Username followeeUsername) {
        timelines.by(followeeUsername).ifPresent(x -> walls.add(follower, x.chits()));
    }
}
