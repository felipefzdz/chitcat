package io.olid16.actions;

import io.olid16.domain.collections.Walls;
import io.olid16.domain.entities.Wall;
import io.olid16.domain.values.Username;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static com.google.common.truth.Truth.assertThat;
import static io.olid16.domain.entities.Wall.createWall;
import static io.olid16.domain.values.Username.createUsername;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;

@RunWith(MockitoJUnitRunner.class)
public class ReadWallShould {

    @Mock Walls walls;

    @Test public void
    return_walls_when_exists(){
        given(walls.by(any())).willReturn(Optional.of(createWall(null)));
        Optional<Wall> wall = new ReadWall(walls).with(createUsername("Alice"));
        assertThat(wall.isPresent());
    }
}