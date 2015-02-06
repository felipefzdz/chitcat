package io.olid16.infrastructure.cli.commands;

import io.olid16.actions.CreateChit;
import io.olid16.domain.values.Chit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static builders.ChitBuilder.aChit;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CreateChitCommandShould {

    @Mock CreateChit createChit;

    @Test public void
    call_create_chit(){
        new CreateChitCommand(createChit, aChit().build()).execute();
        verify(createChit).with(any(Chit.class));
    }

}