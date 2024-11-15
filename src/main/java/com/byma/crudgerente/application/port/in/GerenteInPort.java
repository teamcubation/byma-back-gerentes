package com.byma.crudgerente.application.port.in;

import com.byma.crudgerente.application.exception.GerenteNoEncontradoException;
import com.byma.crudgerente.domain.model.Gerente;

import java.util.List;

public interface GerenteInPort {

    Gerente crear(Gerente gerenteRequestDTO);

    List<Gerente> listarGerentes();

    Gerente obtenerPorIdOrganizacionGerente(Long idRegistro) throws GerenteNoEncontradoException;

    Gerente actualizar(Long idRegistro,Gerente gerenteRequestDTO) throws GerenteNoEncontradoException;

    Gerente toggleHabilitar(Long idRegistro) throws GerenteNoEncontradoException;
}
