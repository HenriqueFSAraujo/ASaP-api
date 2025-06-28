package pdev.com.agenda.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pdev.com.agenda.config.TokenService;
import pdev.com.agenda.domain.dto.UserLoginRequest;
import pdev.com.agenda.domain.dto.UserLoginResponse;
import pdev.com.agenda.domain.entity.UserInfo;
import pdev.com.agenda.domain.repository.UserLoginRepository;

@Service
public class AuthService {

    @Autowired
    private UserLoginRepository userLoginRepository;

    @Autowired
    private TokenService jwtUtil;

    public UserLoginResponse login(UserLoginRequest request) {
        UserInfo user = userLoginRepository.findByUserName(request.getUserName())
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        if (!user.getPassword().equals(request.getPassword())) {
            throw new BadCredentialsException("Senha inválida");
        }
        String token = jwtUtil.generateToken(user);
        user.setToken(token);
        userLoginRepository.save(user);

        return new UserLoginResponse(user.getUserName(), user.getRole().getName(),user);
    }
}