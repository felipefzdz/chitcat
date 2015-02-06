package io.olid16.infrastructure.cli.commands;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import io.olid16.actions.CreateChit;
import io.olid16.domain.values.Chit;

public class CreateChitCommand implements Command{

    private final Chit chit;
    private final CreateChit createChit;

    @Inject
    CreateChitCommand(CreateChit createChit,
                      @Assisted Chit chit) {
        this.chit = chit;
        this.createChit = createChit;
    }

    @Override
    public void execute() {
        createChit.with(chit);
    }

    public static interface CreateChitCommandFactory {
        public CreateChitCommand create(Chit chit);
    }
}
