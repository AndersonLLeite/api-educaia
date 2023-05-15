package com.api.educaia.dtos;

import com.api.educaia.models.UserModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TopicAnswerDTO {
    @NotBlank
    private String content;
    @NotNull
    private Long creationDate;
    @NotBlank
    private String userNameWhoAnswered;
}
