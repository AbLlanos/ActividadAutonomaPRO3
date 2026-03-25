package com.itsqmet.actividad_autonoma_pro3.repository;

import com.itsqmet.actividad_autonoma_pro3.entity.Factura;
import com.itsqmet.actividad_autonoma_pro3.entity.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio JPA para la entidad {@link Proveedor}.
 * Extiende {@link JpaRepository} para heredar automáticamente las operaciones
 * CRUD básicas (save, findById, findAll, deleteById, etc.) sin necesidad de implementarlas.
 */

public interface ProveedorRepository extends JpaRepository<Proveedor,Long> {
}
