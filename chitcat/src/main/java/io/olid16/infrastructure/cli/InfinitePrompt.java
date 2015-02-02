package io.olid16.infrastructure.cli;

import com.google.inject.Inject;

public class InfinitePrompt {

    private final OutputWriter out;
    private final Prompt prompt;

    @Inject
    public InfinitePrompt(OutputWriter out, Prompt prompt) {
        this.out = out;
        this.prompt = prompt;
    }

    public void start() {
        out.write("Welcome to Chitcat!");
        while(true){
           prompt.start();
        }
    }
}
