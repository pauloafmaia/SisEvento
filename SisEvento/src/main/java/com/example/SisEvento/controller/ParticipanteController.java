package com.example.SisEvento.controller;

import com.example.SisEvento.dao.ParticipanteRepository;
import com.example.SisEvento.model.Participante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/participante")
public class ParticipanteController {

    @Autowired
    private ParticipanteRepository participanteRepository;

    @GetMapping
    public List<Participante> getParticipante() {
        return participanteRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity getParticipanteById(@PathVariable Long id) {
        return participanteRepository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/adicionar")
    public Participante createParticipante (@RequestBody Participante participante){
        return participanteRepository.save(participante);
    }

    @PutMapping("/alterar/{id}")
    public ResponseEntity updateParticipante (@PathVariable("id") Long id, @RequestBody Participante participante){
        return participanteRepository.findById(id)
                .map(record -> {
                    record.setNome(participante.getNome());
                    record.setApelido(participante.getApelido());
                    record.setDataNascimento(participante.getDataNascimento());
                    record.setSexo(participante.getSexo());
                    record.setEmail(participante.getEmail());
                    record.setWhatsapp(participante.getWhatsapp());
                    record.setNomeMae(participante.getNomeMae());
                    record.setFoneMae(participante.getFoneMae());
                    record.setNomePai(participante.getNomePai());
                    record.setFonePai(participante.getFonePai());
                    record.setPessoaContato(participante.getPessoaContato());
                    record.setFoneContato(participante.getFoneContato());
                    record.setMedicacao(participante.getMedicacao());
                    record.setAlergia(participante.getAlergia());
                    record.setRestricaoAlimentar(participante.getRestricaoAlimentar());
                    Participante updated = participanteRepository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity deleteParticipante (@PathVariable Long id){
        return participanteRepository.findById(id)
                .map(record -> {
                    participanteRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
