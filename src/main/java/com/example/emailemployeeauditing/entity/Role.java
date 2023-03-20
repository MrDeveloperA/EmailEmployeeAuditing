package com.example.emailemployeeauditing.entity;

import com.example.emailemployeeauditing.entity.enums.RoleNames;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Enumerated(EnumType.STRING)
    private RoleNames roleName;

    @Override
    public String getAuthority() {
        return roleName.name();
    }
}
