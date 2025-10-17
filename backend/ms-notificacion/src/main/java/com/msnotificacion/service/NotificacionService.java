package com.msnotificacion.service;

import com.msnotificacion.entity.Notificacion;

import java.util.List;

public interface NotificacionService {
    Notificacion crear(Notificacion notificacion);
    List<Notificacion> listar();
    List<Notificacion> listarPorUsuario(Long usuarioId);
    Notificacion marcarComoLeido(String id);
}
