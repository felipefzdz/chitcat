package io.olid16.domain.entities;

import io.olid16.domain.values.Chit;
import org.junit.Test;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.truth.Truth.assertThat;
import static java.time.Instant.now;
import static java.time.temporal.ChronoUnit.HOURS;
import static java.time.temporal.ChronoUnit.MINUTES;
import static java.time.temporal.ChronoUnit.SECONDS;
import static java.util.Arrays.asList;

public class TimelineShould {
    
    @Test public void
    format_chits_with_creation_instant_in_order(){
        List<String> formattedTimeline = Timeline.create(null,
                asList(Chit.create("FirstAliceChit", now().minus(15, MINUTES)), 
                        Chit.create("SecondAliceChit", now().minus(10, SECONDS)))).
                formatWithCreationInstant();
        assertThat(formattedTimeline).containsSequence(asList("FirstAliceChit (15 minutes ago)", "SecondAliceChit (10 seconds ago)"));
    }
    
    @Test public void
    return_empty_list_when_there_are_not_chits(){
        List<String> formattedTimeline = Timeline.create(null, newArrayList()).formatWithCreationInstant();
        assertThat(formattedTimeline).isEmpty();
    }

    @Test public void
    format_chit_in_singular(){
        List<String> formattedTimeline = Timeline.create(null,
                asList(Chit.create("FirstAliceChit", now().minus(1, HOURS)))).
                formatWithCreationInstant();
        assertThat(formattedTimeline).containsSequence(asList("FirstAliceChit (1 hour ago)"));
    }

}