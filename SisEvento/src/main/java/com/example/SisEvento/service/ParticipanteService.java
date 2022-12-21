package com.example.SisEvento.service;

import com.example.SisEvento.dao.ParticipanteRepository;
import com.example.SisEvento.dto.ParticipanteDTO;
import com.example.SisEvento.exception.ParticipanteNotFoundException;
import com.example.SisEvento.model.Participante;
import com.example.SisEvento.util.CopyProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
public class ParticipanteService {

    @Autowired
    private ParticipanteRepository participanteRepository;

    public ParticipanteDTO getParticipante() {
        List<Participante> participantes = participanteRepository.findAll();
        return (ParticipanteDTO) participantes;
    }

    public ParticipanteDTO getParticipanteById(Long id) {
        Optional<Participante> participantes = participanteRepository.findById(id);
        return participantes.map(u -> CopyProperties.copy(u, ParticipanteDTO.class)).orElseThrow(() -> new ParticipanteNotFoundException("Participante não encontrado"));
    }

    public ParticipanteDTO createParticipante(Participante participante) {
        Assert.isNull(participante.getId(),"Participante não adicionado");
        return CopyProperties.copy(participanteRepository.save(participante), ParticipanteDTO.class);
    }

    public ParticipanteDTO updateParticipante(Participante participante, Long id) {
        Assert.notNull(id,"Participante não alterado");
        Optional<Participante> optional = participanteRepository.findById(id);
        if(optional.isPresent()) {
            Participante db = optional.get();
            db.setId(participante.getId());
            System.out.println("Participante id " + db.getId());
            participanteRepository.save(db);
            return CopyProperties.copy(participanteRepository.save(participante), ParticipanteDTO.class);
        } else {
            return null;
        }
    }

    public void deleteParticipante(Long id) {
        participanteRepository.deleteById(id);
    }
}
