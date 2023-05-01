package com.api.educaia.models;

import lombok.Data;

@Data
public class RateQuestion {
    private String question;
    private int Rate;

    public RateQuestion(String question, int rate) {
        this.question = question;
        Rate = rate;

    }
}
