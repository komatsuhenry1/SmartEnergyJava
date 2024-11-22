package com.example.smartenergy.Sala;

import com.example.smartenergy.Sala.Dto.RequestCreateSalaDto;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sala")
public class SalaController {

    @Autowired
    private SalaRepository salaRepository;

    @GetMapping
    public ResponseEntity<List<Sala>> findAll() {
        List<Sala> salas = salaRepository.findAll();
        return ResponseEntity.ok(salas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sala> findById(@PathVariable Long id) {
        return ResponseEntity.ok(verify(id));
    }

    @PostMapping
    public ResponseEntity<Sala> createSala(@RequestBody RequestCreateSalaDto sala) {
        Sala salaBuilded = Sala
                .builder()
                .nome(sala.nome())
                .arLigado(sala.arLigado())
                .capacidade(sala.capacidade())
                .temperaturaAtual(sala.temperaturaAtual())
                .potenciaAr(sala.potenciaAr())
                .build();
        salaRepository.save(salaBuilded);
        return ResponseEntity.ok(salaBuilded);
    }

    @PutMapping("/{salaId}")
    public ResponseEntity<Sala> update(@RequestBody Sala sala, @PathVariable Long salaId) {
        Sala salaVerified = verify(salaId);
        if(sala.getCapacidade() != null){
            salaVerified.setCapacidade(sala.getCapacidade());
        }
        if(sala.getTemperaturaAtual() != null){
            salaVerified.setTemperaturaAtual(sala.getTemperaturaAtual());
        }
        if(sala.getPotenciaAr() != null){
            salaVerified.setPotenciaAr(sala.getPotenciaAr());
        }
        if(sala.getNome() != null){
            salaVerified.setNome(sala.getNome());
        }
        if(sala.getArLigado() != null){
            salaVerified.setArLigado(sala.getArLigado());
        }
        return ResponseEntity.ok(salaVerified);
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable Long id){
        verify(id);
        salaRepository.deleteById(id);
        return "Sala " + id + " deletado com sucesso!";

    }

    private Sala verify(Long id){
        return salaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Sala não encontrada!"));
    }
}
