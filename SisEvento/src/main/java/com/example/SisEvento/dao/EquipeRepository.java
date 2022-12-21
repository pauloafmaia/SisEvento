package com.example.SisEvento.dao;

import com.example.SisEvento.model.Equipe;
import com.example.SisEvento.model.Participante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipeRepository extends JpaRepository<Equipe, Long> {
}
