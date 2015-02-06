package io.olid16.infrastructure.cli;

import com.google.inject.Inject;
import io.olid16.infrastructure.cli.commands.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.olid16.domain.values.Chit.createChit;
import static io.olid16.domain.values.Username.createUsername;
import static java.time.Instant.now;
import static java.util.regex.Pattern.compile;

public class CliParser {

    private static final Pattern createChitPattern = compile("(.+) -> (.+)");
    private static final Pattern followUserPattern = compile("(.+) follows (.+)");
    private static final Pattern readWallPattern = compile("(.+) wall");

    private final CreateChitCommand.CreateChitCommandFactory createChitCommandFactory;
    private final FollowUserCommand.FollowUserCommandFactory followUserCommandFactory;
    private final ReadWallCommand.ReadWallCommandFactory readWallCommandFactory;
    private final ReadTimelineCommand.ReadTimelineCommandFactory readTimelineCommandFactory;

    @Inject
    public CliParser(CreateChitCommand.CreateChitCommandFactory createChitCommandFactory,
                     FollowUserCommand.FollowUserCommandFactory followUserCommandFactory,
                     ReadWallCommand.ReadWallCommandFactory readWallCommandFactory,
                     ReadTimelineCommand.ReadTimelineCommandFactory readTimelineCommandFactory) {
        this.createChitCommandFactory = createChitCommandFactory;
        this.followUserCommandFactory = followUserCommandFactory;
        this.readWallCommandFactory = readWallCommandFactory;
        this.readTimelineCommandFactory = readTimelineCommandFactory;
    }

    public Command parse(String input) {
        Matcher createChit = createChitPattern.matcher(input);
        if (createChit.matches()) {
            return createChitCommandFactory.create(createChit(createChit.group(2), now(), createUsername(createChit.group(1))));
        }
        Matcher followUser = followUserPattern.matcher(input);
        if(followUser.matches()) {
            return followUserCommandFactory.create(createUsername(followUser.group(1)), createUsername(followUser.group(2)));
        }
        Matcher readWall = readWallPattern.matcher(input);
        if (readWall.matches()){
            return readWallCommandFactory.create(createUsername(readWall.group(1)));
        }
        return readTimelineCommandFactory.create(createUsername(input));
    }
}
