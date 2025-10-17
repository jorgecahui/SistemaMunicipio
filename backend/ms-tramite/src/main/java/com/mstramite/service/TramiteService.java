package com.mstramite.service;

import com.mstramite.entity.Tramite;

import java.util.List;

public interface TramiteService {
    Tramite registrarTramite(Tramite tramite);
    List<Tramite> listarTramites();
    List<Tramite> listarPorDocumento(Long documentoId);
    Tramite actualizarEstado(Long id, String estado, String observacion);
}
