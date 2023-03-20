package com.example.emailemployeeauditing.repository;

import com.example.emailemployeeauditing.entity.Employee;
import com.example.emailemployeeauditing.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, UUID> {

}

