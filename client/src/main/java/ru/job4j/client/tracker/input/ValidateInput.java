package ru.job4j.client.tracker.input;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class ValidateInput implements Input {
    private final Input input;

    public ValidateInput(@Qualifier("consoleInput") Input input) {
        this.input = input;
    }

    @Override
    public String getResponse(String request) {
        return input.getResponse(request);
    }

    @Override
    public int askInt(String request) {
        return input.askInt(request);
    }

    @Override
    public int askInt(String request, int max) {
        boolean invalid = true;
        int value = -1;
        do {
            try {
                value = input.askInt(request, max);
                invalid = false;
            } catch (IllegalStateException moe) {
                System.out.println("Please select key from menu ");
            } catch (NumberFormatException nfe) {
                System.out.println("Please enter validate data again ");
            }
        } while (invalid);
        return value;
    }
}
