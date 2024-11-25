package com.byma.crudgerente.infrastructure.adapter.in.web.controller;


import com.byma.crudgerente.application.exception.GerenteNoEncontradoException;
import com.byma.crudgerente.infrastructure.adapter.in.web.dto.request.GerenteRequestDTO;
import com.byma.crudgerente.infrastructure.adapter.in.web.dto.request.GerenteUpdateRequestDTO;
import com.byma.crudgerente.infrastructure.adapter.in.web.dto.response.GerenteResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "Gerente", description = "Api para gestionar sociedades gerentes")
public interface ApiGerente {

    @Operation(summary = "Crear un nuevo gerente", description = "Crea un nuevo gerente en el sistema")
    @ApiResponse(responseCode = "200", description = "Gerente creado correctamente", content = @Content(schema = @Schema(implementation = GerenteResponseDTO.class)))
    @ApiResponse(responseCode = "400", description = "Solicitud incorrecta", content = @Content)
    ResponseEntity<GerenteResponseDTO> crear(GerenteRequestDTO gerenteRequestDTO);

    @Operation(summary = "Obtener todos los gerentes", description = "Lista de todos los gerentes en el sistema")
    @ApiResponse(responseCode = "200", description = "Lista de gerentes", content = @Content(array = @ArraySchema(schema = @Schema(implementation = GerenteResponseDTO.class))))
    ResponseEntity<List<GerenteResponseDTO>> listarGerentes();

    @Operation(summary = "Obtener gerente por ID", description = "Obtiene un gerente según su ID de organización")
    @ApiResponse(responseCode = "200", description = "Gerente encontrado", content = @Content(schema = @Schema(implementation = GerenteResponseDTO.class)))
    @ApiResponse(responseCode = "404", description = "Gerente no encontrado")
    ResponseEntity<GerenteResponseDTO> obtenerGerentePorId(Long idRegistro) throws GerenteNoEncontradoException;

    @Operation(summary = "Actualizar gerente", description = "Actualiza los datos de un gerente específico")
    @ApiResponse(responseCode = "200", description = "Gerente actualizado correctamente", content = @Content(schema = @Schema(implementation = GerenteResponseDTO.class)))
    @ApiResponse(responseCode = "400", description = "Datos de solicitud inválidos")
    @ApiResponse(responseCode = "404", description = "Gerente no encontrado")
    ResponseEntity<GerenteResponseDTO> actualizar(Long idRegistro, GerenteUpdateRequestDTO gerenteUpdateRequestDTO) throws GerenteNoEncontradoException;

    @Operation(summary = "Alternar habilitación de gerente", description = "Activa o desactiva la habilitación de un gerente por su ID")
    @ApiResponse(responseCode = "200", description = "Estado de habilitación del gerente actualizado", content = @Content(schema = @Schema(implementation = GerenteResponseDTO.class)))
    @ApiResponse(responseCode = "404", description = "Gerente no encontrado")
    ResponseEntity<GerenteResponseDTO> toggleHabilitar(Long idRegistro) throws GerenteNoEncontradoException;


}
