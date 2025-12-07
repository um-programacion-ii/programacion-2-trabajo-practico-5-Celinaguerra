package com.example.SistemaGestionEmpleados.controller;

import com.example.SistemaGestionEmpleados.model.Proyecto;
import com.example.SistemaGestionEmpleados.service.ProyectoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/proyectos")
@Validated
public class ProyectoController {

    private final ProyectoService proyectoService;

    public ProyectoController(ProyectoService proyectoService) {
        this.proyectoService = proyectoService;
    }

    /**
     * Obtiene una lista de todos los proyectos registrados en el sistema.
     * Endpoint: GET /api/proyectos
     * return: Una lista de objetos Proyecto.
     */
    @GetMapping
    public List<Proyecto> obtenerTodos() {
        return proyectoService.obtenerTodos();
    }

    /**
     * Busca un proyecto específico por ID.
     * Endpoint: GET /api/proyectos/{id}
     * param: id del proyecto a buscar.
     * return: El objeto Proyecto encontrado.
     */
    @GetMapping("/{id}")
    public Proyecto obtenerPorId(@PathVariable Long id) {
        return proyectoService.buscarPorId(id);
    }

    /**
     * Crea un nuevo proyecto.
     * Endpoint: POST /api/proyectos
     * param: El objeto Proyecto a crear.
     * return: El objeto Proyecto creado.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Proyecto crear(@Valid @RequestBody Proyecto proyecto) {
        return proyectoService.guardar(proyecto);
    }

    /**
     * Actualiza un proyecto existente.
     * Endpoint: PUT /api/proyectos/{id}
     * param: id del proyecto a actualizar.
     * param: El objeto Proyecto con los datos actualizados.
     * return: El objeto Proyecto actualizado.
     */
    @PutMapping("/{id}")
    public Proyecto actualizar(@PathVariable Long id, @Valid @RequestBody Proyecto proyecto) {
        return proyectoService.actualizar(id, proyecto);
    }

    /**
     * Elimina un proyecto por su ID.
     * Endpoint: DELETE /api/proyectos/{id}
     * param: id del proyecto a eliminar.
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        proyectoService.eliminar(id);
    }

    /**
     * Busca y devuelve una lista de proyectos que están actualmente activos.
     * Endpoint: GET /api/proyectos/activos
     * return: Una lista de objetos Proyecto que están activos.
     */
    @GetMapping("/activos")
    public List<Proyecto> buscarProyectosActivos() {
        return proyectoService.buscarProyectosActivos();
    }

    /**
     * Asigna un empleado a un proyecto específico.
     * Endpoint: POST /api/proyectos/{proyectoId}/empleados/{empleadoId}
     * param: proyectoId del proyecto al que se asignará el empleado.
     * param: empleadoId del empleado que se asignará al proyecto.
     * return: El objeto Proyecto actualizado con el empleado asignado.
     */
    @PostMapping("/{proyectoId}/empleados/{empleadoId}")
    public Proyecto asignarEmpleadoAProyecto(@PathVariable Long proyectoId, @PathVariable Long empleadoId) {
        return proyectoService.asignarEmpleadoAProyecto(proyectoId, empleadoId);
    }
}