package io.olid16.actions;

import io.olid16.domain.collections.Timelines;
import io.olid16.domain.entities.Timeline;
import io.olid16.domain.values.Username;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static com.google.common.truth.Truth.assertThat;
import static io.olid16.domain.entities.Timeline.createTimeline;
import static io.olid16.domain.values.Username.*;
import static java.util.Optional.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;

@RunWith(MockitoJUnitRunner.class)
public class ReadTimelineShould {

    @Mock Timelines timelines;

    @Test public void
    return_timeline_when_exists(){
        given(timelines.by(any())).willReturn(of(createTimeline(null)));
        Optional<Timeline> timeline = new ReadTimeline(timelines).with(createUsername("Alice"));
        assertThat(timeline.isPresent());
    }
}