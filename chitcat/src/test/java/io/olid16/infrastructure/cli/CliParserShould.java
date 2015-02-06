package io.olid16.infrastructure.cli;

import io.olid16.domain.values.Chit;
import io.olid16.infrastructure.cli.commands.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static io.olid16.domain.values.Username.createUsername;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CliParserShould {

    @Mock
    CreateChitCommand.CreateChitCommandFactory createChitCommandFactory;
    @Mock
    FollowUserCommand.FollowUserCommandFactory followUserCommandFactory;
    @Mock
    ReadWallCommand.ReadWallCommandFactory readWallCommandFactory;
    @Mock
    ReadTimelineCommand.ReadTimelineCommandFactory readTimelineCommandFactory;

    @Test public void
    return_create_chit_command(){
        new CliParser(createChitCommandFactory, followUserCommandFactory, readWallCommandFactory, readTimelineCommandFactory).parse("Alice -> A chit");
        verify(createChitCommandFactory).create(any(Chit.class));
    }

    @Test public void
    return_follow_user_command(){
        new CliParser(createChitCommandFactory, followUserCommandFactory, readWallCommandFactory, readTimelineCommandFactory).parse("Alice follows Bob");
        verify(followUserCommandFactory).create(createUsername("Alice"), createUsername("Bob"));
    }


    @Test public void
    return_read_wall_command(){
        new CliParser(createChitCommandFactory, followUserCommandFactory, readWallCommandFactory, readTimelineCommandFactory).parse("Charlie wall");
        verify(readWallCommandFactory).create(createUsername("Charlie"));
    }

    @Test public void
    return_read_timeline_command(){
        new CliParser(createChitCommandFactory, followUserCommandFactory, readWallCommandFactory, readTimelineCommandFactory).parse("Charlie");
        verify(readTimelineCommandFactory).create(createUsername("Charlie"));
    }

}