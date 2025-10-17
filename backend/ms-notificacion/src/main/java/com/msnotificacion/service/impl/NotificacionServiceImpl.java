package com.msnotificacion.service.impl;

import com.msnotificacion.entity.Notificacion;
import com.msnotificacion.repository.NotificacionRepository;
import com.msnotificacion.service.NotificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificacionServiceImpl implements NotificacionService {

    @Autowired
    private NotificacionRepository notificacionRepository;

    @Override
    public Notificacion crear(Notificacion notificacion) {
        notificacion.setFecha(LocalDateTime.now());
        notificacion.setLeido(false);
        return notificacionRepository.save(notificacion);
    }

    @Override
    public List<Notificacion> listar() {
        return notificacionRepository.findAll();
    }

    @Override
    public List<Notificacion> listarPorUsuario(Long usuarioId) {
        return notificacionRepository.findByUsuarioId(usuarioId);
    }

    @Override
    public Notificacion marcarComoLeido(String id) {
        Notificacion notif = notificacionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Notificación no encontrada"));
        notif.setLeido(true);
        return notificacionRepository.save(notif);
    }
}
