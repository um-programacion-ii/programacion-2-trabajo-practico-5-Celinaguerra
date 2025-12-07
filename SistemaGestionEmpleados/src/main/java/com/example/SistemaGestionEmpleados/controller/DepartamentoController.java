package com.example.SistemaGestionEmpleados.controller;

import com.example.SistemaGestionEmpleados.model.Departamento;
import com.example.SistemaGestionEmpleados.service.DepartamentoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departamentos")
@Validated
public class DepartamentoController {

    private final DepartamentoService departamentoService;

    public DepartamentoController(DepartamentoService departamentoService) {
        this.departamentoService = departamentoService;
    }

    /**
     * Obtiene una lista de todos los departamentos.
     * Endpoint: GET /api/departamentos
     * return: Una lista de objetos Departamento.
     */
    @GetMapping
    public List<Departamento> obtenerTodos() {
        return departamentoService.obtenerTodos();
    }

    /**
     * Busca un departamento específico por ID.
     * Endpoint: GET /api/departamentos/{id}
     * param: id del departamento a buscar.
     * return: El objeto Departamento encontrado.
     */
    @GetMapping("/{id}")
    public Departamento obtenerPorId(@PathVariable Long id) {
        return departamentoService.buscarPorId(id);
    }

    /**
     * Crea un nuevo departamento.
     * Endpoint: POST /api/departamentos
     * param: El objeto Departamento a crear.
     * return: El objeto Departamento creado.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Departamento crear(@Valid @RequestBody Departamento departamento) {
        return departamentoService.guardar(departamento);
    }

    /**
     * Actualiza un departamento existente.
     * Endpoint: PUT /api/departamentos/{id}
     * param: id del departamento a actualizar.
     * param: El objeto Departamento con los datos actualizados.
     * return: El objeto Departamento actualizado.
     */
    @PutMapping("/{id}")
    public Departamento actualizar(@PathVariable Long id, @Valid @RequestBody Departamento departamento) {
        return departamentoService.actualizar(id, departamento);
    }

    /**
     * Elimina un departamento por ID.
     * Endpoint: DELETE /api/departamentos/{id}
     * param: id del departamento a eliminar.
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        departamentoService.eliminar(id);
    }
}