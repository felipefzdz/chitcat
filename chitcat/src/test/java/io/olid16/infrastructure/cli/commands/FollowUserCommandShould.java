package io.olid16.infrastructure.cli.commands;

import io.olid16.actions.FollowUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static io.olid16.domain.values.Username.createUsername;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class FollowUserCommandShould {

    @Mock FollowUser followUser;

    @Test public void
    call_create_chit(){
        new FollowUserCommand(followUser, createUsername("Alice"), createUsername("Bob")).execute();
        verify(followUser).with(createUsername("Alice"), createUsername("Bob"));
    }
}