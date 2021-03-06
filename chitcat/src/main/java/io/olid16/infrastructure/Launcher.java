package io.olid16.infrastructure;

import com.google.inject.Guice;
import io.olid16.infrastructure.cli.wrappers.Repl;
import io.olid16.infrastructure.dependency_injection.ChitcatModule;

public class Launcher {
    
    public static void main(String[] args){
        Guice.createInjector(new ChitcatModule()).getInstance(Repl.class).start();
        
    }
}
