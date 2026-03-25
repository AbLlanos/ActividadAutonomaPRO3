package com.itsqmet.actividad_autonoma_pro3.services;

import com.itsqmet.actividad_autonoma_pro3.entity.Cliente;
import com.itsqmet.actividad_autonoma_pro3.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servicio que contiene la lógica de negocio para la gestión de clientes.
 * Actúa como intermediario entre el controlador y el repositorio.
 */
@Service
public class ClienteService {

    /**
     * Repositorio JPA para operaciones CRUD sobre la entidad Cliente en la base de datos.
     */
    @Autowired
    private ClienteRepository clienteRepository;

    /**
     * Codificador de contraseñas (BCrypt) inyectado desde la configuración de seguridad.
     */
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Retorna la lista completa de clientes registrados en la base de datos.
     * @return Lista de todos los clientes.
     */
    public List<Cliente> mostrarClientes() {
        return clienteRepository.findAll();
    }

    /**
     * Busca un cliente por su identificador único.
     * @param id ID del cliente a buscar.
     * @return Optional con el cliente si existe, o vacío si no se encuentra.
     */
    public Optional<Cliente> bucarClientePorId(String id) {
        return clienteRepository.findById(id);
    }

    /**
     * Guarda un nuevo cliente en la base de datos.
     * Antes de guardar, encripta la contraseña usando BCrypt
     * para que nunca se almacene en texto plano.
     * @param cliente Objeto Cliente con los datos a registrar.
     * @return El cliente guardado con su ID generado.
     */
    public Cliente guardarCliente(Cliente cliente) {
        // Encripta la contraseña antes de persistir
        cliente.setPassword(passwordEncoder.encode(cliente.getPassword()));
        return clienteRepository.save(cliente);
    }

    /**
     * Elimina un cliente de la base de datos por su ID.
     * @param id ID del cliente a eliminar.
     */
    public void eliminarCliente(String id) {
        clienteRepository.deleteById(id);
    }

    /**
     * Busca clientes cuya cédula contenga el texto proporcionado (búsqueda parcial).
     * Si el parámetro es nulo o vacío, retorna todos los clientes.
     * @param buscarCedula Texto a buscar dentro del campo cédula.
     * @return Lista de clientes que coinciden con el criterio de búsqueda.
     */
    public List<Cliente> buscarClientesPorCedula(String buscarCedula) {
        if (buscarCedula == null || buscarCedula.isEmpty()) {
            return clienteRepository.findAll();
        }
        return clienteRepository.findByCedulaContainingIgnoreCase(buscarCedula);
    }
}