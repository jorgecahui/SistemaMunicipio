package com.msstorage.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
    String guardar(MultipartFile file);
    Resource descargar(String filename);
    void eliminar(String filename);
}
