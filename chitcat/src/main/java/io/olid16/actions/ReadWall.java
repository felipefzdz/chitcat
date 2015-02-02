package io.olid16.actions;

import com.google.inject.Inject;
import io.olid16.domain.collections.Walls;
import io.olid16.domain.entities.Wall;
import io.olid16.domain.values.Username;

import java.util.Optional;

public class ReadWall {
    private final Walls walls;

    @Inject
    public ReadWall(Walls walls) {
        this.walls = walls;
    }

    public Optional<Wall> with(Username username) {
        return walls.by(username);
    }
}
