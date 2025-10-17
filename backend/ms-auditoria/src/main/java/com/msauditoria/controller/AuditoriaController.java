package com.msauditoria.controller;

import com.msauditoria.entity.Auditoria;
import com.msauditoria.servicio.AuditoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auditoria")
public class AuditoriaController {

    @Autowired
    private AuditoriaService service;

    @PostMapping
    public ResponseEntity<Auditoria> registrar(@RequestBody Auditoria auditoria) {
        return ResponseEntity.ok(service.registrar(auditoria));
    }

    @GetMapping
    public ResponseEntity<List<Auditoria>> listar() {
        return ResponseEntity.ok(service.listar());
    }
}
