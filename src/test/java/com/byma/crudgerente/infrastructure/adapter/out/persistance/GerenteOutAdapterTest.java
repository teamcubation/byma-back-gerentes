package com.byma.crudgerente.infrastructure.adapter.out.persistance;

import com.byma.crudgerente.application.exception.GerenteNoEncontradoException;
import com.byma.crudgerente.domain.model.Gerente;
import com.byma.crudgerente.infrastructure.adapter.out.persistance.entity.GerenteEntity;
import com.byma.crudgerente.infrastructure.adapter.out.persistance.repository.GerenteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class GerenteOutAdapterTest {
    public static final long ID_REGISTRO_INVALIDO = 999L;
    public static final long ID_REGISTRO = 1L;
    @Mock
    private GerenteRepository gerenteRepository;

    @InjectMocks
    private GerenteOutAdapter gerenteOutAdapter;

    private Gerente gerenteMock;
    private GerenteEntity gerenteEntityMock;
    private final LocalDateTime fechaActual = LocalDateTime.now();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        gerenteMock = Gerente.builder()
                .idRegistro(1L)
                .idOrganizacion(100L)
                .denominacion("Gerente Test")
                .liquidaEnByma(true)
                .habilitado(true)
                .observaciones("Observaciones test")
                .emailGerente("test@test.com")
                .fechaDeAlta(fechaActual)
                .build();

        gerenteEntityMock = GerenteEntity.builder()
                .idRegistro(1L)
                .idOrganizacion(100L)
                .denominacion("Gerente Test")
                .liquidaEnByma(true)
                .habilitado(true)
                .observaciones("Observaciones test")
                .mailGerente("test@test.com")
                .fechaDeAlta(fechaActual)
                .build();
    }

    @Test
    void crear_DeberiaCrearGerenteExitosamente() {
        when(gerenteRepository.save(any(GerenteEntity.class))).thenReturn(gerenteEntityMock);

        Gerente resultado = gerenteOutAdapter.crear(gerenteMock);

        assertNotNull(resultado);
        assertEquals(gerenteMock.getIdRegistro(), resultado.getIdRegistro());
        assertEquals(gerenteMock.getEmailGerente(), resultado.getEmailGerente());
        verify(gerenteRepository, times(1)).save(any(GerenteEntity.class));
    }

    @Test
    void crear_ConParametroNull_DebeLanzarExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> {
            gerenteOutAdapter.crear(null);
        });
        verify(gerenteRepository, never()).save(any());
    }

    @Test
    void listarGerentes_DeberiaRetornarListaDeGerentes() {
        List<GerenteEntity> gerenteEntities = Arrays.asList(gerenteEntityMock);
        when(gerenteRepository.findAll()).thenReturn(gerenteEntities);

        List<Gerente> resultado = gerenteOutAdapter.listarGerentes();

        assertNotNull(resultado);
        assertFalse(resultado.isEmpty());
        assertEquals(1, resultado.size());
        assertEquals(gerenteEntityMock.getIdRegistro(), resultado.get(0).getIdRegistro());
        verify(gerenteRepository, times(1)).findAll();
    }

    @Test
    void obtenerPorIdOrganizacionGerente_DeberiaRetornarGerente() throws GerenteNoEncontradoException {
        Long idRegistro = ID_REGISTRO;
        when(gerenteRepository.findById(idRegistro)).thenReturn(Optional.of(gerenteEntityMock));

        Gerente resultado = gerenteOutAdapter.obtenerPorIdOrganizacionGerente(idRegistro);

        assertNotNull(resultado);
        assertEquals(gerenteEntityMock.getIdRegistro(), resultado.getIdRegistro());
        assertEquals(gerenteEntityMock.getMailGerente(), resultado.getEmailGerente());
        verify(gerenteRepository, times(1)).findById(idRegistro);
    }

    @Test
    void obtenerPorIdOrganizacionGerente_ConIdNoExistente_DebeLanzarExcepcion() {
        Long idRegistro = ID_REGISTRO_INVALIDO;
        when(gerenteRepository.findById(idRegistro)).thenReturn(Optional.empty());

        assertThrows(GerenteNoEncontradoException.class, () -> {
            gerenteOutAdapter.obtenerPorIdOrganizacionGerente(idRegistro);
        });
    }

    @Test
    void obtenerPorIdOrganizacionGerente_ConIdNull_DebeLanzarExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> {
            gerenteOutAdapter.obtenerPorIdOrganizacionGerente(null);
        });
        verify(gerenteRepository, never()).findById(any());
    }

    @Test
    void actualizar_DeberiaActualizarGerenteExitosamente() {
        when(gerenteRepository.save(any(GerenteEntity.class))).thenReturn(gerenteEntityMock);

        Gerente resultado = gerenteOutAdapter.actualizar(gerenteMock);

        assertNotNull(resultado);
        assertEquals(gerenteMock.getIdRegistro(), resultado.getIdRegistro());
        assertEquals(gerenteMock.getEmailGerente(), resultado.getEmailGerente());
        verify(gerenteRepository, times(1)).save(any(GerenteEntity.class));
    }

    @Test
    void actualizar_ConParametroNull_DebeLanzarExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> {
            gerenteOutAdapter.actualizar(null);
        });
        verify(gerenteRepository, never()).save(any());
    }

    @Test
    void eliminar_DeberiaEliminarGerenteExitosamente() {
        Long idRegistro = ID_REGISTRO;
        doNothing().when(gerenteRepository).deleteById(idRegistro);

        gerenteOutAdapter.eliminar(idRegistro);

        verify(gerenteRepository, times(1)).deleteById(idRegistro);
    }

    @Test
    void eliminar_ConIdNull_DebeLanzarExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> {
            gerenteOutAdapter.eliminar(null);
        });
        verify(gerenteRepository, never()).deleteById(any());
    }

    @Test
    void existePorIdRegistro_DeberiaRetornarTrue_CuandoExisteGerente() {
        Long idRegistro = ID_REGISTRO;
        when(gerenteRepository.existsByIdRegistro(idRegistro)).thenReturn(true);

        boolean resultado = gerenteOutAdapter.existePorIdRegistro(idRegistro);

        assertTrue(resultado);
        verify(gerenteRepository, times(1)).existsByIdRegistro(idRegistro);
    }

    @Test
    void existePorIdRegistro_DeberiaRetornarFalse_CuandoNoExisteGerente() {
        Long idRegistro = ID_REGISTRO_INVALIDO;
        when(gerenteRepository.existsByIdRegistro(idRegistro)).thenReturn(false);

        boolean resultado = gerenteOutAdapter.existePorIdRegistro(idRegistro);

        assertFalse(resultado);
        verify(gerenteRepository, times(1)).existsByIdRegistro(idRegistro);
    }
}