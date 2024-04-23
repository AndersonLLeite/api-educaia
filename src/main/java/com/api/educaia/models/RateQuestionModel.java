package com.api.educaia.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "rate_question")
public class RateQuestionModel {
    private String question;
    private int Rate;

    public RateQuestionModel(String question, int rate) {
        this.question = question;
        Rate = rate;

    }
}
