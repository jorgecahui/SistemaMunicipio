package com.mstramite.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tramites")
public class Tramite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long documentoId;
    private Long usuarioOrigenId;
    private Long usuarioDestinoId;
    private String estado;
    private String observacion;
    private LocalDate fechaEnvio;
    private LocalDate fechaRecepcion;
}
