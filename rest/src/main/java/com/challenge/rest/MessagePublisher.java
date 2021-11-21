package com.challenge.rest;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.util.NumberUtils;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;

@RestController
public class MessagePublisher {

    @Autowired
    private RabbitTemplate template;

    @GetMapping ("/sum")
    public Result sum(@RequestParam Map<String, String> values){
        validation(values);
        RequestInput message = setMessageValues(values, RequestInput.Operation.SUM);

        return
                template.convertSendAndReceiveAsType(
                        MQConfig.EXCHANGE,
                        MQConfig.ROUTING_KEY,
                        message,  new ParameterizedTypeReference<>() {
                        });
    }

    @GetMapping ("/subtraction")
    public Result subtraction(@RequestParam Map<String, String> values){
        validation(values);
        RequestInput message = setMessageValues(values, RequestInput.Operation.SUBTRACTION);

        return
                template.convertSendAndReceiveAsType(
                        MQConfig.EXCHANGE,
                        MQConfig.ROUTING_KEY,
                        message,  new ParameterizedTypeReference<>() {
                        });
    }

    @GetMapping ("/multiplication")
    public Result multiplication(@RequestParam Map<String, String> values){
        validation(values);
        RequestInput message = setMessageValues(values, RequestInput.Operation.MULTIPLICATION);

        return
                template.convertSendAndReceiveAsType(
                        MQConfig.EXCHANGE,
                        MQConfig.ROUTING_KEY,
                        message,  new ParameterizedTypeReference<>() {
                        });
    }

    @GetMapping ("/division")
    public Result division(@RequestParam Map<String, String> values) {
        validation(values);
        RequestInput message = setMessageValues(values, RequestInput.Operation.DIVISION);

        return
                template.convertSendAndReceiveAsType(
                        MQConfig.EXCHANGE,
                        MQConfig.ROUTING_KEY,
                        message, new ParameterizedTypeReference<>() {
                        });
    }

    private void validation(Map<String, String> values) {
        if(!values.containsKey("a") || !values.containsKey("b")){
            throw new IllegalArgumentException();
        }
        if(!isNumber(values.get("a")) || !isNumber(values.get("b"))){
            throw new IllegalArgumentException();
        }
    }

    private RequestInput setMessageValues(Map<String, String> values, RequestInput.Operation operation) {
        return new RequestInput(NumberUtils.parseNumber(values.get("a"), BigDecimal.class), NumberUtils.parseNumber(values.get("a"), BigDecimal.class), operation);
    }

    private boolean isNumber(String s)
    {
        try
        {
            NumberUtils.parseNumber(s, BigDecimal.class);
            return true;
        } catch (NumberFormatException ex)
        {
            return false;
        }
    }



}
