package io.olid16.infrastructure.cli;

import io.olid16.infrastructure.cli.commands.Command;
import io.olid16.infrastructure.cli.wrappers.InputReader;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RouterShould {

    @Mock Command command;
    @Mock InputReader reader;
    @Mock CliParser cliParser;

    @Test public void
    execute_command() {
        when(cliParser.parse(any())).thenReturn(command);
        new Router(cliParser, reader).route();
        verify(command).execute();
    }

}