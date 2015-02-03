package io.olid16.domain.entities;

import io.olid16.domain.values.Username;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class User {
    private final Username username;
    private Set<Username> followers = new HashSet<>();

    private User(Username username) {
        this.username = username;
    }

    public static User createUser(Username username) {
        return new User(username);
    }

    public Username username() {
        return username;
    }

    public void addFollower(Username follower) {
        followers.add(follower);
    }

    public Set<Username> followers() {
        return followers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return Objects.equals(username, ((User) o).username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
}
