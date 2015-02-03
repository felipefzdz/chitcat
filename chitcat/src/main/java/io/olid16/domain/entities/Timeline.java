package io.olid16.domain.entities;

import com.google.common.base.Joiner;
import io.olid16.domain.values.Chit;

import java.util.Date;
import java.util.List;

import static io.olid16.infrastructure.utils.TimeFormatter.timeFormatter;
import static java.util.stream.Collectors.toList;

public class Timeline {
    private final List<Chit> chits;

    private Timeline(List<Chit> chits) {
        this.chits = chits;
    }

    public static Timeline createTimeline(List<Chit> chits) {
        return new Timeline(chits);
    }

    public List<String> format() {
        return chits.stream()
                .map(this::formatChit)
                .collect(toList());
    }

    private String formatChit(Chit chit) {
        return Joiner.on(" ").join(chit.text(), String.format("(%s)", elapsedTimeOf(chit)));
    }

    private String elapsedTimeOf(Chit chit) {
        return timeFormatter().format(Date.from(chit.creationInstant()));
    }

    public List<Chit> chits() {
        return chits;
    }

}
