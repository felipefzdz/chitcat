package io.olid16.infrastructure.cli;

import io.olid16.domain.values.Chit;
import io.olid16.domain.values.Username;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.olid16.infrastructure.cli.Command.CommandType.*;
import static java.time.Instant.now;
import static java.util.regex.Pattern.*;

public class CliParser {

    private final Pattern createChitPattern = compile("(.+) -> (.+)");
    private final Pattern followUserPattern = compile("(.+) follows (.+)");
    private final Pattern readWallPattern = compile("(.+) wall");

    public Command parse(String input) {
        Matcher createChit = createChitPattern.matcher(input);
        if (createChit.matches()){
            return new Command(CREATE_CHIT).with(Chit.create(createChit.group(2), now(), Username.create(createChit.group(1))));
        }
        Matcher followUser = followUserPattern.matcher(input);
        if(followUser.matches()){
            return new Command(FOLLOW_USER).withFollower(Username.create(followUser.group(1))).withFollowee(Username.create(followUser.group(2)));
        }
        Matcher readWall = readWallPattern.matcher(input);
        if (readWall.matches()){
            return new Command(READ_WALL).withUsername(Username.create(readWall.group(1)));
        }
        
        return new Command(READ_TIMELINE).withUsername(Username.create(input));
    }
}
