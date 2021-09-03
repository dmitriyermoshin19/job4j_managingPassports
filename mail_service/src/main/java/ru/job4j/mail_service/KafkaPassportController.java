package ru.job4j.mail_service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Controller;

@Controller
public class KafkaPassportController {

    @KafkaListener(topics= {"passport"})
    public void msgListener(ConsumerRecord<Integer, String> input){
        System.out.println(input.value());
    }
}
