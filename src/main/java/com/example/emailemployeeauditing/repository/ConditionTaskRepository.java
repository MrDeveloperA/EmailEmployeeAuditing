package com.example.emailemployeeauditing.repository;

import com.example.emailemployeeauditing.entity.ConditionTask;
import com.example.emailemployeeauditing.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
@RepositoryRestResource(path = "conditionTask")
public interface ConditionTaskRepository extends JpaRepository<ConditionTask, UUID> {

}
