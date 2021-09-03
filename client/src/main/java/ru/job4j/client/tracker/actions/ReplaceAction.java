package ru.job4j.client.tracker.actions;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.job4j.client.tracker.domains.Item;
import ru.job4j.client.tracker.input.Input;
import ru.job4j.client.tracker.servis.ITracker;

@Component
public class ReplaceAction implements UserAction {
    @Value("${replace}")
    private String name;

    @Override
    public String name() {
        return name;
    }

    @Override
    public boolean execute(Input input, ITracker tracker) {
        String id = input.getResponse("Enter id: ");
        String name = input.getResponse("Edit name: ");
        Item item = new Item(name);
        tracker.replace(id, item);
        System.out.print("Item edited");
        return true;
    }

    @Override
    public String toString() {
        return name;
    }
}
