package com.example.SistemaGestionEmpleados.service;

import com.example.SistemaGestionEmpleados.exception.DepartamentoNoEncontradoException;
import com.example.SistemaGestionEmpleados.exception.DepartamentoNombreDuplicadoException;
import com.example.SistemaGestionEmpleados.model.Departamento;
import com.example.SistemaGestionEmpleados.repository.DepartamentoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class DepartamentoServiceImpl implements DepartamentoService {

    private final DepartamentoRepository departamentoRepository;

    public DepartamentoServiceImpl(DepartamentoRepository departamentoRepository) {
        this.departamentoRepository = departamentoRepository;
    }

    @Override
    public Departamento guardar(Departamento departamento) {
        departamentoRepository.findByNombre(departamento.getNombre()).ifPresent(d -> {
            throw new DepartamentoNombreDuplicadoException("El nombre del departamento ya existe: " + d.getNombre());
        });
        return departamentoRepository.save(departamento);
    }

    @Override
    public Departamento buscarPorId(Long id) {
        return departamentoRepository.findById(id)
                .orElseThrow(() -> new DepartamentoNoEncontradoException("Departamento no encontrado con ID: " + id));
    }

    @Override
    public List<Departamento> obtenerTodos() {
        return departamentoRepository.findAll();
    }

    @Override
    public Departamento actualizar(Long id, Departamento departamento) {
        if (!departamentoRepository.existsById(id)) {
            throw new DepartamentoNoEncontradoException("Departamento no encontrado con ID: " + id);
        }
        departamento.setId(id);
        return departamentoRepository.save(departamento);
    }

    @Override
    public void eliminar(Long id) {
        if (!departamentoRepository.existsById(id)) {
            throw new DepartamentoNoEncontradoException("Departamento no encontrado con ID: " + id);
        }
        departamentoRepository.deleteById(id);
    }
}
