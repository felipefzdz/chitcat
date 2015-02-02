package io.olid16.infrastructure.repositories;

import io.olid16.domain.entities.Wall;
import io.olid16.domain.values.Chit;

import java.util.Arrays;
import java.util.Optional;

import static builders.ChitBuilder.aChit;
import static com.google.common.truth.Truth.assertThat;
import static io.olid16.domain.values.Username.create;
import static java.time.Instant.now;
import static java.time.temporal.ChronoUnit.DAYS;
import static java.util.Arrays.*;

public class InMemoryWallsShould {

    @org.junit.Test public void
    return_a_wall_with_a_chit_when_has_been_previously_inserted() {
        InMemoryWalls inMemoryWalls = new InMemoryWalls();
        Chit chit = aChit().build();
        inMemoryWalls.add(create("Alice"), chit);
        Optional<Wall> wall = inMemoryWalls.by(create("Alice"));
        assertThat(wall.get().chits()).contains(chit);
    }

    @org.junit.Test public void
    return_empty_when_has_not_been_previously_inserted(){
        Optional<Wall> wall = new InMemoryWalls().by(create("Alice"));
        assertThat(wall.isPresent()).isFalse();
    }

    @org.junit.Test public void
    return_a_timeline_with_several_chits_in_creation_time_order() {
        Chit aliceTenDaysAgo = aChit().w(create("Alice")).w(now().minus(10, DAYS)).w("Ten days ago").build();
        Chit aliceNow = aChit().w(create("Alice")).w("Now").build();
        Chit alice15DaysAgo = aChit().w(create("Alice")).w(now().minus(15, DAYS)).w("Fifteen days ago").build();

        InMemoryWalls inMemoryWalls = new InMemoryWalls();
        inMemoryWalls.add(create("Alice"), aliceTenDaysAgo);
        inMemoryWalls.add(create("Bob"), aChit().w(create("Bob")).build());
        inMemoryWalls.add(create("Alice"), asList(aliceNow, alice15DaysAgo));

        Optional<Wall> wall = inMemoryWalls.by(create("Alice"));
        assertThat(wall.get().chits()).containsExactly(aliceNow, aliceTenDaysAgo, alice15DaysAgo).inOrder();
    }

}