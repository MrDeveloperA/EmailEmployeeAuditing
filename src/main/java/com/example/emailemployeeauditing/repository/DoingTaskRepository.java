package com.example.emailemployeeauditing.repository;

import com.example.emailemployeeauditing.entity.DoingTask;
import com.example.emailemployeeauditing.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.UUID;

@RepositoryRestResource(path = "doingTask")
public interface DoingTaskRepository extends JpaRepository<DoingTask, UUID> {

}
