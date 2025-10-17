package com.msdocumento.service.impl;

import com.msdocumento.entity.Document;
import com.msdocumento.repository.DocumentRepository;
import com.msdocumento.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    @Override
    public Document registrarDocumento(Document document) {
        document.setFechaRegistro(LocalDate.now());
        document.setEstado("En trámite");
        return documentRepository.save(document);
    }

    @Override
    public List<Document> listarDocumentos() {
        return documentRepository.findAll();
    }

    @Override
    public Optional<Document> obtenerPorNumero(String numero) {
        return documentRepository.findByNumero(numero);
    }
}