package com.example.emailemployeeauditing.repository;

import com.example.emailemployeeauditing.entity.Product;
import com.example.emailemployeeauditing.entity.Salary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.UUID;

@RepositoryRestResource(path = "salary")
public interface SalaryRepository extends JpaRepository<Salary, UUID> {

}
