package io.olid16.infrastructure.repositories;

import io.olid16.domain.entities.Timeline;
import io.olid16.domain.values.Chit;
import io.olid16.domain.values.Username;
import org.junit.Test;

import java.util.Optional;

import static builders.ChitBuilder.aChit;
import static com.google.common.truth.Truth.assertThat;
import static io.olid16.domain.values.Username.*;

public class InMemoryTimelinesShould {

    @Test public void
    return_a_timeline_with_a_chit_when_has_been_previously_inserted() {
        InMemoryTimelines inMemoryTimelines = new InMemoryTimelines();
        inMemoryTimelines.add(aChit().build());
        Optional<Timeline> timeline = inMemoryTimelines.by(createUsername("Alice"));
        assertThat(timeline.get().chits()).contains(aChit().build());
    }

    @Test public void
    return_empty_when_has_not_been_previously_inserted(){
        Optional<Timeline> timeline = new InMemoryTimelines().by(createUsername("Alice"));
        assertThat(timeline.isPresent()).isFalse();
    }

    @Test public void
    return_a_timeline_with_several_chits() {
        InMemoryTimelines inMemoryTimelines = new InMemoryTimelines();
        inMemoryTimelines.add(aChit().w(createUsername("Alice")).build());
        inMemoryTimelines.add(aChit().w(createUsername("Bob")).build());
        inMemoryTimelines.add(aChit().w(createUsername("Alice")).build());
        Optional<Timeline> timeline = inMemoryTimelines.by(createUsername("Alice"));
        assertThat(timeline.get().chits().size()).is(2);
    }


}