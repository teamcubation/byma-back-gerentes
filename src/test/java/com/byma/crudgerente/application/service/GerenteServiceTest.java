package com.byma.crudgerente.application.service;

import com.byma.crudgerente.application.exception.GerenteNoEncontradoException;
import com.byma.crudgerente.application.port.out.GerenteOutPort;
import com.byma.crudgerente.domain.model.Gerente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class GerenteServiceTest {

    @Mock
    private GerenteOutPort gerenteOutPort;

    @InjectMocks
    private GerenteService gerenteService;

    private Gerente gerenteMock;
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
    }

    @Test
    void crear_DeberiaCrearGerenteExitosamente() {
        when(gerenteOutPort.crear(any(Gerente.class))).thenReturn(gerenteMock);

        Gerente resultado = gerenteService.crear(gerenteMock);

        assertNotNull(resultado);
        assertEquals(gerenteMock.getIdRegistro(), resultado.getIdRegistro());
        assertEquals(gerenteMock.getEmailGerente(), resultado.getEmailGerente());
        verify(gerenteOutPort, times(1)).crear(any(Gerente.class));
    }

    @Test
    void crear_ConParametroNull_DebeLanzarExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> {
            gerenteService.crear(null);
        });
        verify(gerenteOutPort, never()).crear(any());
    }

    @Test
    void listarGerentes_DeberiaRetornarListaDeGerentes() {
        List<Gerente> gerentes = Arrays.asList(gerenteMock);
        when(gerenteOutPort.listarGerentes()).thenReturn(gerentes);

        List<Gerente> resultado = gerenteService.listarGerentes();

        assertNotNull(resultado);
        assertFalse(resultado.isEmpty());
        assertEquals(1, resultado.size());
        verify(gerenteOutPort, times(1)).listarGerentes();
    }

    @Test
    void obtenerPorIdOrganizacionGerente_DeberiaRetornarGerente() throws GerenteNoEncontradoException {
        Long idRegistro = 1L;
        when(gerenteOutPort.obtenerPorIdOrganizacionGerente(idRegistro)).thenReturn(gerenteMock);

        Gerente resultado = gerenteService.obtenerPorIdOrganizacionGerente(idRegistro);

        assertNotNull(resultado);
        assertEquals(gerenteMock.getIdRegistro(), resultado.getIdRegistro());
        verify(gerenteOutPort, times(1)).obtenerPorIdOrganizacionGerente(idRegistro);
    }

    @Test
    void obtenerPorIdOrganizacionGerente_ConIdNoExistente_DebeLanzarExcepcion() throws GerenteNoEncontradoException {
        Long idRegistro = 999L;
        when(gerenteOutPort.obtenerPorIdOrganizacionGerente(idRegistro))
                .thenThrow(new GerenteNoEncontradoException("Gerente no encontrado"));

        assertThrows(GerenteNoEncontradoException.class, () -> {
            gerenteService.obtenerPorIdOrganizacionGerente(idRegistro);
        });
    }

    @Test
    void actualizar_DeberiaActualizarGerenteExitosamente() throws GerenteNoEncontradoException {
        Long idRegistro = 1L;
        Gerente gerenteActualizado = Gerente.builder()
                .idRegistro(idRegistro)
                .emailGerente("nuevo@test.com")
                .liquidaEnByma(false)
                .build();

        when(gerenteOutPort.obtenerPorIdOrganizacionGerente(idRegistro)).thenReturn(gerenteMock);
        when(gerenteOutPort.actualizar(any(Gerente.class))).thenReturn(gerenteActualizado);

        Gerente resultado = gerenteService.actualizar(idRegistro, gerenteActualizado);

        assertNotNull(resultado);
        assertEquals("nuevo@test.com", resultado.getEmailGerente());
        assertFalse(resultado.getLiquidaEnByma());
        verify(gerenteOutPort, times(1)).obtenerPorIdOrganizacionGerente(idRegistro);
        verify(gerenteOutPort, times(1)).actualizar(any(Gerente.class));
    }

    @Test
    void actualizar_ConParametrosNull_DebeLanzarExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> {
            gerenteService.actualizar(null, gerenteMock);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            gerenteService.actualizar(1L, null);
        });
    }

    @Test
    void toggleHabilitar_DeberiaAlternarEstadoHabilitado() throws GerenteNoEncontradoException {

        Long idRegistro = 1L;
        boolean estadoInicial = gerenteMock.getHabilitado();
        Gerente gerenteDeshabilitado = Gerente.builder()
                .idRegistro(idRegistro)
                .habilitado(!estadoInicial)
                .build();

        when(gerenteOutPort.obtenerPorIdOrganizacionGerente(idRegistro)).thenReturn(gerenteMock);
        when(gerenteOutPort.actualizar(any(Gerente.class))).thenReturn(gerenteDeshabilitado);

        Gerente resultado = gerenteService.toggleHabilitar(idRegistro);

        assertNotNull(resultado);
        assertNotEquals(estadoInicial, resultado.getHabilitado());
        verify(gerenteOutPort, times(1)).obtenerPorIdOrganizacionGerente(idRegistro);
        verify(gerenteOutPort, times(1)).actualizar(any(Gerente.class));
    }

    @Test
    void toggleHabilitar_ConIdNull_DebeLanzarExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> {
            gerenteService.toggleHabilitar(null);
        });
    }

    @Test
    void toggleHabilitar_ConGerenteNoEncontrado_DebeLanzarExcepcion() throws GerenteNoEncontradoException {
        Long idRegistro = 999L;
        when(gerenteOutPort.obtenerPorIdOrganizacionGerente(idRegistro))
                .thenThrow(new GerenteNoEncontradoException("Gerente no encontrado"));

        assertThrows(GerenteNoEncontradoException.class, () -> {
            gerenteService.toggleHabilitar(idRegistro);
        });
    }
}