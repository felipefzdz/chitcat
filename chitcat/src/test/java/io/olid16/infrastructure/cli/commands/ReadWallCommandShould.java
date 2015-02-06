package io.olid16.infrastructure.cli.commands;

import com.google.common.collect.ImmutableSortedSet;
import io.olid16.actions.ReadWall;
import io.olid16.domain.entities.Wall;
import io.olid16.infrastructure.cli.wrappers.OutputWriter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashSet;

import static builders.ChitBuilder.aChit;
import static io.olid16.domain.entities.Timeline.createTimeline;
import static io.olid16.domain.entities.Wall.createWall;
import static io.olid16.domain.values.Username.createUsername;
import static java.util.Arrays.asList;
import static java.util.Optional.of;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ReadWallCommandShould {

    @Mock OutputWriter out;
    @Mock ReadWall readWall;

    @Test public void
    write_in_out_once_when_there_is_one_chit() {
        given(readWall.with(createUsername("Alice"))).willReturn(of(createWall(ImmutableSortedSet.of((aChit().build())))));
        new ReadWallCommand(readWall, out, createUsername("Alice")).execute();
        verify(out).write(anyString());
    }
}