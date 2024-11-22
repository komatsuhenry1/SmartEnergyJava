package com.example.smartenergy.RegistrosTemperatura.dto;

import com.example.smartenergy.Sensor.Sensor;

import java.time.LocalDateTime;

public record RequestCreateRegistroTemperaturaDto(
        Integer temperatura,
        String timestamp,
        Sensor sensor_id
) {
}
