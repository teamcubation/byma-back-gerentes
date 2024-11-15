package com.byma.crudgerente.application.service;


import com.byma.crudgerente.application.exception.GerenteNoEncontradoException;
import com.byma.crudgerente.application.port.in.GerenteInPort;
import com.byma.crudgerente.application.port.out.GerenteOutPort;
import com.byma.crudgerente.application.validation.Validador;
import com.byma.crudgerente.domain.model.Gerente;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class GerenteService implements GerenteInPort {

    private final GerenteOutPort gerenteOutPort;

    public GerenteService(GerenteOutPort gerenteOutPort) {
        this.gerenteOutPort = gerenteOutPort;
    }

    @Override
    public Gerente crear(Gerente gerenteRequestDTO) {
        log.info("Crear gerente: {}", gerenteRequestDTO);
        Validador.validadorParametrosNull(gerenteRequestDTO);
        return gerenteOutPort.crear(gerenteRequestDTO);
    }

    @Override
    public List<Gerente> listarGerentes() {
        log.info("Obtener todos los gerentes");
        return gerenteOutPort.listarGerentes();
    }

    @Override
    public Gerente obtenerPorIdOrganizacionGerente(Long idRegistro) throws GerenteNoEncontradoException {
        log.info("Obtener gerente por el id de organizacion: {}", idRegistro);
        Validador.validadorParametrosNull(idRegistro);
        return gerenteOutPort.obtenerPorIdOrganizacionGerente(idRegistro);
    }

    @Override
    public Gerente actualizar(Long idRegistro, Gerente gerenteRequestDTO) throws GerenteNoEncontradoException {
        log.info("Actualizar gerente con id de organizacion: {}, datos a actualizar: {}", idRegistro, gerenteRequestDTO);
        Validador.validadorParametrosNull(idRegistro, gerenteRequestDTO);
        Gerente gerenteDb = this.gerenteOutPort.obtenerPorIdOrganizacionGerente(idRegistro);
        gerenteDb.setEmailGerente(gerenteRequestDTO.getEmailGerente());
        gerenteDb.setLiquidaEnByma(gerenteRequestDTO.getLiquidaEnByma());

        return gerenteOutPort.actualizar(gerenteDb);
    }

    @Override
    public Gerente toggleHabilitar(Long idRegistro) throws GerenteNoEncontradoException {
        log.info("Eliminar gerente");
        Validador.validadorParametrosNull(idRegistro);
        Gerente gerenteDb = this.gerenteOutPort.obtenerPorIdOrganizacionGerente(idRegistro);
        gerenteDb.setHabilitado(!gerenteDb.getHabilitado());
        return gerenteOutPort.actualizar(gerenteDb);
    }
}
