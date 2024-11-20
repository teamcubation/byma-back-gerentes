package com.byma.crudgerente.infrastructure.adapter.out.persistance.mapper;

import com.byma.crudgerente.application.validation.Validador;
import com.byma.crudgerente.domain.model.Gerente;
import com.byma.crudgerente.infrastructure.adapter.out.persistance.entity.GerenteEntity;
import java.util.List;
import java.util.stream.Collectors;

public class GerenteMapper {

    public static GerenteEntity aGerenteEntity(Gerente gerente) {
        Validador.validadorParametrosNull(gerente);

        return GerenteEntity.builder()
                .idOrganizacion(gerente.getIdOrganizacion())
                .idRegistro(gerente.getIdRegistro())
                .denominacion(gerente.getDenominacion())
                .liquidaEnByma(gerente.getLiquidaEnByma())
                .habilitado(gerente.getHabilitado())
                .observaciones(gerente.getObservaciones())
                .mailGerente(gerente.getEmailGerente())
                .fechaDeAlta(gerente.getFechaDeAlta())
                .build();
    }


    public static Gerente aGerente(GerenteEntity gerenteEntity) {
        Validador.validadorParametrosNull(gerenteEntity);
        return Gerente.builder()
                .idOrganizacion(gerenteEntity.getIdOrganizacion())
                .idRegistro(gerenteEntity.getIdRegistro())
                .denominacion(gerenteEntity.getDenominacion())
                .liquidaEnByma(gerenteEntity.isLiquidaEnByma())
                .habilitado(gerenteEntity.isHabilitado())
                .observaciones(gerenteEntity.getObservaciones())
                .emailGerente(gerenteEntity.getMailGerente())
                .fechaDeAlta(gerenteEntity.getFechaDeAlta())
                .build();
    }
    public static List<Gerente> aGerentes(List<GerenteEntity> gerenteEntities) {
        return gerenteEntities.stream()
                .map(GerenteMapper::aGerente)
                .collect(Collectors.toList());
    }
}
