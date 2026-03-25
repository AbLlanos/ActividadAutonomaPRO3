package com.itsqmet.actividad_autonoma_pro3.repository;

import com.itsqmet.actividad_autonoma_pro3.entity.Factura;
import com.itsqmet.actividad_autonoma_pro3.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repositorio JPA para la entidad {@link Producto}.
 * Extiende {@link JpaRepository} para heredar automáticamente las operaciones
 * CRUD básicas (save, findById, findAll, deleteById, etc.) sin necesidad de implementarlas.
 */
public interface ProductoRepository extends JpaRepository<Producto,Long> {



}
