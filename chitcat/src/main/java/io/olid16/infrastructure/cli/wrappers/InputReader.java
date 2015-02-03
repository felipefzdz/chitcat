package io.olid16.infrastructure.cli.wrappers;

import com.google.inject.Inject;

import java.util.Scanner;

import static java.lang.System.in;

public class InputReader {
    
    private final Scanner scanner;

    @Inject
    public InputReader() {
        this.scanner = new Scanner(in);
    }


    public String read() {
        return scanner.nextLine();
    }

}
