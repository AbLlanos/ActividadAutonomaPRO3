package com.itsqmet.actividad_autonoma_pro3.repository;

import com.itsqmet.actividad_autonoma_pro3.entity.Factura;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FacturaRepository extends JpaRepository<Factura, Long > {
}
