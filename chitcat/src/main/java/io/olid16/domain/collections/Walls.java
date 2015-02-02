package io.olid16.domain.collections;

import io.olid16.domain.entities.Wall;
import io.olid16.domain.values.Chit;
import io.olid16.domain.values.Username;

import java.util.List;
import java.util.Optional;

public interface Walls {
    Optional<Wall> by(Username username);

    void add(Username username, Chit chit);

    void add(Username username, List<Chit> chits);
}
