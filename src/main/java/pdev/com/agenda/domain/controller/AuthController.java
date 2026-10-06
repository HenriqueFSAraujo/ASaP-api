package pdev.com.agenda.domain.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pdev.com.agenda.domain.dto.UserLoginRequest;
import pdev.com.agenda.domain.dto.UserLoginResponse;
import pdev.com.agenda.domain.service.AuthService;

@RestController
@RequestMapping("api/auth")
@Tag(name = "Autenticação", description = "Endpoints para login e autenticação de usuários")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    @Operation(summary = "Efetua login", description = "Autentica o usuário e retorna um token JWT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login realizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    })
    public ResponseEntity<UserLoginResponse> login(
            @Parameter(description = "Credenciais do usuário", required = true)
            @RequestBody UserLoginRequest loginRequest) {

        UserLoginResponse response = authService.login(loginRequest);
        return ResponseEntity.ok(response);
    }
}