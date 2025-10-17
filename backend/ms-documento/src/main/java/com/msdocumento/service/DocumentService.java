package com.msdocumento.service;

import com.msdocumento.entity.Document;

import java.util.List;
import java.util.Optional;

public interface DocumentService {
    Document registrarDocumento(Document document);
    List<Document> listarDocumentos();
    Optional<Document> obtenerPorNumero(String numero);
}
