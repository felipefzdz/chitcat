package builders;

import io.olid16.domain.values.Chit;
import io.olid16.domain.values.Username;

import java.time.Instant;

import static java.time.Instant.now;

public class ChitBuilder {
    private String text;
    private Instant creationInstant = now();
    private Username username;
    private static Instant now = now();

    public ChitBuilder(String text, Instant creationInstant, Username username) {
        this.text = text;
        this.creationInstant = creationInstant;
        this.username = username;
    }

    public static ChitBuilder aChit() {
        return new ChitBuilder("A chit", now, Username.create("Alice"));
    }

    public Chit build() {
        return Chit.create(text, creationInstant, username);
    }

    public ChitBuilder w(String text) {
        this.text = text;
        return this;
    }

    public ChitBuilder w(Instant creationInstant) {
        this.creationInstant = creationInstant;
        return this;
    }

    public ChitBuilder w(Username username) {
        this.username = username;
        return this;
    }
}
