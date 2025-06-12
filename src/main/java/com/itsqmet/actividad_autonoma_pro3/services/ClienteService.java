package com.itsqmet.actividad_autonoma_pro3.services;

import com.itsqmet.actividad_autonoma_pro3.entity.Cliente;
import com.itsqmet.actividad_autonoma_pro3.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    //Mostrar todos los productos
    public List<Cliente> mostrarClientes(){
        return  clienteRepository.findAll();
    }

    //Buscar por id
    public Optional<Cliente> bucarClientePorId(String id){
        return clienteRepository.findById(id);
    }

    //Guardar
    public Cliente  guardarCliente(Cliente cliente){
        return clienteRepository.save(cliente);
    }

    //Eliminar
    public void eliminarCliente(String id){
        clienteRepository.deleteById(id);
    }

    // Buscar clientes por cédula
    public List<Cliente> buscarClientesPorCedula(String buscarCedula) {
        if (buscarCedula == null || buscarCedula.isEmpty()) {
            return clienteRepository.findAll();
        }
        return clienteRepository.findByCedulaContainingIgnoreCase(buscarCedula);
    }

}
