package io.olid16.domain.values;

import com.google.auto.value.AutoValue;

import java.time.Instant;

@AutoValue
public abstract class Chit {
    
    public static Chit create(String text, Instant creationInstant) {
        return new AutoValue_Chit(text, creationInstant);
    }
    
    public abstract String text();

    public abstract Instant creationInstant();
    
}
