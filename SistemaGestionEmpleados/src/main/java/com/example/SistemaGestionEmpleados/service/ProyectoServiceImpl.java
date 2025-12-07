package com.example.SistemaGestionEmpleados.service;

import com.example.SistemaGestionEmpleados.exception.EmpleadoNoEncontradoException;
import com.example.SistemaGestionEmpleados.exception.ProyectoNoEncontradoException;
import com.example.SistemaGestionEmpleados.model.Empleado;
import com.example.SistemaGestionEmpleados.model.Proyecto;
import com.example.SistemaGestionEmpleados.repository.EmpleadoRepository;
import com.example.SistemaGestionEmpleados.repository.ProyectoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class ProyectoServiceImpl implements ProyectoService {

    private final ProyectoRepository proyectoRepository;
    private final EmpleadoRepository empleadoRepository;

    public ProyectoServiceImpl(ProyectoRepository proyectoRepository, EmpleadoRepository empleadoRepository) {
        this.proyectoRepository = proyectoRepository;
        this.empleadoRepository = empleadoRepository;
    }

    @Override
    public Proyecto guardar(Proyecto proyecto) {
        return proyectoRepository.save(proyecto);
    }

    @Override
    public Proyecto buscarPorId(Long id) {
        return proyectoRepository.findById(id)
                .orElseThrow(() -> new ProyectoNoEncontradoException("Proyecto no encontrado con ID: " + id));
    }

    @Override
    public List<Proyecto> buscarProyectosActivos() {
        return proyectoRepository.findByFechaFinAfter(LocalDate.now());
    }

    @Override
    public Proyecto asignarEmpleadoAProyecto(Long proyectoId, Long empleadoId) {
        Proyecto proyecto = buscarPorId(proyectoId);
        Empleado empleado = empleadoRepository.findById(empleadoId)
                .orElseThrow(() -> new EmpleadoNoEncontradoException("Empleado no encontrado con ID: " + empleadoId));

        proyecto.getEmpleados().add(empleado);
        return proyectoRepository.save(proyecto);
    }

    @Override
    public List<Proyecto> obtenerTodos() {
        return proyectoRepository.findAll();
    }

    @Override
    public Proyecto actualizar(Long id, Proyecto proyecto) {
        if (!proyectoRepository.existsById(id)) {
            throw new ProyectoNoEncontradoException("Proyecto no encontrado con ID: " + id);
        }
        proyecto.setId(id);
        return proyectoRepository.save(proyecto);
    }

    @Override
    public void eliminar(Long id) {
        if (!proyectoRepository.existsById(id)) {
            throw new ProyectoNoEncontradoException("Proyecto no encontrado con ID: " + id);
        }
        proyectoRepository.deleteById(id);
    }
}