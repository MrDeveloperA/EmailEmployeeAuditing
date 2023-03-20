package com.example.emailemployeeauditing.repository;

import com.example.emailemployeeauditing.entity.Employee;
import com.example.emailemployeeauditing.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
    boolean existsByEmail(String email);

    Optional<Employee> findByEmailAndEmailCode(String email, String emailCode);
    Optional<Employee> findByEmail(String email);

}

