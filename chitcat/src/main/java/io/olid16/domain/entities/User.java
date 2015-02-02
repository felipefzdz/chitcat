package io.olid16.domain.entities;

import io.olid16.domain.values.Username;

import java.util.HashSet;
import java.util.Set;

public class User {
    private final Username username;
    private Set<Username> followers = new HashSet<>();

    private User(Username username) {
        this.username = username;
    }

    public static User create(Username username) {
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

        User user = (User) o;

        if (!username.equals(user.username)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return username.hashCode();
    }
}
