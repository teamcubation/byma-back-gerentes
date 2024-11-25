package com.byma.crudgerente.infrastructure.adapter.in.web.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GerenteRequestDTO {
    private Long idGerente;
    @NotNull
    @NotBlank
    private String denominacion;
    @NotNull
    private Boolean liquidaEnByma;
    @NotNull
    private Boolean habilitado;
    private String observaciones;
    @Email
    @NotBlank
    private String mailGerente;
    @Builder.Default
    private LocalDateTime fechaDeAlta = LocalDateTime.now();

}
