package io.olid16.domain.entities;

import com.google.common.base.Joiner;
import io.olid16.domain.values.Chit;
import io.olid16.domain.values.Username;

import java.util.Date;
import java.util.List;
import java.util.NavigableSet;

import static io.olid16.infrastructure.utils.TimeFormatter.timeFormatter;
import static java.util.stream.Collectors.toList;

public class Wall {
    private final Username username;
    private final NavigableSet<Chit> chits;

    public Wall(Username username, NavigableSet<Chit> chits) {
        this.username = username;
        this.chits = chits;
    }

    public List<String> formatWithCreationInstant() {
        return chits.stream()
                .map(this::formatChit)
                .collect(toList());
    }

    private String formatChit(Chit chit) {
        return Joiner.on(" ").
                join(chit.username().value(),
                        "-",
                        chit.text(),
                        String.format("(%s)", elapsedTimeOf(chit)));
    }

    private String elapsedTimeOf(Chit chit) {
        return timeFormatter().format(Date.from(chit.creationInstant()));
    }

    public static Wall create(Username username, NavigableSet<Chit> chits) {
        return new Wall(username, chits);
    }

    public NavigableSet<Chit> chits() {
        return chits;
    }
}
