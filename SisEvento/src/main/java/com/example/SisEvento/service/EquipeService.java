package com.example.SisEvento.service;

import com.example.SisEvento.dao.EquipeRepository;
import com.example.SisEvento.dao.ParticipanteRepository;
import com.example.SisEvento.dto.EquipeDTO;
import com.example.SisEvento.dto.ParticipanteDTO;
import com.example.SisEvento.exception.EquipeNotFoundException;
import com.example.SisEvento.exception.ParticipanteNotFoundException;
import com.example.SisEvento.model.Equipe;
import com.example.SisEvento.model.Participante;
import com.example.SisEvento.util.CopyProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
public class EquipeService {

    @Autowired
    private EquipeRepository equipeRepository;

    public EquipeDTO getEquipe() {
        List<Equipe> equipes = equipeRepository.findAll();
        return (EquipeDTO) equipes;
    }

    public EquipeDTO getEquipeById(Long id) {
        Optional<Equipe> equipe = equipeRepository.findById(id);
        return equipe.map(u -> CopyProperties.copy(u, EquipeDTO.class)).orElseThrow(() -> new EquipeNotFoundException("Equipe não encontrada"));
    }

    public EquipeDTO createEquipe(Equipe equipe) {
        Assert.isNull(equipe.getId(),"Equipe não adicionada");
        return CopyProperties.copy(equipeRepository.save(equipe), EquipeDTO.class);
    }

    public EquipeDTO updateEquipe(Equipe equipe, Long id) {
        Assert.notNull(id,"Equipe não alterada");
        Optional<Equipe> optional = equipeRepository.findById(id);
        if(optional.isPresent()) {
            Equipe db = optional.get();
            db.setId(equipe.getId());
            System.out.println("Equipe id " + db.getId());
            equipeRepository.save(db);
            return CopyProperties.copy(equipeRepository.save(equipe), EquipeDTO.class);
        } else {
            return null;
        }
    }

    public void deleteEquipe(Long id) {
        equipeRepository.deleteById(id);
    }
}
