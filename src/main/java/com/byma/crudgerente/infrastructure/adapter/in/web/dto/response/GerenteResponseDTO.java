package com.byma.crudgerente.infrastructure.adapter.in.web.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GerenteResponseDTO {
    private Long idRegistro;
    private Long idOrganizacion;
    private String denominacion;
    private Boolean liquidaEnByma;
    private Boolean habilitado;
    private String observaciones;
    private String mailGerente;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime fechaDeAlta;
}
