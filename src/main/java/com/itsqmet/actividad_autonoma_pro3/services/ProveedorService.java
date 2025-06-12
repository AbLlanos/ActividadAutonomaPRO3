package com.itsqmet.actividad_autonoma_pro3.services;

import com.itsqmet.actividad_autonoma_pro3.entity.Producto;
import com.itsqmet.actividad_autonoma_pro3.entity.Proveedor;
import com.itsqmet.actividad_autonoma_pro3.repository.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProveedorService {

    @Autowired
    private ProveedorRepository proveedorRepository;

    //Mostrar todos los proveedor
    public List<Proveedor> mostrarProveedores(){
        return  proveedorRepository.findAll();
    }

    //Buscar por id
    public Optional<Proveedor> bucarProveedorPorId(Long id){
        return proveedorRepository.findById(id);
    }

    //Guardar
    public Proveedor guardarProveedor(Proveedor proveedor){
        return proveedorRepository.save(proveedor);
    }

    //Eliminar producto
    public void eliminarProveedor(Long id){
        proveedorRepository.deleteById(id);
    }

}
