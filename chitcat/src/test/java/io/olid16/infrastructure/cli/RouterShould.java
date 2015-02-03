package io.olid16.infrastructure.cli;

import io.olid16.actions.CreateChit;
import io.olid16.actions.FollowUser;
import io.olid16.actions.ReadTimeline;
import io.olid16.actions.ReadWall;
import io.olid16.infrastructure.cli.wrappers.InputReader;
import io.olid16.infrastructure.cli.wrappers.OutputWriter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static builders.ChitBuilder.aChit;
import static io.olid16.domain.values.Username.createUsername;
import static io.olid16.infrastructure.cli.Command.CommandType.*;
import static io.olid16.infrastructure.cli.Command.createCommand;
import static java.util.Optional.empty;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class RouterShould {

    @Mock CliParser cliParser;
    @Mock CreateChit createChit;
    @Mock FollowUser followUser;
    @Mock ReadTimeline readTimeline;
    @Mock ReadWall readWall;
    @Mock InputReader reader;
    @Mock OutputWriter out;

    @Test public void
    call_create_chit() {
        given(cliParser.parse(any())).willReturn(createCommand(CREATE_CHIT).with(aChit().build()));
        new Router(cliParser, createChit, followUser, readTimeline, readWall, reader, out).route();
        verify(createChit).with(aChit().build());
    }


    @Test public void
    call_follow_user() {
        given(cliParser.parse(any())).willReturn(createCommand(FOLLOW_USER).withFollower(createUsername("Alice")).withFollowee(createUsername("Bob")));
        new Router(cliParser, createChit, followUser, readTimeline, readWall, reader, out).route();
        verify(followUser).with(createUsername("Alice"), createUsername("Bob"));
    }

    @Test public void
    call_read_timeline() {
        given(cliParser.parse(any())).willReturn(createCommand(READ_TIMELINE).withUsername(createUsername("Alice")));
        given(readTimeline.with(createUsername("Alice"))).willReturn(empty());
        new Router(cliParser, createChit, followUser, readTimeline, readWall, reader, out).route();
        verify(readTimeline).with(createUsername("Alice"));
    }

    @Test public void
    call_read_wall() {
        given(cliParser.parse(any())).willReturn(createCommand(READ_WALL).withUsername(createUsername("Alice")));
        given(readWall.with(createUsername("Alice"))).willReturn(empty());
        new Router(cliParser, createChit, followUser, readTimeline, readWall, reader, out).route();
        verify(readWall).with(createUsername("Alice"));
    }
    
}