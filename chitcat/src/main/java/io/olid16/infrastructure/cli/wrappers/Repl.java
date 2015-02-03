package io.olid16.infrastructure.cli.wrappers;

import com.google.inject.Inject;
import io.olid16.infrastructure.cli.Router;

public class Repl {

    private final OutputWriter out;
    private final Router router;

    @Inject
    public Repl(OutputWriter out, Router router) {
        this.out = out;
        this.router = router;
    }

    public void start() {
        out.write("Welcome to Chitcat!");
        while(true){
           router.route();
        }
    }
}
