package com.example.SistemaGestionEmpleados.service;

import com.example.SistemaGestionEmpleados.exception.ProyectoNoEncontradoException;
import com.example.SistemaGestionEmpleados.model.Empleado;
import com.example.SistemaGestionEmpleados.model.Proyecto;
import com.example.SistemaGestionEmpleados.repository.EmpleadoRepository;
import com.example.SistemaGestionEmpleados.repository.ProyectoRepository;
import com.example.SistemaGestionEmpleados.service.ProyectoServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProyectoServiceImplTest {

    @Mock ProyectoRepository proyectoRepository;
    @Mock EmpleadoRepository empleadoRepository;
    @InjectMocks ProyectoServiceImpl service;

    @Test
    void buscarProyectosActivos_delegaEnRepo() {
        when(proyectoRepository.findByFechaFinAfter(any()))
                .thenReturn(List.of(Proyecto.builder().nombre("A").build()));

        List<Proyecto> result = service.buscarProyectosActivos();

        assertThat(result).hasSize(1);
        verify(proyectoRepository).findByFechaFinAfter(any());
    }

    @Test
    void asignarEmpleadoAProyecto_ok() {
        Proyecto proyecto = Proyecto.builder()
                .id(1L)
                .nombre("Demo")
                .empleados(new HashSet<>()) // <--- agregado
                .build();

        Empleado empleado = Empleado.builder().id(10L).build();

        when(proyectoRepository.findById(1L)).thenReturn(Optional.of(proyecto));
        when(empleadoRepository.findById(10L)).thenReturn(Optional.of(empleado));
        when(proyectoRepository.save(any())).thenAnswer(a -> a.getArgument(0));

        Proyecto resultado = service.asignarEmpleadoAProyecto(1L, 10L);

        assertThat(resultado.getEmpleados()).hasSize(1);
        assertThat(resultado.getEmpleados()).contains(empleado);
        verify(proyectoRepository).save(proyecto);
    }

    @Test
    void buscarPorId_notFound() {
        when(proyectoRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.buscarPorId(99L))
                .isInstanceOf(ProyectoNoEncontradoException.class)
                .hasMessageContaining("99");
    }

    @Test
    void actualizar_ok() {
        Proyecto existente = Proyecto.builder().id(1L).nombre("Old").build();
        Proyecto entrada = Proyecto.builder().nombre("New").build();

        when(proyectoRepository.existsById(1L)).thenReturn(true);
        when(proyectoRepository.save(any())).thenAnswer(a -> a.getArgument(0));

        Proyecto out = service.actualizar(1L, entrada);

        assertThat(out.getId()).isEqualTo(1L);
        assertThat(out.getNombre()).isEqualTo("New");
        verify(proyectoRepository).save(any());
    }

    @Test
    void actualizar_notFound() {
        when(proyectoRepository.existsById(1L)).thenReturn(false);

        assertThatThrownBy(() -> service.actualizar(1L, Proyecto.builder().build()))
                .isInstanceOf(ProyectoNoEncontradoException.class);
    }
}

