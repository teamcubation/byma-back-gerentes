package com.byma.crudgerente.application.port.out;
import com.byma.crudgerente.application.exception.GerenteNoEncontradoException;
import com.byma.crudgerente.domain.model.Gerente;
import java.util.List;

public interface GerenteOutPort {

    Gerente crear(Gerente gerente);

    List<Gerente> listarGerentes();

    Gerente obtenerPorIdOrganizacionGerente(Long idRegistro) throws GerenteNoEncontradoException;

    Gerente actualizar(Gerente gerente);

    void eliminar(Long idOrganizacionGerente);

    boolean existePorIdRegistro(Long idRegistro);
}
