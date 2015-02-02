package io.olid16.infrastructure.utils;

import org.ocpsoft.prettytime.PrettyTime;
import org.ocpsoft.prettytime.units.JustNow;
import org.ocpsoft.prettytime.units.Millisecond;

import java.util.Date;

public class TimeFormatter {

    private final PrettyTime p = new PrettyTime();

    private TimeFormatter() {
        p.removeUnit(JustNow.class);
        p.removeUnit(Millisecond.class);
    }

    private static TimeFormatter instance = new TimeFormatter();

    public static TimeFormatter timeFormatter() {
        return instance;
    }

    public String format(Date date) {
        return p.format(date);
    }
}
