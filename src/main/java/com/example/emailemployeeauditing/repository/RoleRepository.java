package com.example.emailemployeeauditing.repository;

import com.example.emailemployeeauditing.entity.Role;
import com.example.emailemployeeauditing.entity.enums.RoleNames;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByRoleName(RoleNames roleName);
}
