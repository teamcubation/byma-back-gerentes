package com.byma.crudgerente.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Gerente {

    private Long idGerente;
    private String denominacion;
    @Builder.Default
    private Boolean liquidaEnByma = true;
    private Boolean habilitado;
    private String observaciones;
    private String emailGerente;
    @Builder.Default
    private LocalDateTime fechaDeAlta = LocalDateTime.now();
}
