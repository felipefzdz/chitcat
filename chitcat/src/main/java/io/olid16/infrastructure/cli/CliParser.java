package io.olid16.infrastructure.cli;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.olid16.domain.values.Chit.*;
import static io.olid16.domain.values.Username.*;
import static io.olid16.infrastructure.cli.Command.CommandType.*;
import static io.olid16.infrastructure.cli.Command.createCommand;
import static java.time.Instant.now;
import static java.util.regex.Pattern.*;

public class CliParser {

    private static final Pattern createChitPattern = compile("(.+) -> (.+)");
    private static final Pattern followUserPattern = compile("(.+) follows (.+)");
    private static final Pattern readWallPattern = compile("(.+) wall");

    public Command parse(String input) {
        Matcher createChit = createChitPattern.matcher(input);
        if (createChit.matches()){
            return createCommand(CREATE_CHIT).with(createChit(createChit.group(2), now(), createUsername(createChit.group(1))));
        }
        Matcher followUser = followUserPattern.matcher(input);
        if(followUser.matches()){
            return createCommand(FOLLOW_USER).withFollower(createUsername(followUser.group(1))).withFollowee(createUsername(followUser.group(2)));
        }
        Matcher readWall = readWallPattern.matcher(input);
        if (readWall.matches()){
            return createCommand(READ_WALL).withUsername(createUsername(readWall.group(1)));
        }
        return createCommand(READ_TIMELINE).withUsername(createUsername(input));
    }
}
