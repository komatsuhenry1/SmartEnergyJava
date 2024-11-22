package com.example.smartenergy.Sala;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@Data
@Entity
@Table(name = "tb_salas")
@NoArgsConstructor
@AllArgsConstructor
public class Sala {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSala;

    @NotNull
    private String nome;

    @NotNull
    private Integer capacidade;

    @NotNull
    private Boolean arLigado;

    @NotNull
    private Integer temperaturaAtual;

    @NotNull
    private Integer potenciaAr;

}
