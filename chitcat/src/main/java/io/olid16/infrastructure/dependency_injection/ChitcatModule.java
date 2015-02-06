package io.olid16.infrastructure.dependency_injection;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import io.olid16.domain.collections.Timelines;
import io.olid16.domain.collections.Users;
import io.olid16.domain.collections.Walls;
import io.olid16.infrastructure.cli.commands.*;
import io.olid16.infrastructure.cli.commands.FollowUserCommand.FollowUserCommandFactory;
import io.olid16.infrastructure.repositories.InMemoryTimelines;
import io.olid16.infrastructure.repositories.InMemoryUsers;
import io.olid16.infrastructure.repositories.InMemoryWalls;

import javax.inject.Singleton;

import static io.olid16.infrastructure.cli.commands.CreateChitCommand.*;
import static io.olid16.infrastructure.cli.commands.ReadTimelineCommand.*;
import static io.olid16.infrastructure.cli.commands.ReadWallCommand.*;

public class ChitcatModule extends AbstractModule{
    private final FactoryModuleBuilder factoryModuleBuilder = new FactoryModuleBuilder();

    @Override
    protected void configure() {
        bind(Users.class).to(InMemoryUsers.class).in(Singleton.class);
        bind(Timelines.class).to(InMemoryTimelines.class).in(Singleton.class);
        bind(Walls.class).to(InMemoryWalls.class).in(Singleton.class);
        install(CreateChitCommandFactory.class,
                FollowUserCommandFactory.class,
                ReadWallCommandFactory.class,
                ReadTimelineCommandFactory.class);
    }

    private void install(Class... clazzes) {
        for(Class clazz: clazzes){
            install(clazz);
        }
    }

    private void install(Class clazz) {
        install(factoryModuleBuilder.build(clazz));
    }
}
