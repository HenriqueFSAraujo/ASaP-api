package pdev.com.agenda.domain.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pdev.com.agenda.domain.UserInfoResponse;
import pdev.com.agenda.domain.dto.ResetPasswordRequest;
import pdev.com.agenda.domain.dto.StatusDTO;
import pdev.com.agenda.domain.dto.UserInfoDTO;
import pdev.com.agenda.domain.service.UserInfoService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserInfoController {

    private final UserInfoService userInfoService;

    public UserInfoController(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @GetMapping
    public ResponseEntity<List<UserInfoResponse>> getAllUsers() {
        return ResponseEntity.ok(userInfoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserInfoDTO> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userInfoService.findById(id));
    }

    @PostMapping
    public ResponseEntity<UserInfoDTO> createUser(@RequestBody UserInfoDTO dto) {
        return ResponseEntity.ok(userInfoService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserInfoDTO> updateUser(
            @PathVariable Long id,
            @RequestBody UserInfoDTO dto) {
        return ResponseEntity.ok(userInfoService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userInfoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/reset-password/{id}")
    public ResponseEntity<String> resetPassword(
            @PathVariable Long id,
            @RequestBody ResetPasswordRequest request) {
        userInfoService.resetPassword(id, request);
        return ResponseEntity.ok("Senha redefinida com sucesso.");
    }

    @PutMapping("/status/{id}")
    public ResponseEntity<String> changeUserStatus(@PathVariable Long id, @RequestBody StatusDTO statusDTO) {
        userInfoService.changeUserStatus(id, statusDTO.isActive());
        String status = statusDTO.isActive() ? "ativado" : "desativado";
        return ResponseEntity.ok("Usuário " + status + " com sucesso.");
    }
}