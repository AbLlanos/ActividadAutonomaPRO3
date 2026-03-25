package com.itsqmet.actividad_autonoma_pro3.security;

import com.itsqmet.actividad_autonoma_pro3.entity.Cliente;
import com.itsqmet.actividad_autonoma_pro3.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Servicio personalizado de autenticación que implementa {@link UserDetailsService}.
 * Spring Security utiliza esta clase para cargar los datos del usuario
 * desde la base de datos durante el proceso de inicio de sesión.
 */
@Service
public class UsuarioDetailsService implements UserDetailsService {

    /**
     * Repositorio de clientes usado para buscar al usuario por su email
     * en la base de datos durante la autenticación.
     */
    @Autowired
    private ClienteRepository clienteRepository;

    /**
     * Carga los detalles del usuario a partir de su nombre de usuario (email).
     * Spring Security llama a este método automáticamente al procesar el formulario de login.
     * Si el email no existe en la base de datos, lanza una excepción que Spring Security
     * interpreta como credenciales inválidas.
     *
     * @param username Email del cliente ingresado en el formulario de login.
     * @return Objeto {@link UserDetails} con el email y contraseña encriptada del cliente.
     * @throws UsernameNotFoundException Si no existe ningún cliente con el email proporcionado.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Busca el cliente por email; lanza excepción si no existe
        Cliente cliente = clienteRepository.findByemail(username)
                .orElseThrow(() -> new UsernameNotFoundException(
                        "Cliente no encontrado con el email: " + username
                ));

        // Construye el UserDetails con las credenciales del cliente para que
        // Spring Security pueda validar la contraseña ingresada contra el hash BCrypt
        return User.builder()
                .username(cliente.getEmail())
                .password(cliente.getPassword())
                .build();
    }
}