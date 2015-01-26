package io.olid16.infrastructure.repositories;

import io.olid16.domain.entities.Timeline;
import io.olid16.domain.values.Chit;
import io.olid16.domain.values.Username;
import org.junit.Test;

import java.util.Optional;

import static com.google.common.truth.Truth.assertThat;
import static java.time.Instant.now;

public class InMemoryTimelinesShould {
    
    @Test public void
    return_a_timeline_with_a_chit_when_has_been_previously_inserted() {
        InMemoryTimelines inMemoryTimelines = new InMemoryTimelines();
        Chit chit = Chit.create("A Chit", now());
        inMemoryTimelines.add(Username.create("Alice"), chit);
        Optional<Timeline> timeline = inMemoryTimelines.by(Username.create("Alice"));
        assertThat(timeline.get().chits()).contains(chit);
    }

    @Test public void
    return_empty_when_has_not_been_previously_inserted(){
        Optional<Timeline> timeline = new InMemoryTimelines().by(Username.create("Alice"));
        assertThat(timeline.isPresent()).isFalse();
    }

    @Test public void
    return_a_timeline_with_several_chits() {
        InMemoryTimelines inMemoryTimelines = new InMemoryTimelines();
        inMemoryTimelines.add(Username.create("Alice"), Chit.create("A Chit", now()));
        inMemoryTimelines.add(Username.create("Alice"), Chit.create("Another Chit", now()));
        inMemoryTimelines.add(Username.create("Bob"), Chit.create("A Chit", now()));
        Optional<Timeline> timeline = inMemoryTimelines.by(Username.create("Alice"));
        assertThat(timeline.get().chits().size()).is(2);
    }


}