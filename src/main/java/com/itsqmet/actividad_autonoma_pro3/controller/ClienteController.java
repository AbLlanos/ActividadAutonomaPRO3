package com.itsqmet.actividad_autonoma_pro3.controller;

import com.itsqmet.actividad_autonoma_pro3.entity.Cliente;
import com.itsqmet.actividad_autonoma_pro3.services.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controlador MVC que gestiona las peticiones HTTP relacionadas con los clientes.
 * Todas las rutas de este controlador están prefijadas con /cliente.
 */
@Controller
@RequestMapping("/cliente")
public class ClienteController {

    /**
     * Servicio de clientes inyectado para acceder a la lógica de negocio.
     */
    @Autowired
    private ClienteService clienteService;

    /**
     * Muestra la lista de todos los clientes registrados.
     * Si se proporciona una cédula como parámetro, filtra los resultados
     * mostrando solo los clientes cuya cédula contenga ese texto.
     * @param buscarCedula Texto opcional para filtrar por cédula (por defecto vacío).
     * @param model Modelo que transfiere los datos a la vista.
     * @return Vista de la tabla de clientes (pages/tablaClientes).
     */
    @GetMapping("/clientes")
    public String listaClientes(
            @RequestParam(name = "buscarCedula", required = false, defaultValue = "") String buscarCedula,
            Model model) {
        List<Cliente> clientes = clienteService.buscarClientesPorCedula(buscarCedula);
        model.addAttribute("clientes", clientes);
        model.addAttribute("buscarCedula", buscarCedula);
        return "pages/tablaClientes";
    }

    /**
     * Muestra el formulario vacío para registrar un nuevo cliente.
     * Se agrega un objeto Cliente vacío al modelo para el binding del formulario.
     * @param model Modelo que transfiere los datos a la vista.
     * @return Vista del formulario de cliente (pages/formularioCliente).
     */
    @GetMapping("/irFormCliente")
    public String mostrarFormularioCliente(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "pages/formularioCliente";
    }

    /**
     * Procesa el formulario de registro y guarda el nuevo cliente en la base de datos.
     * Si el formulario contiene errores de validación, regresa al formulario con los mensajes de error.
     * Si es exitoso, redirige a la pantalla de login.
     * @param cliente Objeto Cliente poblado con los datos del formulario y validado con @Valid.
     * @param result Resultado de la validación del formulario.
     * @param model Modelo que transfiere los datos a la vista.
     * @return Vista de login si es exitoso, o formulario con errores si falla la validación.
     */
    @PostMapping("/guardarCliente")
    public String guardarCliente(
            @Valid @ModelAttribute("cliente") Cliente cliente,
            BindingResult result,
            Model model) {
        // Si hay errores de validación, regresa al formulario mostrando los mensajes
        if (result.hasErrors()) {
            return "pages/formularioCliente";
        }
        Cliente clienteGuardado = clienteService.guardarCliente(cliente);
        model.addAttribute("cliente", clienteGuardado);
        return "pages/login";
    }

    /**
     * Carga el formulario de edición con los datos del cliente seleccionado.
     * Si el ID no corresponde a ningún cliente, carga un formulario vacío.
     * @param id ID del cliente a editar.
     * @param model Modelo que transfiere los datos a la vista.
     * @return Vista del formulario de cliente con los datos precargados (pages/formularioCliente).
     */
    @GetMapping("/editarCliente/{id}")
    public String actualizarCliente(@PathVariable String id, Model model) {
        Optional<Cliente> cliente = clienteService.bucarClientePorId(id);
        // Si no existe el cliente, se carga un formulario vacío como fallback
        model.addAttribute("cliente", cliente.orElse(new Cliente()));
        return "pages/formularioCliente";
    }

    /**
     * Elimina el cliente con el ID especificado de la base de datos
     * y redirige a la lista de clientes actualizada.
     * @param id ID del cliente a eliminar.
     * @return Redirección a la lista de clientes (/cliente/clientes).
     */
    @GetMapping("/eliminarCliente/{id}")
    public String eliminarClliente(@PathVariable String id) {
        clienteService.eliminarCliente(id);
        return "redirect:/cliente/clientes";
    }
}