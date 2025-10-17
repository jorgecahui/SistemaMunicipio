package com.msauth.service.impl;

import com.msauth.dto.AuthUserDto;
import com.msauth.entity.AuthUser;
import com.msauth.entity.TokenDto;
import com.msauth.repository.AuthUserRepository;
import com.msauth.security.JwtProvider;
import com.msauth.service.AuthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class AuthUserServiceImpl implements AuthUserService {

    @Autowired
    private AuthUserRepository authUserRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public AuthUser save(AuthUserDto authUserDto) {
        Optional<AuthUser> user = authUserRepository.findByUserName(authUserDto.getUserName());
        if (user.isPresent())
            throw new ResponseStatusException(HttpStatus.CONFLICT, "El usuario ya existe");

        String password = passwordEncoder.encode(authUserDto.getPassword());
        AuthUser authUser = AuthUser.builder()
                .userName(authUserDto.getUserName())
                .password(password)
                .role(authUserDto.getRole() != null ? authUserDto.getRole() : "USER")
                .build();
        return authUserRepository.save(authUser);
    }

    @Override
    public TokenDto login(AuthUserDto authUserDto) {
        Optional<AuthUser> user = authUserRepository.findByUserName(authUserDto.getUserName());
        if (user.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado");

        if (!passwordEncoder.matches(authUserDto.getPassword(), user.get().getPassword()))
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Contraseña incorrecta");

        return TokenDto.builder()
                .token(jwtProvider.createToken(user.get()))
                .role(user.get().getRole())
                .build();
    }

    @Override
    public TokenDto validate(String token) {
        if (!jwtProvider.validate(token))
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Token inválido o expirado");
        String username = jwtProvider.getUserNameFromToken(token);
        Optional<AuthUser> user = authUserRepository.findByUserName(username);
        if (user.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado");
        return TokenDto.builder()
                .token(token)
                .role(user.get().getRole())
                .build();
    }
}
