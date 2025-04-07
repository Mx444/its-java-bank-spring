package com.example.bankapi.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.bankapi.models.ContoBancario;
import com.example.bankapi.repositories.ContoRepository;

@RestController
@RequestMapping("/conti")
public class ContoController {

    @Autowired
    private ContoRepository contoRepository;

    @GetMapping
    public List<ContoBancario> getTuttiIConti() {
        return contoRepository.findAll();
    }

    @PostMapping
    public ContoBancario aggiungiConto(@RequestBody ContoBancario conto) {
        return contoRepository.save(conto);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        if (contoRepository.existsById(id)) {
            contoRepository.deleteById(id);
            return "Conto eliminato.";
        } else {
            return "Conto non trovato.";
        }
    }

    @GetMapping("/ricerca")
    public List<ContoBancario> ricercaPerNome(@RequestParam String nome) {
        return contoRepository.findByIntestatarioContainingIgnoreCase(nome);
    }

    @GetMapping("/tipo")
    public List<ContoBancario> filtraPerTipo(@RequestParam String tipo) {
        return contoRepository.findByTipoConto(tipo);
    }
}