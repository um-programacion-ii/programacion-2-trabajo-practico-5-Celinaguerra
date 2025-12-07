package com.example.SistemaGestionEmpleados.controller;

import com.example.SistemaGestionEmpleados.model.Empleado;
import com.example.SistemaGestionEmpleados.service.EmpleadoService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;


import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/empleados")
@Validated
public class EmpleadoController {
    private final EmpleadoService empleadoService;

    public EmpleadoController(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    /**
     * Obtiene una lista completa de todos los empleados.
     * Endpoint: GET /api/empleados
     * return: Una lista de objetos Empleado.
     */
    @GetMapping
    public List<Empleado> obtenerTodos() {
        return empleadoService.obtenerTodos();
    }

    /**
     * Busca un empleado específico por ID.
     * Endpoint: GET /api/empleados/{id}
     * param: id del empleado a buscar.
     * return: El objeto Empleado encontrado.
     */
    @GetMapping("/{id}")
    public Empleado obtenerPorId(@PathVariable Long id) {
        return empleadoService.buscarPorId(id);
    }

    /**
     * Crea un nuevo empleado.
     * Endpoint: POST /api/empleados
     * param: El objeto Empleado a crear.
     * return: El objeto Empleado creado.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Empleado crear(@Valid @RequestBody Empleado empleado) {
        return empleadoService.guardar(empleado);
    }

    /**
     * Actualiza un empleado existente.
     * Endpoint: PUT /api/empleados/{id}
     * param: id del empleado a actualizar.
     * param: El objeto Empleado con los datos actualizados.
     * return: El objeto Empleado actualizado.
     */
    @PutMapping("/{id}")
    public Empleado actualizar(@PathVariable Long id, @Valid @RequestBody Empleado empleado) {
        return empleadoService.actualizar(id, empleado);
    }

    /**
     * Elimina un empleado por ID.
     * Endpoint: DELETE /api/empleados/{id}
     * param: id del empleado a eliminar.
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        empleadoService.eliminar(id);
    }

    /**
     * Busca empleados que pertenecen a un departamento específico por su nombre.
     * Endpoint: GET /api/empleados/departamento/{nombre}
     * param: nombre del departamento.
     * return: Una lista de empleados que pertenecen a ese departamento.
     */
    @GetMapping("/departamento/{nombre}")
    public List<Empleado> obtenerPorDepartamento(@PathVariable String nombre) {
        return empleadoService.buscarPorDepartamento(nombre);
    }

    /**
     * Busca empleados dentro de un rango específico de salario.
     * Endpoint: GET /api/empleados/salario?min={min}&max={max}
     * param: min salario mínimo.
     * param: max salario máximo.
     * return: Una lista de empleados cuyo salario está dentro del rango especificado.
     */
    @GetMapping("/salario")
    public List<Empleado> obtenerPorRangoSalario(
            @RequestParam BigDecimal min,
            @RequestParam BigDecimal max) {
        return empleadoService.buscarPorRangoSalario(min, max);
    }
}