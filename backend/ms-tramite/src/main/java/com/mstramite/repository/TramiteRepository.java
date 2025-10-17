package com.mstramite.repository;

import com.mstramite.entity.Tramite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TramiteRepository extends JpaRepository<Tramite, Long> {
    List<Tramite> findByDocumentoId(Long documentoId);
}
