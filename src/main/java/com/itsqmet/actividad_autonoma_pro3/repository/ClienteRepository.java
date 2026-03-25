package com.itsqmet.actividad_autonoma_pro3.repository;

import com.itsqmet.actividad_autonoma_pro3.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio JPA para la entidad {@link Cliente}.
 * Extiende {@link JpaRepository} para heredar automáticamente las operaciones
 * CRUD básicas (save, findById, findAll, deleteById, etc.) sin necesidad de implementarlas.
 * El tipo de la clave primaria es {@link String} (cédula o UUID del cliente).
 */
public interface ClienteRepository extends JpaRepository<Cliente, String> {

    /**
     * Busca clientes cuya cédula contenga el texto proporcionado,
     * ignorando diferencias entre mayúsculas y minúsculas.
     * Spring Data JPA genera la consulta SQL automáticamente a partir del nombre del método.
     * Equivale a: SELECT * FROM clientes WHERE cedula ILIKE '%cedula%'
     *
     * @param cedula Texto parcial o completo a buscar dentro del campo cédula.
     * @return Lista de clientes cuya cédula coincida con el criterio de búsqueda.
     */
    List<Cliente> findByCedulaContainingIgnoreCase(String cedula);

    /**
     * Busca un cliente por su dirección de email exacta.
     * Usado principalmente por {@link com.itsqmet.actividad_autonoma_pro3.security.UsuarioDetailsService}
     * durante el proceso de autenticación de Spring Security.
     * Equivale a: SELECT * FROM clientes WHERE email = ?
     *
     * @param email Email exacto del cliente a buscar.
     * @return Optional con el cliente si existe, o vacío si no se encuentra.
     */
    Optional<Cliente> findByemail(String email);
}