package io.olid16.infrastructure.cli.commands;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import io.olid16.actions.ReadWall;
import io.olid16.domain.values.Username;
import io.olid16.infrastructure.cli.wrappers.OutputWriter;

public class ReadWallCommand implements Command{

    private final ReadWall readWall;
    private final Username username;
    private final OutputWriter out;

    @Inject
    ReadWallCommand(ReadWall readWall,
                    OutputWriter out,
                    @Assisted Username username) {
        this.readWall = readWall;
        this.username = username;
        this.out = out;
    }


    @Override
    public void execute() {
        readWall.with(username).
                ifPresent(wall -> wall.format().forEach(out::write));
    }

    public static interface ReadWallCommandFactory {
        public ReadWallCommand create(Username username);
    }
}
