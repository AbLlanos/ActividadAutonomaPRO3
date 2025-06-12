package com.itsqmet.actividad_autonoma_pro3.services;


import com.itsqmet.actividad_autonoma_pro3.entity.Cliente;
import com.itsqmet.actividad_autonoma_pro3.entity.Producto;
import com.itsqmet.actividad_autonoma_pro3.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoInterface;

    //Mostrar todos los productos
    public List<Producto> mostrarProductos(){
        return  productoInterface.findAll();
    }

    //Buscar por id
    public Optional<Producto> bucarProductoPorId(Long id){
        return productoInterface.findById(id);
    }

    //Guardar
    public Producto guardarProducto(Producto producto){
        return productoInterface.save(producto);
    }

    //Eliminar producto
    public void eliminarProducto(Long id){
        productoInterface.deleteById(id);
    }




}
