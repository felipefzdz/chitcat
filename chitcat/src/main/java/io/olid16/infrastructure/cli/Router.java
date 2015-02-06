package io.olid16.infrastructure.cli;

import com.google.inject.Inject;
import io.olid16.infrastructure.cli.commands.Command;
import io.olid16.infrastructure.cli.wrappers.InputReader;

public class Router {

    private final CliParser cliParser;
    private final InputReader reader;

    @Inject
    public Router(CliParser cliParser,
                  InputReader reader) {
        this.cliParser = cliParser;
        this.reader = reader;
    }

    public void route() {
        Command command = cliParser.parse(reader.read());
        command.execute();
    }
}
