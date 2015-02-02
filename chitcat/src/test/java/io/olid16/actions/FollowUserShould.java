package io.olid16.actions;

import io.olid16.domain.collections.Timelines;
import io.olid16.domain.collections.Users;
import io.olid16.domain.collections.Walls;
import io.olid16.domain.entities.Timeline;
import io.olid16.domain.entities.User;
import io.olid16.domain.values.Chit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static builders.ChitBuilder.aChit;
import static io.olid16.domain.values.Username.create;
import static java.time.Instant.now;
import static java.util.Arrays.asList;
import static java.util.Optional.empty;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static utils.Assert.assertThrows;

@RunWith(MockitoJUnitRunner.class)
public class FollowUserShould {

    @Mock User user;
    @Mock Users users;
    @Mock Timelines timelines;
    @Mock Walls walls;

    @Test public void
    add_follower_into_followee() {
        given(users.by(any())).willReturn(Optional.of(user));
        given(timelines.by(any())).willReturn(empty());
        new FollowUser(users, timelines, walls).with(create("Alice"), null);
        verify(user).addFollower(create("Alice"));
    }

    @Test public void
    throw_illegal_argument_exception_when_followee_not_exists(){
        given(users.by(any())).willReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> new FollowUser(users, timelines, walls).with(null, null));
    }

    @Test public void
    update_follower_wall_with_followee_timeline(){
        List<Chit> chits = asList(aChit().build(), aChit().w(now()).build());

        given(users.by(any())).willReturn(Optional.of(user));
        given(timelines.by(any())).willReturn(Optional.of(Timeline.create(chits)));

        new FollowUser(users, timelines, walls).with(create("Alice"), null);
        verify(walls).add(create("Alice"), chits);
    }
}