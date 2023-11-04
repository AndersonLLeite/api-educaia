package com.api.educaia.dtos;

import lombok.Getter;

import java.util.List;

@Getter
public class ChatResponseDTO {
    private List<Choice> choices;

    @Getter
    public static class Choice {

        private int index;
        private MessageDTO message;

        public void setIndex(int index) {
            this.index = index;
        }

        public void setMessage(MessageDTO message) {
            this.message = message;
        }
    }

    public void setChoices(List<Choice> choices) {
        this.choices = choices;
    }
}
