package com.example.SisEvento.service;

import com.example.SisEvento.dao.EquipanteRepository;
import com.example.SisEvento.dto.EquipanteDTO;
import com.example.SisEvento.exception.EquipanteNotFoundException;
import com.example.SisEvento.model.Equipante;
import com.example.SisEvento.util.CopyProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
public class EquipanteService {

    @Autowired
    private EquipanteRepository equipanteRepository;

    public EquipanteDTO getEquipante() {
        List<Equipante> equipantes = equipanteRepository.findAll();
        return (EquipanteDTO) equipantes;
    }

    public EquipanteDTO getEquipanteById(Long id) {
        Optional<Equipante> equipantes = equipanteRepository.findById(id);
        return equipantes.map(u -> CopyProperties.copy(u, EquipanteDTO.class)).orElseThrow(() -> new EquipanteNotFoundException("Equipante não encontrado"));
    }

    public EquipanteDTO createEquipante(Equipante equipante) {
        Assert.isNull(equipante.getId(),"Equipante não adicionado");
        return CopyProperties.copy(equipanteRepository.save(equipante), EquipanteDTO.class);
    }

    public EquipanteDTO updateEquipante(Equipante equipante, Long id) {
        Assert.notNull(id,"Equipante não alterado");
        Optional<Equipante> optional = equipanteRepository.findById(id);
        if(optional.isPresent()) {
            Equipante db = optional.get();
            db.setId(equipante.getId());
            System.out.println("Equipante id " + db.getId());
            equipanteRepository.save(db);
            return CopyProperties.copy(equipanteRepository.save(equipante), EquipanteDTO.class);
        } else {
            return null;
        }
    }

    public void deleteEquipante(Long id) {
        equipanteRepository.deleteById(id);
    }
}
