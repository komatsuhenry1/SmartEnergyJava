package com.example.smartenergy.RegistrosMovimentos;

import com.example.smartenergy.RegistrosMovimentos.dto.RequestCreateRegistroMovimentoDto;
import com.example.smartenergy.Sensor.Sensor;
import com.example.smartenergy.Sensor.SensorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/registroMovimento")
public class RegistrosMovimentosController {

    @Autowired
    private RegistrosMovimentosRepository registrosMovimentosRepository;

    @Autowired
    private SensorRepository sensorRepository;

    @GetMapping
    public ResponseEntity<List<RegistrosMovimentos>> listarRegistrosMovimentos() {
        return ResponseEntity.ok(registrosMovimentosRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegistrosMovimentos> buscarRegistrosMovimentos(@PathVariable Integer id) {
        return ResponseEntity.ok(verifyRegistrosMovimentos(id));
    }

    @PostMapping
    public ResponseEntity<RegistrosMovimentos> createRegistroMovimentos(@RequestBody RequestCreateRegistroMovimentoDto registrosMovimentos) {
        Sensor sensor = sensorRepository.findById(registrosMovimentos.sensor_id()).orElseThrow(() -> new EntityNotFoundException("sensor não encontrado!"));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        String dateTimeString = registrosMovimentos.timestamp().contains(" ")
                ? registrosMovimentos.timestamp()
                : registrosMovimentos.timestamp() + " 00:00:00";

        LocalDateTime timestamp = LocalDateTime.parse(dateTimeString, formatter);

        RegistrosMovimentos registrosMovimentoss = RegistrosMovimentos
                .builder()
                .sensor_id(sensor)
                .detectado(registrosMovimentos.detectado())
                .timestamp(timestamp)
                .build();
        return ResponseEntity.ok(registrosMovimentosRepository.save(registrosMovimentoss));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateRegistrosMovimentos(@PathVariable Integer id,@RequestBody RegistrosMovimentos registrosMovimentos) {
        RegistrosMovimentos registrosMovimentoVerified = verifyRegistrosMovimentos(id);
        if(registrosMovimentos.getDetectado() != null){
            registrosMovimentoVerified.setDetectado(registrosMovimentos.getDetectado());
        }
        if(registrosMovimentos.getTimestamp() != null){
            registrosMovimentoVerified.setTimestamp(registrosMovimentos.getTimestamp());
        }
        return ResponseEntity.ok(registrosMovimentoVerified);
    }

    @DeleteMapping("/{id}")
    public String DeleteRegistroMovimento(@PathVariable Integer id){
        verifyRegistrosMovimentos(id);
        registrosMovimentosRepository.deleteById(id);
        return "Registro de movimento deletado com sucesso!";
    }

    private RegistrosMovimentos verifyRegistrosMovimentos(Integer registroId) {
        return registrosMovimentosRepository.findById(registroId).orElseThrow(() -> new EntityNotFoundException("Registro de movimento não encontrado  "));
    }
}