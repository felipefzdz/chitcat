import com.google.inject.Inject;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.olid16.actions.CreateChit;
import io.olid16.actions.ReadTimeline;
import io.olid16.domain.entities.Timeline;
import io.olid16.domain.values.Chit;
import io.olid16.domain.values.Username;

import java.time.temporal.ChronoUnit;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static io.olid16.domain.values.Chit.*;
import static java.time.Instant.now;
import static java.time.temporal.ChronoUnit.*;
import static java.time.temporal.ChronoUnit.MINUTES;

public class ReadTimelineStepDefs {

    private final CreateChit createChit;
    private final ReadTimeline readTimeline;
    private static Timeline timeline;

    @Inject
    public ReadTimelineStepDefs(CreateChit createChit, ReadTimeline readTimeline) {
        this.createChit = createChit;
        this.readTimeline = readTimeline;
    }

    @Given("^([^ ]+) writes ([^ ]+), (\\d+) ([^ ]+) ago$")
    public void user_writes_a_chit_some_minutes_ago(String userName, String chitText, int minutesAgo, String chronoUnit) throws Throwable {
        Chit chit = create(chitText, now().minus(minutesAgo, valueOf(chronoUnit.toUpperCase())));
        createChit.with(Username.create(userName), chit);
    }

    @When("^a user reads ([^ ]+) timeline$")
    public void a_user_read_a_timeline(String userName) throws Throwable {
        timeline = readTimeline.with(Username.create(userName)).get();
    }

    @Then("^timeline should contain \"(.+)\"")
    public void timeline_should_contain_chits(List<String> chits) throws Throwable {
        assertThat(timeline.formatWithCreationInstant()).containsSequence(chits);
    }
}
