package com.api.educaia.models;

import com.api.educaia.enums.RoleName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TB_ROLE")
public class RoleModel implements GrantedAuthority, Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID roleId;

    @Column(nullable = false, unique = true)
    private String roleName;

    @Override
    public String getAuthority() {
        return this.roleName;
    }
}
