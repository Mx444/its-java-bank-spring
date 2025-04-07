package com.example.bankapi.controllers;

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
import com.example.bankapi.providers.ContoService;

@RestController
@RequestMapping("/conti")
public class ContoController {
    @Autowired
    private ContoService contoService = new ContoService();

    @GetMapping
    public List<ContoBancario> getTuttiIConti() {
        return contoService.findAll();
    }

    @PostMapping
    public String aggiungiConto(@RequestBody ContoBancario conto) {
        return contoService.create(conto);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        return contoService.delete(id);
    }

    @GetMapping("/ricerca")
    public List<ContoBancario> ricercaPerNome(@RequestParam String nome) {
        return contoService.findByName(nome);
    }

    @GetMapping("/tipo")
    public List<ContoBancario> filtraPerTipo(@RequestParam String tipo) {
        return contoService.filterByType(tipo);
    }
}