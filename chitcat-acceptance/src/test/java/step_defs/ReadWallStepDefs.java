package step_defs;

import com.google.inject.Inject;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.olid16.actions.FollowUser;
import io.olid16.actions.ReadWall;
import io.olid16.domain.entities.Wall;
import io.olid16.domain.values.Username;

import java.util.List;

import static com.google.common.truth.Truth.assertThat;

public class ReadWallStepDefs {

    private final FollowUser followUser;
    private final ReadWall readWall;
    private static Wall wall;

    @Inject
    public ReadWallStepDefs(FollowUser followUser, ReadWall readWall) {
        this.followUser = followUser;
        this.readWall = readWall;
    }

    @And("^([^ ]+) follows ([^ ]+)$")
    public void follower_follows_followee(String follower, String followee) throws Throwable {
        followUser.with(Username.create(follower), Username.create(followee));
    }

    @When("^a user reads ([^ ]+) wall$")
    public void a_user_reads_user_wall(String username) throws Throwable {
        wall = readWall.with(Username.create(username)).get();
    }

    @Then("^wall should contain \"(.+)\"$")
    public void wall_should_contain(List<String> chits) throws Throwable {
        assertThat(wall.formatWithCreationInstant()).containsSequence(chits);
    }
}
