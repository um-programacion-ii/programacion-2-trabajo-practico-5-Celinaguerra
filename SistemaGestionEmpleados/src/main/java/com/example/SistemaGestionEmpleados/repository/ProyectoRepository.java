package com.example.SistemaGestionEmpleados.repository;

import com.example.SistemaGestionEmpleados.model.Empleado;
import com.example.SistemaGestionEmpleados.model.Proyecto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ProyectoRepository extends JpaRepository<Proyecto, Long> {
    List<Proyecto> findByFechaFinAfter(LocalDate fecha);
    List<Proyecto> findByFechaInicioBetween(LocalDate fechaInicio, LocalDate fechaFin);
    List<Proyecto> findByEmpleadosContains(Empleado empleado);
}