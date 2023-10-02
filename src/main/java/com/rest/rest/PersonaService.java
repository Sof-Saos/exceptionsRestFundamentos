package com.rest.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PersonaService {
    private List<Persona> personas;

    public PersonaService() {
        this.personas = new ArrayList<>();
    }

    public List<Persona> obtenerPersonas() {
        return personas;
    }

    public Persona obtenerPersonaPorCedula(String cedula) {
        return this.personas
                .stream()
                .filter(persona -> persona.getCedula().equals(cedula))
                .findFirst().get();
    }

    public void eliminarPersona(String cedula) {
        Persona personaEliminar = obtenerPersonaPorCedula(cedula);
        this.personas.remove(personaEliminar);
    }

    public Persona crearPersona(Persona persona) {
        this.personas.add(persona);
        return persona;
    }

    public List<Persona> obtenerPersonaCiudad(String ciudad) {
        return this.personas
                .stream()
                .filter(persona -> persona.getCiudad().equals(ciudad))
                .collect(Collectors.toList());

    }
}
