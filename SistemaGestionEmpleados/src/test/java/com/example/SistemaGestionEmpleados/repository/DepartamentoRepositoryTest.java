package com.example.SistemaGestionEmpleados.repository;

import com.example.SistemaGestionEmpleados.model.Departamento;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class DepartamentoRepositoryTest {

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @Test
    void guardarYBuscarPorNombre() {
        Departamento d = Departamento.builder()
                .nombre("RRHH")
                .descripcion("Recursos Humanos")
                .build();

        departamentoRepository.save(d);

        Optional<Departamento> encontrado = departamentoRepository.findByNombre("RRHH");

        assertThat(encontrado).isPresent();
        assertThat(encontrado.get().getDescripcion()).isEqualTo("Recursos Humanos");
    }
}