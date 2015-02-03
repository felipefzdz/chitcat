package io.olid16.domain.entities;

import org.junit.Test;

import java.util.List;

import static builders.ChitBuilder.aChit;
import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.truth.Truth.assertThat;
import static io.olid16.domain.entities.Timeline.*;
import static java.time.Instant.now;
import static java.time.temporal.ChronoUnit.*;
import static java.util.Arrays.asList;

public class TimelineShould {

    @Test public void
    format_chits_with_creation_instant_in_order(){
        List<String> formattedTimeline = createTimeline(
                asList(aChit().w("FirstAliceChit").w(now().minus(15, MINUTES).minus(10, SECONDS)).build(),
                        aChit().w("SecondAliceChit").w(now().minus(10, SECONDS)).build())).
                format();
        assertThat(formattedTimeline).containsSequence(asList("FirstAliceChit (15 minutes ago)", "SecondAliceChit (10 seconds ago)"));
    }

    @Test public void
    return_empty_list_when_there_are_not_chits(){
        List<String> formattedTimeline = createTimeline(newArrayList()).format();
        assertThat(formattedTimeline).isEmpty();
    }

    @Test public void
    format_chit_in_singular(){
        List<String> formattedTimeline = createTimeline(
                asList(aChit().w("FirstAliceChit").w(now().minus(1, HOURS)).build())).
                format();
        assertThat(formattedTimeline).containsSequence(asList("FirstAliceChit (1 hour ago)"));
    }

}