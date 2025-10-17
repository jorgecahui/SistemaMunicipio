package com.msauditoria.servicio.impl;

import com.msauditoria.entity.Auditoria;
import com.msauditoria.repository.AuditoriaRepository;
import com.msauditoria.servicio.AuditoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AuditoriaServiceImpl implements AuditoriaService {

    @Autowired
    private AuditoriaRepository repository;

    @Override
    public Auditoria registrar(Auditoria auditoria) {
        auditoria.setFecha(LocalDateTime.now());
        return repository.save(auditoria);
    }

    @Override
    public List<Auditoria> listar() {
        return repository.findAll();
    }
}