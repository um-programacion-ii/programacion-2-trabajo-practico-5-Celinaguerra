package com.example.SistemaGestionEmpleados.service;

import com.example.SistemaGestionEmpleados.model.Proyecto;
import java.util.List;

public interface ProyectoService {
    Proyecto guardar(Proyecto proyecto);
    Proyecto buscarPorId(Long id);
    List<Proyecto> buscarProyectosActivos();
    Proyecto asignarEmpleadoAProyecto(Long proyectoId, Long empleadoId);
    List<Proyecto> obtenerTodos();
    Proyecto actualizar(Long id, Proyecto proyecto);
    void eliminar(Long id);
}
