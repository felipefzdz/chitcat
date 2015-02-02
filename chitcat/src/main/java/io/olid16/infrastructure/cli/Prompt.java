package io.olid16.infrastructure.cli;

import com.google.inject.Inject;
import io.olid16.actions.CreateChit;
import io.olid16.actions.FollowUser;
import io.olid16.actions.ReadTimeline;
import io.olid16.actions.ReadWall;

public class Prompt {

    private final CliParser cliParser;
    private final CreateChit createChit;
    private final FollowUser followUser;
    private final ReadTimeline readTimeline;
    private final ReadWall readWall;
    private final InputReader reader;
    private final OutputWriter out;

    @Inject
    public Prompt(CliParser cliParser,
                  CreateChit createChit,
                  FollowUser followUser,
                  ReadTimeline readTimeline,
                  ReadWall readWall, 
                  InputReader reader, 
                  OutputWriter out) {
        this.cliParser = cliParser;
        this.createChit = createChit;
        this.followUser = followUser;
        this.readTimeline = readTimeline;
        this.readWall = readWall;
        this.reader = reader;
        this.out = out;
    }

    public void start() {
        Command command = cliParser.parse(reader.next());
        switch (command.type()){
            case CREATE_CHIT: createChit.with(command.chit());
                break;
            case FOLLOW_USER: followUser.with(command.follower(), command.followee());
                break;
            case READ_TIMELINE:
                readTimeline.with(command.username()).
                        ifPresent(timeline -> timeline.formatWithCreationInstant().forEach(out::write));
                break;
            case READ_WALL:
                readWall.with(command.username()).
                        ifPresent(wall -> wall.formatWithCreationInstant().forEach(out::write));
                break;
        }
    }
}
