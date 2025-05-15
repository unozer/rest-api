package com.giuseppe.rest_api.service;

import com.giuseppe.rest_api.model.Persona;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonaService {

    private List<Persona> listaPersone = new ArrayList<>();

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

    public List<Persona> getAllPersone() {
        return listaPersone;
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
