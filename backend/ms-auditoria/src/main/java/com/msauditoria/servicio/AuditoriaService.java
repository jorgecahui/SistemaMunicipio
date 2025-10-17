package com.msauditoria.servicio;

import com.msauditoria.entity.Auditoria;

import java.util.List;

public interface AuditoriaService {
    Auditoria registrar(Auditoria auditoria);
    List<Auditoria> listar();
}