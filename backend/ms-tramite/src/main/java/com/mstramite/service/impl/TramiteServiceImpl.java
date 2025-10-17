package com.mstramite.service.impl;

import com.mstramite.entity.Tramite;
import com.mstramite.repository.TramiteRepository;
import com.mstramite.service.TramiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
public class TramiteServiceImpl implements TramiteService {

    @Autowired
    private TramiteRepository tramiteRepository;

    @Override
    public Tramite registrarTramite(Tramite tramite) {
        tramite.setFechaEnvio(LocalDate.now());
        tramite.setEstado("Enviado");
        return tramiteRepository.save(tramite);
    }

    @Override
    public List<Tramite> listarTramites() {
        return tramiteRepository.findAll();
    }

    @Override
    public List<Tramite> listarPorDocumento(Long documentoId) {
        return tramiteRepository.findByDocumentoId(documentoId);
    }

    @Override
    public Tramite actualizarEstado(Long id, String estado, String observacion) {
        Tramite tramite = tramiteRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Trámite no encontrado"));
        tramite.setEstado(estado);
        tramite.setObservacion(observacion);
        tramite.setFechaRecepcion(LocalDate.now());
        return tramiteRepository.save(tramite);
    }
}