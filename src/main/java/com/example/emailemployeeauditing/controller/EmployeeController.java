package com.example.emailemployeeauditing.controller;

import com.example.emailemployeeauditing.payload.ApiResponse;
import com.example.emailemployeeauditing.payload.LoginDto;
import com.example.emailemployeeauditing.payload.RegisterDto;
import com.example.emailemployeeauditing.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @PreAuthorize(value = "hasAnyRole('ROLE_HR_MANAGER')")
    @PostMapping("/register")
    public HttpEntity<?> registerUser(@RequestBody RegisterDto registerDto) {
        ApiResponse apiResponse = employeeService.registerEmployee(registerDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }
    @PreAuthorize(value = "hasAnyRole('ROLE_EMPLOYEE')")
    @GetMapping("/verifyEmail")
    public HttpEntity<?> verifyEmail(@RequestParam String emailCode, @RequestParam String email) {
        ApiResponse apiResponse = employeeService.verifyEmail(emailCode, email);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }
    @PreAuthorize(value = "hasAnyRole('ROLE_EMPLOYEE')")
    @PostMapping("/login")
    public HttpEntity<?> login(@RequestBody LoginDto loginDto){
       ApiResponse apiResponse = employeeService.login(loginDto);
    return ResponseEntity.status(apiResponse.isSuccess()?200:401).body(apiResponse);
    }
}
