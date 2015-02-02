package io.olid16.actions;

import io.olid16.domain.collections.Timelines;
import io.olid16.domain.collections.Users;
import io.olid16.domain.collections.Walls;
import io.olid16.domain.entities.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static builders.ChitBuilder.aChit;
import static io.olid16.domain.values.Username.create;
import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CreateChitShould {

    @Mock Users users;
    @Mock Timelines timelines;
    @Mock Walls walls;

    @Test public void
    create_user_if_it_did_not_exist_previously(){
        given(users.by(any())).willReturn(empty());
        given(users.add(any())).willReturn(User.create(create("Alice")));
        new CreateChit(users, timelines, walls).with(aChit().build());
        verify(users).add(User.create(create("Alice")));
    }

    @Test public void
    add_chit_into_timelines(){
        given(users.by(any())).willReturn(of(User.create(create("Alice"))));
        new CreateChit(users, timelines, walls).with(aChit().build());
        verify(timelines).add(aChit().build());
    }

    @Test public void
    add_chit_into_followers_wall(){
        User alice = User.create(create("Alice"));
        alice.addFollower(create("Bob"));
        alice.addFollower(create("Charlie"));
        given(users.by(any())).willReturn(of(alice));
        new CreateChit(users, timelines, walls).with(aChit().build());
        verify(walls).add(create("Bob"), aChit().build());
        verify(walls).add(create("Charlie"), aChit().build());
    }

    @Test public void
    add_chit_into_own_wall(){
        given(users.by(any())).willReturn(of(User.create(create("Alice"))));
        new CreateChit(users, timelines, walls).with(aChit().build());
        verify(walls).add(create("Alice"), aChit().build());
    }
}