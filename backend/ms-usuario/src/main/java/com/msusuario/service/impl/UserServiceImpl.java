package com.msusuario.service.impl;

import com.msusuario.entity.User;
import com.msusuario.repository.UserRepository;
import com.msusuario.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User crearUsuario(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> listarUsuarios() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> obtenerUsuario(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void eliminarUsuario(Long id) {
        userRepository.deleteById(id);
    }
}
