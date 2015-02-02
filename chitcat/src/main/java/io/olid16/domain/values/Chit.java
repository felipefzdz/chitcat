package io.olid16.domain.values;

import com.google.auto.value.AutoValue;

import java.time.Instant;

@AutoValue
public abstract class Chit implements Comparable{

    public static Chit create(String text, Instant creationInstant, Username username) {
        return new AutoValue_Chit(text, creationInstant, username);
    }

    public abstract String text();

    public abstract Instant creationInstant();

    public abstract Username username();

    @Override
    public int compareTo(Object o) {
        return ((Chit) o).creationInstant().compareTo(creationInstant());
    }
}
