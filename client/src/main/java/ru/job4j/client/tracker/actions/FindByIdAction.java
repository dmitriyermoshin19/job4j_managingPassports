package ru.job4j.client.tracker.actions;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.job4j.client.tracker.servis.ITracker;
import ru.job4j.client.tracker.input.Input;

@Component
public class FindByIdAction implements UserAction {
    @Value("${findById}")
    private String name;

    @Override
    public String name() {
        return name;
    }

    @Override
    public boolean execute(Input input, ITracker tracker) {
        String id = input.getResponse("Enter id: ");
        System.out.print(tracker.findById(id));
        return true;
    }

    @Override
    public String toString() {
        return name;
    }
}
