package com.byma.crudgerente.infrastructure.adapter.in.web.mapper;


import com.byma.crudgerente.application.validation.Validador;
import com.byma.crudgerente.domain.model.Gerente;
import com.byma.crudgerente.infrastructure.adapter.in.web.dto.request.GerenteRequestDTO;
import com.byma.crudgerente.infrastructure.adapter.in.web.dto.request.GerenteUpdateRequestDTO;
import com.byma.crudgerente.infrastructure.adapter.in.web.dto.response.GerenteResponseDTO;

public class GerenteControllerMapper {

    public static Gerente aGerente(GerenteRequestDTO gerenteRequestDTO) {

        Validador.validadorParametrosNull(gerenteRequestDTO);
        return Gerente.builder()
                .idOrganizacion(gerenteRequestDTO.getIdOrganizacion())
                .denominacion(gerenteRequestDTO.getDenominacion())
                .liquidaEnByma(gerenteRequestDTO.getLiquidaEnByma())
                .habilitado(gerenteRequestDTO.getHabilitado())
                .observaciones(gerenteRequestDTO.getObservaciones())
                .emailGerente(gerenteRequestDTO.getMailGerente())
                .fechaDeAlta(gerenteRequestDTO.getFechaDeAlta())
                .build();
    }

    public static GerenteResponseDTO aGerenteResponseDTO(Gerente gerente) {

        Validador.validadorParametrosNull(gerente);
        return GerenteResponseDTO.builder()
                .idRegistro(gerente.getIdRegistro())
                .idOrganizacion(gerente.getIdOrganizacion())
                .denominacion(gerente.getDenominacion())
                .liquidaEnByma(gerente.getLiquidaEnByma())
                .habilitado(gerente.getHabilitado())
                .observaciones(gerente.getObservaciones())
                .mailGerente(gerente.getEmailGerente())
                .fechaDeAlta(gerente.getFechaDeAlta())
                .build();
    }


    public static Gerente aGerente(GerenteUpdateRequestDTO gerenteUpdateRequestDTO) {
        return Gerente.builder()
                .liquidaEnByma(gerenteUpdateRequestDTO.getLiquidaEnByma())
                .emailGerente(gerenteUpdateRequestDTO.getEmailGerente())
                .build();
    }
}
