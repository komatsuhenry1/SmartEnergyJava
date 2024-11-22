package com.example.smartenergy.Sensor;

import com.example.smartenergy.Sala.Sala;
import com.example.smartenergy.Sala.SalaRepository;
import com.example.smartenergy.Sensor.dto.RequestCreateSensorDto;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/sensor")
public class SensorController {

    @Autowired
    private SensorRepository sensorRepository;

    @Autowired
    private SalaRepository salaRepository;

    @GetMapping
    public ResponseEntity<List<Sensor>> findAll() {
        List<Sensor> listaSensor = sensorRepository.findAll();
        return ResponseEntity.ok(listaSensor);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sensor> getSensorById(@PathVariable Integer id) {
        return ResponseEntity.ok(verifySensorById(id));
    }

    @PostMapping
    public ResponseEntity<RequestCreateSensorDto> createSensor(@RequestBody RequestCreateSensorDto sensor) {
        Sala sala = salaRepository.findById(sensor.sala_id()).orElseThrow(() -> new EntityNotFoundException("sala não encontrada"));
        Sensor sensorBuilded = Sensor
                .builder()
                .tipo(sensor.tipo())
                .sala_id(sala)
                .build();
        sensorRepository.save(sensorBuilded);
        return ResponseEntity.ok(sensor);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Sensor> updateSensor(@PathVariable Integer id, @RequestBody Sensor sensor) {
        Sensor sensorVerified = verifySensorById(id);
        if(sensor.getTipo() != null){
            sensorVerified.setTipo(sensor.getTipo());
        }
        return ResponseEntity.ok(sensorVerified);
    }

    @DeleteMapping("{id}")
    public String DeleteSensor(@PathVariable Integer id) {
        verifySensorById(id);
        sensorRepository.deleteById(id);
        return "Sensor deletado com sucesso";
    }

    private Sensor verifySensorById(Integer id){
        return sensorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Sensor não encontrado!"));
    }
}