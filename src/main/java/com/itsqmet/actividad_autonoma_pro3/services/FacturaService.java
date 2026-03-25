package com.itsqmet.actividad_autonoma_pro3.services;

import com.itsqmet.actividad_autonoma_pro3.entity.Cliente;
import com.itsqmet.actividad_autonoma_pro3.entity.Factura;
import com.itsqmet.actividad_autonoma_pro3.repository.ClienteRepository;
import com.itsqmet.actividad_autonoma_pro3.repository.FacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servicio que contiene la lógica de negocio para la gestión de facturas.
 * Actúa como intermediario entre el controlador y el repositorio de facturas.
 */
@Service
public class FacturaService {

    /**
     * Repositorio JPA para operaciones CRUD sobre la entidad Factura en la base de datos.
     */
    @Autowired
    private FacturaRepository facturaRepository;

    /**
     * Retorna la lista completa de facturas registradas en la base de datos.
     * @return Lista de todas las facturas.
     */
    public List<Factura> mostrarFactura() {
        return facturaRepository.findAll();
    }

    /**
     * Busca una factura por su identificador único.
     * @param id ID de la factura a buscar.
     * @return Optional con la factura si existe, o vacío si no se encuentra.
     */
    public Optional<Factura> bucarFacturaPorId(Long id) {
        return facturaRepository.findById(id);
    }

    /**
     * Guarda una nueva factura o actualiza una existente en la base de datos.
     * Si la factura ya tiene ID, se actualizará; de lo contrario, se creará una nueva.
     * @param factura Objeto Factura con los datos a persistir.
     * @return La factura guardada con su ID generado.
     */
    public Factura guardarFactura(Factura factura) {
        return facturaRepository.save(factura);
    }

    /**
     * Elimina una factura de la base de datos por su ID.
     * @param id ID de la factura a eliminar.
     */
    public void eliminarFactura(Long id) {
        facturaRepository.deleteById(id);
    }
}