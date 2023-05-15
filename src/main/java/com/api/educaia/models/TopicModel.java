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
@Table(name = "topic")
public class TopicModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String title;
    @Column(columnDefinition = "TEXT")
    private String content;
    private String schoolId;
    private String category;
    @OneToOne
    private UserModel userWhoCreated;
    private String username;
    private Long creationDate;
    @OneToMany(cascade = CascadeType.ALL)
    private List<TopicAnswer> answers = new ArrayList<>();
    private boolean isOpen = true;
    @OneToOne
    private TopicAnswer bestAnswer;
    @ElementCollection
    private List<String> usernamesWhoLiked = new ArrayList<>();
    @ElementCollection
    private List<String> usernamesWhoFavorite = new ArrayList<>();

    public void addAnswer(TopicAnswer topicAnswer) {
        this.answers.add(topicAnswer);
    }

    public void addUserWhoFavorite(String username) {
        if (!this.usernamesWhoFavorite.contains(username)) {
            this.usernamesWhoFavorite.add(username);
        }
    }

    public void removeUserWhoFavorite(String username) {
            this.usernamesWhoFavorite.remove(username);
    }
}
