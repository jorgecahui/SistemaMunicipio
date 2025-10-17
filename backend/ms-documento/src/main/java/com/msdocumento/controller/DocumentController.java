package com.msdocumento.controller;

import com.msdocumento.entity.Document;
import com.msdocumento.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/documentos")
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @PostMapping
    public ResponseEntity<Document> registrar(@RequestBody Document document) {
        return ResponseEntity.ok(documentService.registrarDocumento(document));
    }

    @GetMapping
    public ResponseEntity<List<Document>> listar() {
        return ResponseEntity.ok(documentService.listarDocumentos());
    }

    @GetMapping("/{numero}")
    public ResponseEntity<Document> obtener(@PathVariable String numero) {
        return documentService.obtenerPorNumero(numero)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
