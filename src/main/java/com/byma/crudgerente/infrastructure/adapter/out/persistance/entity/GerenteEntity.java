package com.byma.crudgerente.infrastructure.adapter.out.persistance.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "gerentes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GerenteEntity {


    private Long idOrganizacion;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRegistro;
    @Column(nullable = false)
    private String denominacion;
    @Column(nullable = false)
    private boolean liquidaEnByma = true;
    @Column(nullable = false)
    private boolean habilitado;
    @Column(nullable = false)
    private String observaciones;
    private String mailGerente;
    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime fechaDeAlta;
}
