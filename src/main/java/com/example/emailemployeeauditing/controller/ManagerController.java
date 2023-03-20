package com.example.emailemployeeauditing.controller;

import com.example.emailemployeeauditing.payload.ApiResponse;
import com.example.emailemployeeauditing.payload.LoginDto;
import com.example.emailemployeeauditing.payload.RegisterDto;
import com.example.emailemployeeauditing.service.DirectorService;
import com.example.emailemployeeauditing.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/manager")
public class ManagerController {
    @Autowired
    ManagerService managerService;
    @PreAuthorize(value = "hasAnyRole('ROLE_DIRECTOR')")
    @PostMapping("/registerManager")
    public HttpEntity<?> registerManager(@RequestBody RegisterDto registerDto) {
        ApiResponse apiResponse = managerService.registerMangaer(registerDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }
    @PreAuthorize(value = "hasAnyRole('ROLE_MANAGER')")
    @GetMapping("/verifyEmail")
    public HttpEntity<?> verifyEmail(@RequestParam String emailCode, @RequestParam String email) {
        ApiResponse apiResponse = managerService.verifyEmail(emailCode, email);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }
    @PreAuthorize(value = "hasAnyRole('ROLE_MANAGER')")
    @PostMapping("/login")
    public HttpEntity<?> login(@RequestBody LoginDto loginDto){
       ApiResponse apiResponse = managerService.login(loginDto);
    return ResponseEntity.status(apiResponse.isSuccess()?200:401).body(apiResponse);
    }
}
