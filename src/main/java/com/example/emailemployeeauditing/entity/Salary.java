package com.example.emailemployeeauditing.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Salary {
    @Id
    @GeneratedValue
    private UUID id;

    @OneToOne
    private Employee employee;
    @Column(nullable = false)
    private double amount;
    @Column(nullable = false)
    private double additionalPayment;
    @Column(nullable = false)
    private boolean given;
    @CreatedBy
    private UUID createdBy;

    @LastModifiedBy
    private UUID updatedBy;
    @CreationTimestamp
    private Timestamp createdAt;
    @UpdateTimestamp
    private Timestamp updatedAt;
}
