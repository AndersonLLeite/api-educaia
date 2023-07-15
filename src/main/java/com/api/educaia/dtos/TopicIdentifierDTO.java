package com.api.educaia.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TopicIdentifierDTO {
    private UUID id;
    private String title;
    private String category;
    private String creatorName;
    private int answersCount;
    private int favoriteCount;
}
