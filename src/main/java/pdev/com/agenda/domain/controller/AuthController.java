package pdev.com.agenda.domain.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
@Api(tags = "Autenticação", description = "Endpoints para login e autenticação de usuários")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    @ApiOperation(value = "Efetua login", notes = "Autentica o usuário e retorna um token JWT")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Login realizado com sucesso"),
            @ApiResponse(code = 400, message = "Requisição inválida"),
            @ApiResponse(code = 401, message = "Credenciais inválidas")
    })
    public ResponseEntity<UserLoginResponse> login(
            @ApiParam(value = "Credenciais do usuário", required = true)
            @RequestBody UserLoginRequest loginRequest) {

        UserLoginResponse response = authService.login(loginRequest);
        return ResponseEntity.ok(response);
    }
}