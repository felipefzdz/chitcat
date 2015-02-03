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
import static io.olid16.domain.entities.User.createUser;
import static io.olid16.domain.values.Username.createUsername;
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
        given(users.add(any())).willReturn(createUser(createUsername("Alice")));
        new CreateChit(users, timelines, walls).with(aChit().build());
        verify(users).add(createUser(createUsername("Alice")));
    }

    @Test public void
    add_chit_into_timelines(){
        given(users.by(any())).willReturn(of(createUser(createUsername("Alice"))));
        new CreateChit(users, timelines, walls).with(aChit().build());
        verify(timelines).add(aChit().build());
    }

    @Test public void
    add_chit_into_followers_wall(){
        User alice = createUser(createUsername("Alice"));
        alice.addFollower(createUsername("Bob"));
        alice.addFollower(createUsername("Charlie"));
        given(users.by(any())).willReturn(of(alice));
        new CreateChit(users, timelines, walls).with(aChit().build());
        verify(walls).add(createUsername("Bob"), aChit().build());
        verify(walls).add(createUsername("Charlie"), aChit().build());
    }

    @Test public void
    add_chit_into_own_wall(){
        given(users.by(any())).willReturn(of(createUser(createUsername("Alice"))));
        new CreateChit(users, timelines, walls).with(aChit().build());
        verify(walls).add(createUsername("Alice"), aChit().build());
    }
}