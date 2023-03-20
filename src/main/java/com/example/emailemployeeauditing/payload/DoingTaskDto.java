package com.example.emailemployeeauditing.payload;

import com.example.emailemployeeauditing.entity.Employee;
import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import java.sql.Timestamp;
import java.util.UUID;


@Data
public class DoingTaskDto {
    @NotNull
    private UUID conditionTask;
    @NotNull
    @Email
    private UUID username;
}
