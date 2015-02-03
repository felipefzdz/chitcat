package io.olid16.infrastructure.cli;

import io.olid16.domain.values.Chit;
import io.olid16.domain.values.Username;

public class Command {
    private final CommandType type;
    private Chit chit;
    private Username follower;
    private Username followee;
    private Username username;

    private Command(CommandType type) {
        this.type = type;
    }

    public static Command createCommand(CommandType type) {
        return new Command(type);
    }

    public CommandType type() {
        return type;
    }

    public Chit chit() {
        return chit;
    }

    public Username follower() {
        return follower;
    }

    public Username followee() {
        return followee;
    }

    public Username username() {
        return username;
    }

    public Command with(Chit chit) {
        this.chit = chit;
        return this;
    }

    public Command withFollower(Username follower) {
        this.follower = follower;
        return this;
    }

    public Command withFollowee(Username followee) {
        this.followee = followee;
        return this;
    }

    public Command withUsername(Username username) {
        this.username = username;
        return this;
    }

    public static enum CommandType {
        CREATE_CHIT, FOLLOW_USER, READ_TIMELINE, READ_WALL
    }
}
