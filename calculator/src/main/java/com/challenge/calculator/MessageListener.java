package com.challenge.calculator;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.RoundingMode;

@Component
public class MessageListener {

    @Autowired
    private RabbitTemplate template;

    @RabbitListener(queues = MQConfig.QUEUE)
    public Result listener(RequestInput message) {
    return getResult(message);
    }

    private Result getResult(RequestInput message) {
        if (RequestInput.Operation.SUM == message.getOperation()) {
            return new Result(message.getValueA().add(message.getValueB()));
        } else if (RequestInput.Operation.SUBTRACTION == message.getOperation()) {
            return new Result(message.getValueA().subtract(message.getValueB()));
        } else if (RequestInput.Operation.DIVISION == message.getOperation()) {
         return new Result(message.getValueA().divide(message.getValueB(), 2 , RoundingMode.HALF_UP));
        } else if (RequestInput.Operation.MULTIPLICATION == message.getOperation()) {
            return new Result(message.getValueA().multiply( message.getValueB()));
        }else{
            return null;
        }
    }

}
