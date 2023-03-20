package com.example.emailemployeeauditing.service;

import com.example.emailemployeeauditing.entity.Employee;
import com.example.emailemployeeauditing.entity.enums.RoleNames;
import com.example.emailemployeeauditing.payload.ApiResponse;
import com.example.emailemployeeauditing.payload.LoginDto;
import com.example.emailemployeeauditing.payload.RegisterDto;
import com.example.emailemployeeauditing.repository.RoleRepository;
import com.example.emailemployeeauditing.repository.EmployeeRepository;
import com.example.emailemployeeauditing.security.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

@Service
public class DirectorService implements UserDetailsService {
    @Autowired
    EmployeeRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    JavaMailSender javaMailSender;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtProvider jwtProvider;


    public ApiResponse registerDirector(RegisterDto registerDto) {
        boolean existsByEmail = userRepository.existsByEmail(registerDto.getEmail());
        if (existsByEmail)
            return new ApiResponse("Such email already exist", false);

        Employee director = new Employee();
        director.setFirstName(registerDto.getFirstName());
        director.setLastName(registerDto.getLastName());
        director.setEmail(registerDto.getEmail());
        director.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        director.setRoles(Collections.singleton(roleRepository.findByRoleName(RoleNames.ROLE_DIRECTOR)));
        director.setEmailCode(UUID.randomUUID().toString());
        userRepository.save(director);

        sendEmail(director.getEmail(), director.getEmailCode());
        return new ApiResponse("Successfully registered. Confirm your email!", true);
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

    public ApiResponse verifyEmail(String emailCode, String email) {
        Optional<Employee> optionalEmployee = userRepository.findByEmailAndEmailCode(email, emailCode);
        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();
            employee.setEnabled(true);
            employee.setEmailCode(null);
            userRepository.save(employee);
            return new ApiResponse("Account confirmed", true);
        }
        return new ApiResponse("Account has already been confirmed", false);

    }

    public ApiResponse login(LoginDto loginDto) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginDto.getUsername(),
                    loginDto.getPassword()));
            Employee employee = (Employee) authentication.getPrincipal();
            String token = jwtProvider.generateToken(loginDto.getUsername(), employee.getRoles());
            return new ApiResponse("Token", true, token);
        } catch (BadCredentialsException badCredentialsException) {
            return new ApiResponse("Password or login is wrong", false);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Employee> optionalUser = userRepository.findByEmail(username);
        if (optionalUser.isPresent())
            return optionalUser.get();
        throw new UsernameNotFoundException(username + "Not found!");
    }
}
