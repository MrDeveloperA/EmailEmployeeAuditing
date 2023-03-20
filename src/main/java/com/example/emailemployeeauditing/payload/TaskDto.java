package com.example.emailemployeeauditing.payload;

import com.example.emailemployeeauditing.entity.Employee;
import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.UUID;

@Data
public class TaskDto {
    @NotNull
    private String name;
    @NotNull
    private String description;
    @NotNull
    private Timestamp deadline;
    @NotNull
    @Email
    private UUID username;//email
}
