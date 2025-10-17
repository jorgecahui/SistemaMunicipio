package com.msauditoria.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "auditorias")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Auditoria {
    @Id
    private String id;
    private String servicio;
    private String accion;
    private String usuario;
    private LocalDateTime fecha;
    private String detalle;
}