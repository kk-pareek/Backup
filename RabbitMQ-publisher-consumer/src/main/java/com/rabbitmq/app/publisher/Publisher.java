package com.rabbitmq.app.publisher;

import com.rabbitmq.app.configuration.MQConfig;
import com.rabbitmq.app.dto.SampleObject;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/publisher")
public class Publisher {

    @Autowired
    RabbitTemplate theRabbitTemplate;

    @PostMapping("/publish")
    public String publishMessage(@RequestBody SampleObject theSampleObject) {
        theRabbitTemplate.convertAndSend(MQConfig.EXCHANGE, MQConfig.ROUTING_KEY, theSampleObject);
        return "message Published";
    }
}
