package io.olid16.infrastructure.cli.commands;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import io.olid16.actions.FollowUser;
import io.olid16.domain.values.Username;

public class FollowUserCommand implements Command{

    private final FollowUser followUser;
    private final Username follower;
    private final Username followee;

    @Inject
    FollowUserCommand(FollowUser followUser,
                      @Assisted("follower") Username follower,
                      @Assisted("followee") Username followee) {
        this.followUser = followUser;
        this.follower = follower;
        this.followee = followee;
    }


    @Override
    public void execute() {
        followUser.with(follower, followee);
    }

    public static interface FollowUserCommandFactory {
        public FollowUserCommand create(@Assisted("follower") Username follower, @Assisted("followee") Username followee);
    }
}
