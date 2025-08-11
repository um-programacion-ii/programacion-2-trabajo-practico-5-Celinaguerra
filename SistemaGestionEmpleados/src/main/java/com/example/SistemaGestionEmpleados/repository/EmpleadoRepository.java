package com.example.SistemaGestionEmpleados.repository;

import com.example.SistemaGestionEmpleados.model.Departamento;
import com.example.SistemaGestionEmpleados.model.Empleado;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
    Optional<Empleado> findByEmail(String email);
    List<Empleado> findByDepartamento(Departamento departamento);
    List<Empleado> findBySalarioBetween(BigDecimal salarioMin, BigDecimal salarioMax);
    List<Empleado> findByFechaContratacionAfter(LocalDate fecha);
}