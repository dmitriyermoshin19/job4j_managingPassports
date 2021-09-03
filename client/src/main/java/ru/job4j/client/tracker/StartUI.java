package ru.job4j.client.tracker;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import ru.job4j.client.tracker.actions.*;
import ru.job4j.client.tracker.input.Input;
import ru.job4j.client.tracker.servis.ITracker;


import java.util.List;
import java.util.function.Consumer;

@Component
public class StartUI {
    private final KafkaTemplate<Integer, String> template;
    ApplicationContext context;
    private final List<UserAction> actions;
    Input input;
    ITracker tracker;
    @Value("${exit}")
    String name;
    UserAction action;

    public StartUI(KafkaTemplate<Integer, String> template, List<UserAction> actions,
                   @Qualifier("validateInput") Input input, @Qualifier("tracker")ITracker tracker,
                   ApplicationContext context) {
        this.template = template;
        this.actions = actions;
        this.input = input;
        this.tracker = tracker;
        this.context = context;
    }

    private void showMenu() {
        Consumer<String> output = messageConsumer();
        output.accept("===== Menu =====");
        int index = 0;
        for (UserAction act : actions) {
            output.accept(index++ + ". " + act);
        }
    }

    public Consumer<String> messageConsumer() {
        return System.out::println;
    }

    @Autowired
    public void init() {
        boolean run = true;
        while (run) {
            showMenu();
            int select = input.askInt("Enter select: ", actions.size());
            action = actions.get(select);
            run = action.execute(input, tracker);
            // ОТПРАВКА СТАТИСТИКИ
            template.send("tracker", action.getClass().getSimpleName());
            System.out.println(action.toString());
        }
    }

    @KafkaListener(topics = {"tracker-stats"})
    public void listenStatistic(ConsumerRecord<Integer, String> input) {
        System.out.printf("KafkaListener(topics = tracker-stats) - input.value() -\n%s%n", input.value());
        if(!action.toString().equals(name)) {
            init();
        } else {
            System.out.println("подождите, пока программа закроется...");
            ConfigurableApplicationContext closable = (ConfigurableApplicationContext)context;
            closable.close();
        }
    }
}
