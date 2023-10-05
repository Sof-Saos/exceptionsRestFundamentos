package com.rest.rest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;
import java.util.NoSuchElementException;

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
    //representa toda la respuesta HTTP
    public ResponseEntity<?> obtenerPersonPorCedula(@PathVariable("cedula") String cedula) {
        try {
            Persona persona = this.personaService.obtenerPersonaPorCedula(cedula);
            return ResponseEntity.ok(persona);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    //localhost:8080/api/v1/personas/{cedula}
    @DeleteMapping("/personas/{cedula}")
    public ResponseEntity<?> deletePersona(@PathVariable("cedula") String cedula) {
        try {
            this.personaService.eliminarPersona(cedula);
            return ResponseEntity.ok().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    //localhost:8080/api/v1/personas
    @PostMapping("/personas")
    public ResponseEntity<?> agregarPersona(@RequestBody Persona persona) {
        try {
            Persona nuevaPersona = this.personaService.crearPersona(persona);
            return ResponseEntity.ok(nuevaPersona);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    //localhost:8080/api/v1/personas?ciudad=Medellin
    @GetMapping("/personas")
    public ResponseEntity<?> obtenerPersonaPorCiudad(@RequestParam("ciudad") String ciudad) {
        List<Persona> personas = this.personaService.obtenerPersonaCiudad(ciudad);
        if (personas.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(personas);
        }
    }

    //localhost:8080/api/v1/personas/cedula
    @GetMapping("/personas/cedula")
    public ResponseEntity<?> obtenerCedulas() {
        List<String> obtenercedulas = this.personaService.obtenerCedulas();
        if (obtenercedulas.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        else {
            return ResponseEntity.ok(obtenercedulas);
        }
    }

    //localhost:8080/api/v1/exceptions/dividir/{numerador}/{denominador}
    @GetMapping("/exceptions/dividir/{numerador}/{denominador}")
    public double dividir(@PathVariable int numerador, @PathVariable int denominador) {
        try {
            if (denominador == 0) {
                throw new ArithmeticException("No se puede dividir por cero");
            }
            return (double) numerador / denominador;
        } catch (ArithmeticException ex) {
            throw new ArithmeticException("Por favor, realiza una nueva solicitud con datos válidos");
        }
    }

}
