package com.example.emailemployeeauditing.controller;

import com.example.emailemployeeauditing.payload.ApiResponse;
import com.example.emailemployeeauditing.payload.LoginDto;
import com.example.emailemployeeauditing.payload.RegisterDto;
import com.example.emailemployeeauditing.service.DirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/director")
public class DirectorController {
    @Autowired
    DirectorService directorService;
    @PreAuthorize(value = "hasAnyRole('ROLE_DIRECTOR')")
    @PostMapping("/registerDirector")
    public HttpEntity<?> registerUser(@RequestBody RegisterDto registerDto) {
        ApiResponse apiResponse = directorService.registerDirector(registerDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }
    @PreAuthorize(value = "hasAnyRole('ROLE_DIRECTOR')")
    @GetMapping("/verifyEmail")
    public HttpEntity<?> verifyEmail(@RequestParam String emailCode, @RequestParam String email) {
        ApiResponse apiResponse = directorService.verifyEmail(emailCode, email);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }
    @PreAuthorize(value = "hasAnyRole('ROLE_DIRECTOR')")
    @PostMapping("/login")
    public HttpEntity<?> login(@RequestBody LoginDto loginDto){
       ApiResponse apiResponse = directorService.login(loginDto);
    return ResponseEntity.status(apiResponse.isSuccess()?200:401).body(apiResponse);
    }
}
