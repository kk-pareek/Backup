package com.rabbitmq.app.consumer;

import com.rabbitmq.app.configuration.MQConfig;
import com.rabbitmq.app.dto.SampleObject;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Component
public class Consumer {
    @RabbitListener(queues = MQConfig.QUEUE)
    public void consumeAndPrintMessage(SampleObject theSampleObject) {
        System.out.println(theSampleObject);
    }
}
