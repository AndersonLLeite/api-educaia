package com.api.educaia.models;

import lombok.Data;

@Data
public class RateQuestionModel {
    private String question;
    private int Rate;

    public RateQuestionModel(String question, int rate) {
        this.question = question;
        Rate = rate;

    }
}
