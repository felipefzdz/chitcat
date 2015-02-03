package io.olid16.infrastructure.repositories;

import com.google.common.collect.TreeMultimap;
import io.olid16.domain.collections.Walls;
import io.olid16.domain.entities.Wall;
import io.olid16.domain.values.Chit;
import io.olid16.domain.values.Username;

import java.util.List;
import java.util.Optional;

import static io.olid16.domain.entities.Wall.*;
import static java.util.Optional.empty;
import static java.util.Optional.of;

public class InMemoryWalls implements Walls {

    TreeMultimap<Username, Chit> walls = TreeMultimap.create();

    @Override
    public Optional<Wall> by(Username username) {
        return walls.containsKey(username) ?
                of(createWall(walls.get(username))) :
                empty();
    }

    @Override
    public void add(Username username, Chit chit) {
        walls.put(username, chit);
    }

    @Override
    public void add(Username username, List<Chit> chits) {
        walls.putAll(username, chits);
    }
}
