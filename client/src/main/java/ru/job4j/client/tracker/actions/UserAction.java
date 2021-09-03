package ru.job4j.client.tracker.actions;

import ru.job4j.client.tracker.input.Input;
import ru.job4j.client.tracker.servis.ITracker;

public interface UserAction {
    String name();
    boolean execute(Input input, ITracker tracker);
}
