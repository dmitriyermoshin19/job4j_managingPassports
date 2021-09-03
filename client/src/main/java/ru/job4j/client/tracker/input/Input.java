package ru.job4j.client.tracker.input;

public interface Input {
    String getResponse(String request);
    int askInt(String request);
    int askInt(String request, int max);
}
