package com.itsqmet.actividad_autonoma_pro3.repository;

import com.itsqmet.actividad_autonoma_pro3.entity.Cliente;
import com.itsqmet.actividad_autonoma_pro3.entity.Factura;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio JPA para la entidad {@link Factura}.
 * Extiende {@link JpaRepository} para heredar automáticamente las operaciones
 * CRUD básicas (save, findById, findAll, deleteById, etc.) sin necesidad de implementarlas.
 */
public interface FacturaRepository extends JpaRepository<Factura, Long > {
}
