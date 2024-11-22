package com.example.smartenergy.Sala.Dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RequestCreateSalaDto(
        @NotNull @Size(min = 1, max = 50) String id,
        String nome,

        @NotNull
        Integer capacidade,

        @NotNull
        Boolean arLigado,

        @NotNull
        Integer temperaturaAtual,

        @NotNull
        Integer potenciaAr
) {
}
