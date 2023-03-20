package com.example.emailemployeeauditing.controller;

import com.example.emailemployeeauditing.entity.Employee;
import com.example.emailemployeeauditing.entity.Task;
import com.example.emailemployeeauditing.payload.TaskDto;
import com.example.emailemployeeauditing.repository.EmployeeRepository;
import com.example.emailemployeeauditing.repository.TaskRepository;
import com.example.emailemployeeauditing.security.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/task")
@Service
public class TaskController {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    JavaMailSender javaMailSender;

    //    send task to employee
    @PreAuthorize(value = "hasAnyRole('ROLE_MANAGER', 'ROLE_DIRECTOR')")
    @PostMapping
    public HttpEntity<?> addTask(@RequestBody TaskDto taskDto) {

        Task task = new Task();
        task.setName(taskDto.getName());
        task.setDescription(taskDto.getDescription());
        task.setDeadline(taskDto.getDeadline());

        Optional<Employee> optionalEmployee = employeeRepository.findById(taskDto.getUsername());
        if (!optionalEmployee.isPresent())
            return ResponseEntity.notFound().build();

        task.setEmployee(optionalEmployee.get());


        sendEmail(optionalEmployee.get().getEmail(), task.toString());

        Task saveTask = taskRepository.save(task);
        return ResponseEntity.ok(saveTask);
    }

    //    Get
    @PreAuthorize(value = "hasAnyRole('ROLE_EMPLOYEE', 'ROLE_DIRECTOR', 'ROLE_MANAGER')")
    @GetMapping
    public HttpEntity<?> getTask() {
        return ResponseEntity.ok(taskRepository.findAll());
    }

    //    get by id
    @PreAuthorize(value = "hasAnyRole('ROLE_EMPLOYEE', 'ROLE_DIRECTOR', 'ROLE_MANAGER')")
    @GetMapping("/{id}")
    public HttpEntity<?> getTaskById(@PathVariable UUID id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        return ResponseEntity.status(optionalTask.isPresent() ? 200 : 404).body(optionalTask.orElse(null));
    }

    //    Update
    @PreAuthorize(value = "hasAnyRole('ROLE_DIRECTOR', 'ROLE_MANAGER')")
    @PutMapping("/{id}")
    public HttpEntity<?> editTask(@PathVariable UUID id, @RequestBody TaskDto taskDto) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (!optionalTask.isPresent())
            return ResponseEntity.notFound().build();
        Task editTask = optionalTask.get();
        editTask.setName(taskDto.getName());
        editTask.setDescription(taskDto.getDescription());
        editTask.setDeadline(taskDto.getDeadline());

        Optional<Employee> optionalEmployee = employeeRepository.findById(taskDto.getUsername());
        if (!optionalEmployee.isPresent())
            return ResponseEntity.notFound().build();

        editTask.setEmployee(optionalEmployee.get());


        sendEmail(optionalEmployee.get().getEmail(), editTask.toString());

        Task saveTask = taskRepository.save(editTask);
        return ResponseEntity.ok(saveTask);
    }

    //    Delete
    @PreAuthorize(value = "hasAnyRole('ROLE_DIRECTOR', 'ROLE_MANAGER')")
    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteTask(@PathVariable UUID id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (!optionalTask.isPresent())
            return ResponseEntity.notFound().build();

        taskRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }


    public Boolean sendEmail(String sendingEmail, String emailCode) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom("aq9163577");
            mailMessage.setTo(sendingEmail);
            mailMessage.setSubject("Accountni tasdiqlash");
            mailMessage.setText("<a href='http://localhost:8080/api/auth/verifyEmail?emailCode=" + emailCode + "&email=" + sendingEmail + "'>Tasdiqlang</a>");
            javaMailSender.send(mailMessage);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
