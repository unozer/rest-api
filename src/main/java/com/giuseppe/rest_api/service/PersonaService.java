package com.giuseppe.rest_api.service;

import com.giuseppe.rest_api.model.Persona;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class PersonaService {

    private List<Persona> listaPersone = List.of(
            new Persona(12, "Mario", "Rossi"),
            new Persona(1, "Anna", "Verdi"),
            new Persona(2, "Vasco", "Rossi"),
            new Persona(45, "Manuel", "Gialli"),
            new Persona(213, "Francesca", "Neri"),
            new Persona(123, "Aldo", "Rossi"),
            new Persona(555, "Giorgio", "Anice"),
            new Persona(76, "Michela", "Zanzara")
    );

    public boolean aggiungiPersona(Persona p) {
        for (Persona per : listaPersone)  {
            if (per.getId() == p.getId()) {
                return false;
            }
        }
        listaPersone.add(p);
        return true;
    }

    public boolean rimuoviPersona(int id) {
        int indexToDelete = -1;

        for (int i = 0; i < listaPersone.size(); i++) {
            if (listaPersone.get(i).getId() == id) {
                indexToDelete = i;
            }
        }

        if (indexToDelete != -1) {
            listaPersone.remove(indexToDelete);
            return true;
        }

        return false;
    }

    public boolean modificaPersona(Persona personaModificata, int id) {
        for (Persona p : listaPersone) {
            if (p.getId() == id) {
                p.setId(personaModificata.getId());
                p.setNome(personaModificata.getNome());
                p.setCognome(personaModificata.getCognome());
                return true;
            }
        }
        return false;
    }

    public List<Persona> getAllPersone(String orderBy, Integer limit) {
        Comparator<Persona> comparator;

        switch (orderBy) {
            case "nome":
                comparator = Comparator.comparing(Persona::getNome);
                break;
            case "cognome":
                comparator = Comparator.comparing(Persona::getCognome);
                break;
            case "id":
            default:
                comparator = Comparator.comparing(Persona::getId);
                break;
        }

        Stream<Persona> stream = listaPersone.stream().sorted(comparator);

        if (limit != null)
            stream = stream.limit(limit);

        return stream.collect(Collectors.toList());
    }

    public Persona getPersonaById(int id) {
        for (Persona p : listaPersone) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }
}
