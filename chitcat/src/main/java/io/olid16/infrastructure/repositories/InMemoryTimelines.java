package io.olid16.infrastructure.repositories;

import com.google.common.collect.LinkedListMultimap;
import io.olid16.domain.collections.Timelines;
import io.olid16.domain.entities.Timeline;
import io.olid16.domain.values.Chit;
import io.olid16.domain.values.Username;

import java.util.Optional;

import static com.google.common.collect.Lists.reverse;
import static io.olid16.domain.entities.Timeline.createTimeline;
import static java.util.Optional.empty;
import static java.util.Optional.of;

public class InMemoryTimelines implements Timelines {
    private LinkedListMultimap<Username, Chit> timelines = LinkedListMultimap.create();

    @Override
    public void add(Chit chit) {
        timelines.put(chit.username(), chit);
    }

    @Override
    public Optional<Timeline> by(Username username) {
        return timelines.containsKey(username) ?
                of(createTimeline(reverse(timelines.get(username)))) :
                empty();
    }
}
