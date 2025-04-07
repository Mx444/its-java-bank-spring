package com.example.bankapi.providers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bankapi.models.ContoBancario;
import com.example.bankapi.repositories.ContoRepository;

@Service
public class ContoService {
    @Autowired
    private ContoRepository contoRepository;

    public List<ContoBancario> findAll() {
        try {
            return contoRepository.findAll();
        } catch (Exception e) {
            throw new Error("[Hibernate ORM] : Errore getTuttiIConti", e);
        }
    }

    public String create(ContoBancario conto) {
        if (conto == null)
            throw new IllegalArgumentException("Il conto non può essere nullo");

        if (conto.getIntestatario() == null || conto.getIntestatario().isEmpty())
            throw new IllegalArgumentException("L'intestatario non può essere nullo o vuoto");

        if (conto.getTipoConto() == null)
            throw new IllegalArgumentException("Il tipo di conto non può essere nullo o vuoto");

        if (conto.getSaldo() < 0)
            throw new IllegalArgumentException("Il saldo non può essere negativo");

        try {

            List<ContoBancario> contoEsistente = contoRepository
                    .findByIntestatarioContainingIgnoreCase(conto.getIntestatario());

            if (contoEsistente != null && !contoEsistente.isEmpty())
                throw new IllegalArgumentException(
                        "Conto già esistente per l'intestatario: " + conto.getIntestatario());

            contoRepository.save(conto);

            return "Conto aggiunto con successo";
        } catch (Exception e) {
            throw new Error("[Hibernate ORM] : Errore aggiungiConto", e);
        }
    }

    public String delete(long id) {
        if (id <= 0)
            throw new IllegalArgumentException("L'ID del conto non può essere nullo o negativo");
        try {
            if (contoRepository.existsById(id)) {
                contoRepository.deleteById(id);
                return "Conto eliminato.";
            } else {
                return "Conto non trovato.";
            }
        } catch (Exception e) {
            throw new Error("[Hibernate ORM] : Errore delete", e);

        }
    }

    public List<ContoBancario> findByName(String nome) {
        if (nome == null || nome.isEmpty())
            throw new IllegalArgumentException("Il nome non può essere nullo o vuoto");
        try {
            List<ContoBancario> conto = contoRepository.findByIntestatarioContainingIgnoreCase(nome);
            if (conto == null || conto.isEmpty())
                throw new IllegalArgumentException("Nessun conto trovato per l'intestatario: " + nome);
            return conto;
        } catch (Exception e) {
            throw new Error("[Hibernate ORM] : Errore ricercaPerNome", e);
        }
    }

    public List<ContoBancario> filterByType(String tipo) {
        if (tipo == null || tipo.isEmpty())
            throw new IllegalArgumentException("Il tipo di conto non può essere nullo o vuoto");

        try {
            List<ContoBancario> conto = contoRepository.findByTipoConto(tipo);
            if (conto == null || conto.isEmpty())
                throw new IllegalArgumentException("Nessun conto trovato per il tipo: " + tipo);
            return conto;
        } catch (Exception e) {
            throw new Error("[Hibernate ORM] : Errore filtraPerTipo", e);
        }
    }
}
