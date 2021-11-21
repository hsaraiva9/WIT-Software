package com.challenge.calculator;

import java.math.BigDecimal;

public class Result {

    private BigDecimal result;

    public Result(BigDecimal result) {
        this.result = result;
    }

    public Result() {
    }

    public BigDecimal getResult() {
        return result;
    }
}
