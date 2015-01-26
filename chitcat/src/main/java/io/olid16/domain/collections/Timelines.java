package io.olid16.domain.collections;

import io.olid16.domain.entities.Timeline;
import io.olid16.domain.values.Chit;
import io.olid16.domain.values.Username;

import java.util.Optional;

public interface Timelines {
    void add(Username username, Chit chit);

    Optional<Timeline> by(Username username);
}
