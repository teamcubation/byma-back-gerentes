package com.byma.crudgerente.infrastructure.adapter.in.web.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GerenteUpdateRequestDTO {

    @NotNull
    private Boolean liquidaEnByma;
    @NotNull
    @NotBlank
    @Email
    private String emailGerente;
}
