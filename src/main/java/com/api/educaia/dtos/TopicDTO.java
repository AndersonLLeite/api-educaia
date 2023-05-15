package com.api.educaia.dtos;

import com.api.educaia.models.TopicAnswer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TopicDTO {
    @NotBlank
    private String title;
    @NotBlank

    private String content;
    @NotBlank

    private String schoolId;
    @NotBlank

    private String username;
    @NotBlank

    private String category;
    @NotNull

    private Long creationDate;

    private List<TopicAnswer> answers;





}
