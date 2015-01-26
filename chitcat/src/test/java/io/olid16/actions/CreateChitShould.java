package io.olid16.actions;

import io.olid16.domain.collections.Timelines;
import io.olid16.domain.collections.Users;
import io.olid16.domain.entities.User;
import io.olid16.domain.values.Chit;
import io.olid16.domain.values.Username;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.time.Instant;
import java.util.Optional;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CreateChitShould {

    @Mock Users users;
    @Mock Timelines timelines;

    @Test public void
    create_user_if_it_did_not_exist_previously(){
        given(users.by(any())).willReturn(Optional.empty());
        new CreateChit(users, timelines).with(Username.create("Alice"), null);
        verify(users).add(User.create(Username.create("Alice")));
    }
    
    @Test public void
    add_chit_into_chits_collection(){
        given(users.by(any())).willReturn(Optional.ofNullable(null));
        Chit chit = Chit.create("A chit", Instant.now());
        new CreateChit(users, timelines).with(Username.create("Alice"), chit);
        verify(timelines).add(Username.create("Alice"), chit);
    }
}