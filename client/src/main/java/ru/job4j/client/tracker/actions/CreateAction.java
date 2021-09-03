package ru.job4j.client.tracker.actions;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.job4j.client.tracker.servis.ITracker;
import ru.job4j.client.tracker.domains.Item;
import ru.job4j.client.tracker.input.Input;

@Component
public class CreateAction implements UserAction {
    @Value("${create}")
    private String name;

    @Override
    public String name() {
        return name;
    }

    @Override
    public boolean execute(Input input, ITracker tracker) {
        String name = input.getResponse("Enter name: ");
        Item item = new Item(name);
        tracker.add(item);
        System.out.print("Item created");
        return true;
    }

    @Override
    public String toString() {
        return name;
    }
}
