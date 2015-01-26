package io.olid16.domain.entities;

import com.google.common.base.Joiner;
import io.olid16.domain.values.Chit;
import io.olid16.domain.values.Username;
import org.joda.time.Period;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

import java.util.List;

import static java.time.Instant.now;
import static java.util.stream.Collectors.toList;

public class Timeline {
    private final Username username;
    private final List<Chit> chits;

    private Timeline(Username username, List<Chit> chits) {
        this.username = username;
        this.chits = chits;
    }

    public static Timeline create(Username username, List<Chit> chits) {
        return new Timeline(username, chits);
    }

    public List<String> formatWithCreationInstant() {
        return chits.stream()
                .map(this::formatChit)
                .collect(toList());
    }

    private String formatChit(Chit chit) {
        return Joiner.on(" ").
                join(chit.text(),
                        String.format("(%s ago)", elapsedTimeOf(chit)));
    }

    private String elapsedTimeOf(Chit chit) {
        Period period = new Period(chit.creationInstant().toEpochMilli(), now().toEpochMilli());
        return formatter().print(period);
    }

    private PeriodFormatter formatter() {
        return new PeriodFormatterBuilder()
                    .appendSeconds().appendSuffix(" second", " seconds")
                    .appendMinutes().appendSuffix(" minute", " minutes")
                    .appendHours().appendSuffix(" hour", " hours")
                    .appendDays().appendSuffix(" day", " days")
                    .appendWeeks().appendSuffix(" week", " weeks")
                    .appendMonths().appendSuffix(" month", " months")
                    .appendYears().appendSuffix(" year", " years")
                    .printZeroNever()
                    .toFormatter();
    }

    public List<Chit> chits() {
        return chits;
    }
}
