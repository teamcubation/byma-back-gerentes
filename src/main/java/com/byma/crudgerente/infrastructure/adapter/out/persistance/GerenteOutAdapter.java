package com.byma.crudgerente.infrastructure.adapter.out.persistance;


import com.byma.crudgerente.application.exception.GerenteNoEncontradoException;
import com.byma.crudgerente.application.port.out.GerenteOutPort;
import com.byma.crudgerente.application.validation.Validador;
import com.byma.crudgerente.domain.model.Gerente;
import com.byma.crudgerente.infrastructure.adapter.out.persistance.mapper.GerenteMapper;
import com.byma.crudgerente.infrastructure.adapter.out.persistance.repository.GerenteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class GerenteOutAdapter implements GerenteOutPort {

    private final GerenteRepository gerenteRepository;
    @Override
    public Gerente crear(Gerente gerente) {
        Validador.validadorParametrosNull(gerente);
        return GerenteMapper.gerenteEntityAGerente(gerenteRepository.save(GerenteMapper.gerenteAGerenteEntity(gerente)));
    }

    @Override
    public List<Gerente> listarGerentes() {
        return GerenteMapper.gerenteEntitiesAGerentes(gerenteRepository.findAll());
    }

    @Override
    public Gerente obtenerPorIdOrganizacionGerente(Long idRegistro) throws GerenteNoEncontradoException {
        Validador.validadorParametrosNull(idRegistro);
        return GerenteMapper.gerenteEntityAGerente(gerenteRepository.findById(idRegistro).orElseThrow(() -> new GerenteNoEncontradoException("Gerente no encontrado")));
    }

    @Override
    public Gerente actualizar(Gerente gerente) {
        Validador.validadorParametrosNull(gerente);
        return GerenteMapper.gerenteEntityAGerente(gerenteRepository.save(GerenteMapper.gerenteAGerenteEntity(gerente)));
    }

    @Override
    public void eliminar(Long idOrganizacionGerente) {
        Validador.validadorParametrosNull(idOrganizacionGerente);
        gerenteRepository.deleteById(idOrganizacionGerente);
    }

    @Override
    public boolean existePorIdRegistro(Long idRegistro) {
        return this.gerenteRepository.existsByIdRegistro(idRegistro);
    }


}
