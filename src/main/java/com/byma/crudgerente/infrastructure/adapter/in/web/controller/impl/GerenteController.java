package com.byma.crudgerente.infrastructure.adapter.in.web.controller.impl;


import com.byma.crudgerente.application.exception.GerenteNoEncontradoException;
import com.byma.crudgerente.application.port.in.GerenteInPort;
import com.byma.crudgerente.application.validation.Validador;
import com.byma.crudgerente.domain.model.Gerente;
import com.byma.crudgerente.infrastructure.adapter.in.web.controller.ApiGerente;
import com.byma.crudgerente.infrastructure.adapter.in.web.dto.request.GerenteRequestDTO;
import com.byma.crudgerente.infrastructure.adapter.in.web.dto.request.GerenteUpdateRequestDTO;
import com.byma.crudgerente.infrastructure.adapter.in.web.dto.response.GerenteResponseDTO;
import com.byma.crudgerente.infrastructure.adapter.in.web.mapper.GerenteControllerMapper;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/gerentes")
@AllArgsConstructor
@Slf4j
public class GerenteController implements ApiGerente {

    private final GerenteInPort gerenteInPort;

    @PostMapping("")
    public ResponseEntity<GerenteResponseDTO> crear(@RequestBody @Valid GerenteRequestDTO gerenteRequestDTO) {

        log.info("Solicitud para crear un gerente: {}", gerenteRequestDTO);
        Validador.validadorParametrosNull(gerenteRequestDTO);
        Gerente gerenteCreado = gerenteInPort.crear(GerenteControllerMapper.aGerente(gerenteRequestDTO));
        log.info("Creacion de gerente finalizada: {}", gerenteCreado);

        return ResponseEntity.ok().body(GerenteControllerMapper.aGerenteResponseDTO(gerenteCreado));
    }

    @GetMapping("")
    public ResponseEntity<List<GerenteResponseDTO>> listarGerentes() {

        log.info("Solicitud para obtener todos los gerentes");
        List<Gerente> gerentes = gerenteInPort.listarGerentes();
        log.info("Finalizacion de la solicitud para obtener todos los gerentes");

        return ResponseEntity.ok().body(gerentes.stream().map(GerenteControllerMapper::aGerenteResponseDTO).toList());
    }

    @GetMapping("/{idRegistro}")
    public ResponseEntity<GerenteResponseDTO> obtenerGerentePorId(@PathVariable Long idRegistro) throws GerenteNoEncontradoException {

        log.info("Solicitud para obtener gerente por id de organizacion: {}", idRegistro);
        Validador.validadorParametrosNull(idRegistro);
        Gerente gerente = gerenteInPort.obtenerPorIdOrganizacionGerente(idRegistro);
        log.info("Finalizacion de obtener gerente por id de organicacion: {}", gerente);

        return ResponseEntity.ok().body(GerenteControllerMapper.aGerenteResponseDTO(gerente));
    }

    @PatchMapping("/{idRegistro}")
    public ResponseEntity<GerenteResponseDTO> actualizar(@PathVariable Long idRegistro, @RequestBody @Valid GerenteUpdateRequestDTO gerenteUpdateRequestDTO) throws GerenteNoEncontradoException {

        log.info("Solicitud para actualizar gerente por id de organizacion: {}, datos a actualizar: {}", idRegistro, gerenteUpdateRequestDTO);
        Validador.validadorParametrosNull(idRegistro, gerenteUpdateRequestDTO);
        Gerente gerenteActualizado = gerenteInPort.actualizar(idRegistro, GerenteControllerMapper.aGerente(gerenteUpdateRequestDTO));
        log.info("Finalizacion de actualizacion de gerente, gerente actualizado: {}", gerenteActualizado);

        return ResponseEntity.ok().body(GerenteControllerMapper.aGerenteResponseDTO(gerenteActualizado));
    }

    @PatchMapping("/toggle-habilitar/{idRegistro}")
    public ResponseEntity<GerenteResponseDTO> toggleHabilitar(@PathVariable Long idRegistro) throws GerenteNoEncontradoException {

        log.info("Solicitud para eliminar gerente con id de organizacion: {}", idRegistro);
        Validador.validadorParametrosNull(idRegistro);
        Gerente gerenteActualizado = gerenteInPort.toggleHabilitar(idRegistro);
        log.info("Finalizacion de eliminacion de gerente con id de organizacion: {}", idRegistro);

        return ResponseEntity.ok().body(GerenteControllerMapper.aGerenteResponseDTO(gerenteActualizado));
    }
}
