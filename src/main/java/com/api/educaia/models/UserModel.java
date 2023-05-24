package com.api.educaia.models;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import com.api.educaia.enums.LevelsOfEducationEnum;
import com.api.educaia.enums.RoleName;
import com.api.educaia.token.Token;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@With
@Entity
@Table(name = "tb_user")
public class UserModel implements UserDetails, Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(unique = true)
    private String username;
    @JsonIgnore
    private String password;
    private String nameComplete;

    @Enumerated(EnumType.STRING)
    private RoleName role;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Token> tokens;

    private String schoolId;
    private String profileImagePath;
    private int points;
    @ElementCollection
    private List<String> medalImagePath;
    private String classId;
    @ManyToMany
    @JoinTable(
            name = "user_class",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "class_id")
    )
    private List<ClassModel> classes;
    @Enumerated(EnumType.STRING)
    private LevelsOfEducationEnum levelsOfEducation;
    private String childID;
    @ElementCollection
    private List<String> following;
    @ElementCollection
    private List<String> followers;
    private int forumPoints = 0;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void addFollower(String followerUsername) {
        this.followers.add(followerUsername);
    }

    public void removeFollower(String followerUsername) {
        this.followers.remove(followerUsername);
    }

    public void addFollowing(String username) {
        this.following.add(username);
    }

    public void removeFollowing(String username) {
        this.following.remove(username);
    }
}