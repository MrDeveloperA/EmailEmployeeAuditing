package com.example.emailemployeeauditing.repository;

import com.example.emailemployeeauditing.entity.IDCard;
import com.example.emailemployeeauditing.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.UUID;
@RepositoryRestResource(path = "idCard")

public interface IDCardRepository extends JpaRepository<IDCard, UUID> {

}

