package com.challenge.rest;

import java.math.BigDecimal;

public class CustomMessage {

    private BigDecimal valueA;
    private BigDecimal valueB;
    private Operation operation;

    public CustomMessage(BigDecimal valueA, BigDecimal valueB, Operation operation){
        this.valueA = valueA;
        this.valueB = valueB;
        this.operation = operation;
    }

    public CustomMessage(){
    }

    public BigDecimal getValueA() {
        return valueA;
    }

    public void setValueA(BigDecimal valueA) {
        this.valueA = valueA;
    }

    public BigDecimal getValueB() {
        return valueB;
    }

    public void setValueB(BigDecimal valueB) {
        this.valueB = valueB;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    enum Operation {
        SUM,
        DIVISION,
        MULTIPLICATION,
        SUBTRACTION
    }
}