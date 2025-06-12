package com.itsqmet.actividad_autonoma_pro3.services;

import com.itsqmet.actividad_autonoma_pro3.entity.Cliente;
import com.itsqmet.actividad_autonoma_pro3.entity.Factura;
import com.itsqmet.actividad_autonoma_pro3.repository.ClienteRepository;
import com.itsqmet.actividad_autonoma_pro3.repository.FacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FacturaService {

    @Autowired
    private FacturaRepository facturaRepository;

    //Mostrar todos los productos
    public List<Factura> mostrarFactura(){
        return facturaRepository.findAll();
    }

    //Buscar por id
    public Optional<Factura> bucarFacturaPorId(Long id){
        return facturaRepository.findById(id);
    }

    //Guardar
    public Factura  guardarFactura(Factura factura){
        return facturaRepository.save(factura);
    }

    //Eliminar
    public void eliminarFactura(Long id){
        facturaRepository.deleteById(id);
    }


}
