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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_user")
public class UserModel implements UserDetails, Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String username;
    private String password;

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
}