package io.olid16.infrastructure.cli;

import io.olid16.actions.CreateChit;
import io.olid16.actions.FollowUser;
import io.olid16.actions.ReadTimeline;
import io.olid16.actions.ReadWall;
import io.olid16.domain.values.Username;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static builders.ChitBuilder.aChit;
import static io.olid16.infrastructure.cli.Command.CommandType.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class PromptShould {

    @Mock CliParser cliParser;
    @Mock CreateChit createChit;
    @Mock FollowUser followUser;
    @Mock ReadTimeline readTimeline;
    @Mock ReadWall readWall;
    @Mock InputReader reader;
    @Mock OutputWriter out;

    @Test public void
    call_create_chit() {
        given(cliParser.parse(any())).willReturn(new Command(CREATE_CHIT).with(aChit().build()));
        new Prompt(cliParser, createChit, followUser, readTimeline, readWall, reader, out).start();
        verify(createChit).with(aChit().build());
    }


    @Test public void
    call_follow_user() {
        given(cliParser.parse(any())).willReturn(new Command(FOLLOW_USER).withFollower(Username.create("Alice")).withFollowee(Username.create("Bob")));
        new Prompt(cliParser, createChit, followUser, readTimeline, readWall, reader, out).start();
        verify(followUser).with(Username.create("Alice"), Username.create("Bob"));
    }

    @Test public void
    call_read_timeline() {
        given(cliParser.parse(any())).willReturn(new Command(READ_TIMELINE).withUsername(Username.create("Alice")));
        given(readTimeline.with(Username.create("Alice"))).willReturn(Optional.empty());
        new Prompt(cliParser, createChit, followUser, readTimeline, readWall, reader, out).start();
        verify(readTimeline).with(Username.create("Alice"));
    }

    @Test public void
    call_read_wall() {
        given(cliParser.parse(any())).willReturn(new Command(READ_WALL).withUsername(Username.create("Alice")));
        given(readWall.with(Username.create("Alice"))).willReturn(Optional.empty());
        new Prompt(cliParser, createChit, followUser, readTimeline, readWall, reader, out).start();
        verify(readWall).with(Username.create("Alice"));
    }
    
}