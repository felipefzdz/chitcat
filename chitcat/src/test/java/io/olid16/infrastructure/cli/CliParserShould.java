package io.olid16.infrastructure.cli;

import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;
import static io.olid16.domain.values.Username.createUsername;
import static io.olid16.infrastructure.cli.Command.CommandType.*;

public class CliParserShould {
    
    @Test public void
    return_create_chit_type() {
        Command command = new CliParser().parse("Alice -> I love the weather today");
        assertThat(command.type()).isEqualTo(CREATE_CHIT);
    }
    
    @Test public void
    return_chit_when_create_chit_command() {
        Command command = new CliParser().parse("Alice -> A chit");
        assertThat(command.chit().text()).isEqualTo("A chit");
        assertThat(command.chit().username()).isEqualTo(createUsername("Alice"));
    }
    
    @Test public void
    return_follow_user_type(){
        Command command = new CliParser().parse("Alice follows Bob");
        assertThat(command.type()).isEqualTo(FOLLOW_USER);
    }
    
    @Test public void
    return_follower_and_followee_when_follow_user_command() {
        Command command = new CliParser().parse("Alice follows Bob");
        assertThat(command.follower()).isEqualTo(createUsername("Alice"));
        assertThat(command.followee()).isEqualTo(createUsername("Bob"));
    }

    @Test public void
    return_read_wall_type(){
        Command command = new CliParser().parse("Charlie wall");
        assertThat(command.type()).isEqualTo(READ_WALL);
    }
    
    @Test public void
    return_username_when_read_wall(){
        Command command = new CliParser().parse("Charlie wall");
        assertThat(command.username()).isEqualTo(createUsername("Charlie"));
    }

    @Test public void
    return_read_timeline_type(){
        Command command = new CliParser().parse("Alice");
        assertThat(command.type()).isEqualTo(READ_TIMELINE);
    }

    @Test public void
    return_username_when_read_timeline(){
        Command command = new CliParser().parse("Charlie");
        assertThat(command.username()).isEqualTo(createUsername("Charlie"));
    }

}