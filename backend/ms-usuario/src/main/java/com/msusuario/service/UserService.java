package com.msusuario.service;

import com.msusuario.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User crearUsuario(User user);
    List<User> listarUsuarios();
    Optional<User> obtenerUsuario(Long id);
    void eliminarUsuario(Long id);
}
