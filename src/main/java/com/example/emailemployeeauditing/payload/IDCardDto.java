package com.example.emailemployeeauditing.payload;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import java.sql.Timestamp;
import java.util.UUID;

@Data
public class IDCardDto {
    @NotNull
    @Email
    private UUID username;//email
}
