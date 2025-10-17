package com.msauditoria.repository;

import com.msauditoria.entity.Auditoria;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AuditoriaRepository extends MongoRepository<Auditoria, String> {
    List<Auditoria> findByServicio(String servicio);
}
