package com.example.SistemaGestionEmpleados.service;
import com.example.SistemaGestionEmpleados.exception.DepartamentoNoEncontradoException;
import com.example.SistemaGestionEmpleados.model.Departamento;
import com.example.SistemaGestionEmpleados.repository.DepartamentoRepository;
import com.example.SistemaGestionEmpleados.repository.EmpleadoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DepartamentoServiceImplTest {

    @Mock DepartamentoRepository departamentoRepository;
    @Mock EmpleadoRepository empleadoRepository;
    @InjectMocks DepartamentoServiceImpl service;

    @Test
    void actualizar_ok() {
        Departamento existente = Departamento.builder().id(1L).nombre("IT").build();
        Departamento entrada = Departamento.builder().nombre("Tech").build();

        when(departamentoRepository.existsById(1L)).thenReturn(true);
        when(departamentoRepository.save(any())).thenAnswer(a -> a.getArgument(0));

        Departamento out = service.actualizar(1L, entrada);

        assertThat(out.getId()).isEqualTo(1L);
        assertThat(out.getNombre()).isEqualTo("Tech");
    }

    @Test
    void buscarPorId_notFound() {
        when(departamentoRepository.findById(7L)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> service.buscarPorId(7L))
                .isInstanceOf(DepartamentoNoEncontradoException.class);
    }
}
