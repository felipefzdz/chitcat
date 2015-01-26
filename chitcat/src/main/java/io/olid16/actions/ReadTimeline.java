package io.olid16.actions;

import com.google.inject.Inject;
import io.olid16.domain.collections.Timelines;
import io.olid16.domain.entities.Timeline;
import io.olid16.domain.values.Username;

import java.util.Optional;

public class ReadTimeline {
    private final Timelines timelines;

    @Inject
    public ReadTimeline(Timelines timelines) {
        this.timelines = timelines;
    }

    public Optional<Timeline> with(Username username) {
        return timelines.by(username);
    }
}
