package com.example.smartenergy.RegistrosTemperatura;

import com.example.smartenergy.RegistrosTemperatura.dto.RequestCreateRegistroTemperaturaDto;
import com.example.smartenergy.Sensor.Sensor;
import com.example.smartenergy.Sensor.SensorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/registrostemperatura")
public class RegistrosTemperaturaController {

    @Autowired
    private RegistrosTemperaturaRepository registrosTemperaturaRepository;

    @Autowired
    private SensorRepository sensorRepository;

    @GetMapping
    public ResponseEntity<String> getRegistrosTemperatura() {
        return ResponseEntity.ok(registrosTemperaturaRepository.toString());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegistrosTemperatura> getRegistrosTemperaturaById(@PathVariable Long id) {
        return ResponseEntity.ok(verify(id));
    }

    @PostMapping
    public ResponseEntity<RegistrosTemperatura> createRegistroTemperatura(@RequestBody RequestCreateRegistroTemperaturaDto registroTemperatura){
        Sensor sensor = sensorRepository.findById(registroTemperatura.sensor_id().getId_sensores()).orElseThrow(() -> new EntityNotFoundException("sensor não encontrado!"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dateTimeString = registroTemperatura.timestamp().contains(" ")
                ? registroTemperatura.timestamp()
                : registroTemperatura.timestamp() + " 00:00:00";

        LocalDateTime timestamp = LocalDateTime.parse(dateTimeString, formatter);

        RegistrosTemperatura registrosTemperatura = RegistrosTemperatura
                .builder()
                .temperatura(registroTemperatura.temperatura())
                .sensor_id(sensor)
                .timestamp(timestamp)
                .build();
        return ResponseEntity.ok(registrosTemperaturaRepository.save(registrosTemperatura));
    }

    @PutMapping("{id}")
    public ResponseEntity<RegistrosTemperatura> UpdateResgistrosMovimentos(@PathVariable Long id, @RequestBody RegistrosTemperatura registrosTemperatura){
        RegistrosTemperatura registrosTemperaturaVerified = verify(id);
        if(registrosTemperatura.getTemperatura() != null){
            registrosTemperaturaVerified.setTemperatura(registrosTemperatura.getTemperatura());
        }
        if(registrosTemperatura.getTimestamp() != null){
            registrosTemperaturaVerified.setTimestamp(registrosTemperatura.getTimestamp());
        }
        if(registrosTemperatura.getSensor_id() != null){
            registrosTemperaturaVerified.setSensor_id(registrosTemperatura.getSensor_id());
        }
        return(ResponseEntity.ok(registrosTemperaturaVerified));
    }

    @DeleteMapping("/{id}")
    public String DeleteRegistrosTemperatura(@PathVariable Long id){
        verify(id);
        registrosTemperaturaRepository.deleteById(id);
        return "Registro de temperatura deletado com sucesso!";
    }

    public RegistrosTemperatura verify(Long id) {
        return(registrosTemperaturaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Registro de temperatura não encontrada!")));
    }
}
