package ru.job4j.client.tracker.servis;

import ru.job4j.client.tracker.domains.Item;

import java.util.List;

public interface ITracker {
    Item add(Item item);
    boolean replace(String id, Item item);
    boolean delete(String id);
    List<Item> findAll();
    List<Item> findByName(String key);
    Item findById(String id);
}
