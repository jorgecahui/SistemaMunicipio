package com.mstramite.controller;

import com.mstramite.entity.Tramite;
import com.mstramite.service.TramiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tramites")
public class TramiteController {

    @Autowired
    private TramiteService tramiteService;

    @PostMapping
    public ResponseEntity<Tramite> registrar(@RequestBody Tramite tramite) {
        return ResponseEntity.ok(tramiteService.registrarTramite(tramite));
    }

    @GetMapping
    public ResponseEntity<List<Tramite>> listar() {
        return ResponseEntity.ok(tramiteService.listarTramites());
    }

    @GetMapping("/documento/{documentoId}")
    public ResponseEntity<List<Tramite>> listarPorDocumento(@PathVariable Long documentoId) {
        return ResponseEntity.ok(tramiteService.listarPorDocumento(documentoId));
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<Tramite> actualizarEstado(
            @PathVariable Long id,
            @RequestParam String estado,
            @RequestParam(required = false) String observacion) {
        return ResponseEntity.ok(tramiteService.actualizarEstado(id, estado, observacion));
    }
}
