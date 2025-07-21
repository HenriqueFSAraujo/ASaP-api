package pdev.com.agenda.domain.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pdev.com.agenda.domain.dto.UserLoginRequest;
import pdev.com.agenda.domain.dto.UserLoginResponse;
import pdev.com.agenda.domain.service.AuthService;
import pdev.com.agenda.domain.service.UserLoginService;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserLoginService userLoginService;

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponse> login(@RequestBody UserLoginRequest loginRequest) {
        UserLoginResponse response = authService.login(loginRequest);
        return ResponseEntity.ok(response);
    }


}