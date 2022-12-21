package com.example.SisEvento.controller;

import com.example.SisEvento.dao.EquipanteRepository;
import com.example.SisEvento.model.Equipante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/equipante")
public class EquipanteController {

    @Autowired
    private EquipanteRepository equipanteRepository;

    @GetMapping
    public List<Equipante> getEquipante() {
        return equipanteRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity getEquipanteById(@PathVariable Long id) {
        return equipanteRepository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/adicionar")
    public Equipante createEquipante (@RequestBody Equipante equipante){
        return equipanteRepository.save(equipante);
    }

    @PutMapping("/alterar/{id}")
    public ResponseEntity updateEquipante (@PathVariable("id") Long id, @RequestBody Equipante equipante){
        return equipanteRepository.findById(id)
                .map(record -> {
                    record.setNome(equipante.getNome());
                    record.setApelido(equipante.getApelido());
                    record.setDataNascimento(equipante.getDataNascimento());
                    record.setSexo(equipante.getSexo());
                    record.setEmail(equipante.getEmail());
                    record.setWhatsapp(equipante.getWhatsapp());
                    record.setMedicacao(equipante.getMedicacao());
                    record.setAlergia(equipante.getAlergia());
                    record.setRestricaoAlimentar(equipante.getRestricaoAlimentar());
                    Equipante updated = equipanteRepository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity deleteEquipante (@PathVariable Long id){
        return equipanteRepository.findById(id)
                .map(record -> {
                    equipanteRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
