package com.example.bankapi.repositories;

import com.example.bankapi.models.ContoBancario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ContoRepository extends JpaRepository<ContoBancario, Long> {
    List<ContoBancario> findByIntestatarioContainingIgnoreCase(String nome);

    List<ContoBancario> findByTipoConto(String tipo);
}