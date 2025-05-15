package com.giuseppe.rest_api.controller;

import com.giuseppe.rest_api.model.Persona;
import com.giuseppe.rest_api.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/persone")
public class PersonaController {

    @Autowired
    private PersonaService service;

    @GetMapping
    public List<Persona> getAllPersone() {
        return service.getAllPersone();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Persona> getPersonaById(@PathVariable int id) {
        Persona p = service.getPersonaById(id);
        if (p == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(p);
    }

    @PostMapping
    public ResponseEntity<String> createPersona(@RequestBody Persona nuovaPersona) {
        boolean retValue = service.aggiungiPersona(nuovaPersona);
        if (retValue) {
            return ResponseEntity.created(URI.create("/persone/" + nuovaPersona.getId())).build();
        }
        return ResponseEntity.unprocessableEntity().body("Persona con id " + nuovaPersona.getId() + " già presente");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Persona> modificaPersona(
            @RequestBody Persona personaModificata,
            @PathVariable int id) {
        boolean retValue = service.modificaPersona(personaModificata, id);
        if (retValue) {
            return ResponseEntity.ok(personaModificata);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePersona(@PathVariable int id) {
        boolean retValue = service.rimuoviPersona(id);
        if (retValue) {
            return ResponseEntity.ok("Persona eliminata con successo.");
        }
        return ResponseEntity.notFound().build();
    }
}
