package com.itsqmet.actividad_autonoma_pro3.services;

import com.itsqmet.actividad_autonoma_pro3.entity.Producto;
import com.itsqmet.actividad_autonoma_pro3.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servicio que contiene la lógica de negocio para la gestión de productos.
 * Actúa como intermediario entre el controlador y el repositorio de productos.
 */
@Service
public class ProductoService {

    /**
     * Repositorio JPA para operaciones CRUD sobre la entidad Producto en la base de datos.
     */
    @Autowired
    private ProductoRepository productoInterface;

    /**
     * Retorna la lista completa de productos registrados en la base de datos.
     * @return Lista de todos los productos.
     */
    public List<Producto> mostrarProductos() {
        return productoInterface.findAll();
    }

    /**
     * Busca un producto por su identificador único.
     * @param id ID del producto a buscar.
     * @return Optional con el producto si existe, o vacío si no se encuentra.
     */
    public Optional<Producto> bucarProductoPorId(Long id) {
        return productoInterface.findById(id);
    }

    /**
     * Guarda un nuevo producto o actualiza uno existente en la base de datos.
     * Si el producto ya tiene ID, se actualizará; de lo contrario, se creará uno nuevo.
     * @param producto Objeto Producto con los datos a persistir.
     * @return El producto guardado con su ID generado.
     */
    public Producto guardarProducto(Producto producto) {
        return productoInterface.save(producto);
    }

    /**
     * Elimina un producto de la base de datos por su ID.
     * @param id ID del producto a eliminar.
     */
    public void eliminarProducto(Long id) {
        productoInterface.deleteById(id);
    }
}