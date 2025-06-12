package com.itsqmet.actividad_autonoma_pro3.repository;

import com.itsqmet.actividad_autonoma_pro3.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, String> {

    List<Cliente> findByCedulaContainingIgnoreCase(String cedula);

}
