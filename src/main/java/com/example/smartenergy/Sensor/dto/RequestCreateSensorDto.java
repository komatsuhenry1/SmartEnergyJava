package com.example.smartenergy.Sensor.dto;

import jakarta.validation.constraints.NotNull;

public record RequestCreateSensorDto(
        @NotNull
        String tipo,

        @NotNull
        Long sala_id
) {
}
