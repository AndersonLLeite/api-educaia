package com.api.educaia.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "topic_answer")
public class TopicAnswer implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(columnDefinition = "TEXT")
    private String content;
    private Long creationDate;
    private UUID topicId;
    private String userNameWhoAnswered;
    @OneToOne
    private UserModel userWhoAnswered;
    @ElementCollection
    private List<String> usernamesWhoLiked = new ArrayList<>();
}
