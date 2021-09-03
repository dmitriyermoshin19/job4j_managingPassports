package ru.job4j.client.tracker.input;

import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ConsoleInput implements Input {
    private final Scanner scanner = new Scanner(System.in);


    @Override
    public String getResponse(String request) {
        System.out.print(request);
        return scanner.nextLine();
    }

    @Override
    public int askInt(String request) {
        return Integer.parseInt(getResponse(request));
    }

    @Override
    public int askInt(String request, int max) {
        int select = askInt(request);
        if (select < 0 || select >= max) {
            throw new IllegalStateException(String.format("Out of about %d > [0, %d]", select, max));
        }
        return select;
    }
}
