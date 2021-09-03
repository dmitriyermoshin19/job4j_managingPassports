package ru.job4j.client.tracker.actions;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.job4j.client.tracker.servis.ITracker;
import ru.job4j.client.tracker.input.Input;

@Component
public class DeleteAction implements UserAction {
    @Value("${delete}")
    private String name;

    @Override
    public String name() {
        return name;
    }

    @Override
    public boolean execute(Input input, ITracker tracker) {
        String id = input.getResponse("Enter id: ");
        tracker.delete(id);
        System.out.print("Enter id: ");
        return true;
    }

    @Override
    public String toString() {
        return name;
    }
}
