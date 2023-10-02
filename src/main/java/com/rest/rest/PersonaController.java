package com.rest.rest;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class PersonaController {
    private  PersonaService personaService;

    public PersonaController() {
        this.personaService = new PersonaService();
    }

    //personas
    @GetMapping("/personas/")
    public List<Persona> obtenerPersonas(){
        return this.personaService.obtenerPersonas();
    }

    //localhost:8080/api/v1/personas/123
    @GetMapping("/personas/{cedula}")
    public Persona obtenerPersonPorCedula(@PathVariable("cedula") String cedula){
        return this.personaService.obtenerPersonaPorCedula(cedula);
    }

    @DeleteMapping("/personas/{cedula}")
    public void deletePersona(@PathVariable("cedula") String cedula) {
        this.personaService.eliminarPersona(cedula);
    }

    @PostMapping("/personas")
    public Persona agregarPersona(@RequestBody Persona persona) {
        return this.personaService.crearPersona(persona);
    }
    //localhost:8080/api/v1/personas?ciudad=Medellin
    @GetMapping("/personas")
    public List<Persona> obtenerPersonaPorCiudad(@RequestParam("ciudad") String ciudad) {
        return this.personaService.obtenerPersonaCiudad(ciudad);
    }
}
