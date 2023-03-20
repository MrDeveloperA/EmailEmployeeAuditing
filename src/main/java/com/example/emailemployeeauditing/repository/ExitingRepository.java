package com.example.emailemployeeauditing.repository;

import com.example.emailemployeeauditing.entity.Entering;
import com.example.emailemployeeauditing.entity.Exiting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.UUID;

@RepositoryRestResource(path = "exiting")
public interface ExitingRepository extends JpaRepository<Exiting, UUID> {

}
