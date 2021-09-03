package ru.job4j.server.controller;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class KafkaTrackerController {
    private final Map<String, Integer> statistic = new ConcurrentHashMap<>();
    private final KafkaTemplate<Integer, String> template;
    ApplicationContext context;
    String value;

    public KafkaTrackerController(ApplicationContext context, KafkaTemplate<Integer, String> template) {
        this.context = context;
        this.template = template;
    }

    @KafkaListener(topics = {"tracker"})
    public void onApiCall(ConsumerRecord<Integer, String> input) {
        value = input.value();
        statistic.put(value, statistic.getOrDefault(value, 0) + 1);
        System.out.println("save:+++++++++input.value(): " + value);
        System.out.println("statistic без посылания клиенту: " + statistic.toString());
        if (value.equals("StatisticAction")) {
            template.send("tracker-stats", statistic.toString());
            System.out.println(input.value() + " - input.value() после посыла kafka-client: tracker-stats");
        }
        closeProgram();
    }

    public void closeProgram() {
        if(value.equals("ExitProgramAction")) {
            System.out.println("подождите, пока программа закроется...");
            ConfigurableApplicationContext closable = (ConfigurableApplicationContext)context;
            closable.close();
        }
    }
}
