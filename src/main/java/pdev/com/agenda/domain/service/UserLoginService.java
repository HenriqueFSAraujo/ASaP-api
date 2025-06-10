package pdev.com.agenda.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pdev.com.agenda.domain.dto.UserLoginResponse;
import pdev.com.agenda.domain.mapper.UserLoginMapper;
import pdev.com.agenda.domain.repository.UserInfoRepository;
import pdev.com.agenda.domain.repository.UserLoginRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserLoginService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private UserLoginRepository userLoginRepository;

    @Autowired
    private UserLoginMapper userLoginMapper;

    public List<UserLoginResponse> getAll() {
        return userLoginRepository.findAll()
                .stream()
                .map(userLoginMapper::toDto)
                .toList();
    }

    public Optional<UserLoginResponse> getById(Long id) {
        return userLoginRepository.findById(id)
                .map(userLoginMapper::toDto);
    }
}

