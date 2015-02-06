package io.olid16.infrastructure.cli.commands;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import io.olid16.actions.ReadTimeline;
import io.olid16.domain.values.Username;
import io.olid16.infrastructure.cli.wrappers.OutputWriter;

public class ReadTimelineCommand implements Command{

    private final ReadTimeline readTimeline;
    private final OutputWriter out;
    private final Username username;

    @Inject
    ReadTimelineCommand(ReadTimeline readTimeline,
                        OutputWriter out,
                        @Assisted Username username) {
        this.readTimeline = readTimeline;
        this.out = out;
        this.username = username;
    }


    @Override
    public void execute() {
        readTimeline.with(username).
                ifPresent(timeline -> timeline.format().forEach(out::write));
    }

    public static interface ReadTimelineCommandFactory {
        public ReadTimelineCommand create(Username username);
    }
}
