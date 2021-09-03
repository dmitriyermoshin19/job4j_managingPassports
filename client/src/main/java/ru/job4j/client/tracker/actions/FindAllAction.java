package ru.job4j.client.tracker.actions;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.job4j.client.tracker.servis.ITracker;
import ru.job4j.client.tracker.input.Input;
import ru.job4j.client.tracker.domains.Item;

import java.util.List;

@Component
public class FindAllAction implements UserAction {
    @Value("${findAll}")
    private String name;

    @Override
    public String name() {
        return name;
    }

    @Override
    public boolean execute(Input input, ITracker tracker) {
        List<Item> items = tracker.findAll();
        for (Item item : items) {
            System.out.println(item.getId() + " " + item.getName());
        }
        return true;
    }

    @Override
    public String toString() {
        return name;
    }
}
