package com.example.SisEvento.controller;

import com.example.SisEvento.dao.EquipeRepository;
import com.example.SisEvento.model.Equipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/equipe")
public class EquipeController {

    @Autowired
    private EquipeRepository equipeRepository;

    @GetMapping
    public List<Equipe> getEquipe() {
        return equipeRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity getEquipeById(@PathVariable Long id) {
        return equipeRepository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/adicionar")
    public Equipe createEquipe (@RequestBody Equipe equipe){
        return equipeRepository.save(equipe);
    }

    @PutMapping("/alterar/{id}")
    public ResponseEntity updateEquipe (@PathVariable("id") Long id, @RequestBody Equipe equipe){
        return equipeRepository.findById(id)
                .map(record -> {
                    record.setNome(equipe.getNome());
                    Equipe updated = equipeRepository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity deleteEquipe (@PathVariable Long id){
        return equipeRepository.findById(id)
                .map(record -> {
                    equipeRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
