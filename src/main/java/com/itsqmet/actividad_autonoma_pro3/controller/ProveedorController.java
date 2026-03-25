package com.itsqmet.actividad_autonoma_pro3.controller;

import com.itsqmet.actividad_autonoma_pro3.entity.Proveedor;
import com.itsqmet.actividad_autonoma_pro3.services.ProveedorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controlador MVC que gestiona las peticiones HTTP relacionadas con los proveedores.
 * Todas las rutas de este controlador están prefijadas con /proveedor.
 */
@Controller
@RequestMapping("/proveedor")
public class ProveedorController {

    /**
     * Servicio de proveedores para acceder a la lógica de negocio de proveedores.
     */
    @Autowired
    private ProveedorService proveedorService;

    /**
     * Muestra la lista completa de todos los proveedores registrados en el sistema.
     * @param model Modelo que transfiere los datos a la vista.
     * @return Vista de la tabla de proveedores (pages/tablaProveedor).
     */
    @GetMapping("/proveedor")
    public String listaProveedor(Model model) {
        List<Proveedor> proveedores = proveedorService.mostrarProveedores();
        model.addAttribute("proveedores", proveedores);
        return "pages/tablaProveedor";
    }

    /**
     * Muestra el formulario vacío para registrar un nuevo proveedor.
     * Se agrega un objeto Proveedor vacío al modelo para el binding del formulario.
     * @param model Modelo que transfiere los datos a la vista.
     * @return Vista del formulario de proveedor (pages/formularioProveedor).
     */
    @GetMapping("/irFormProveedor")
    public String mostrarFormProveedor(Model model) {
        model.addAttribute("proveedor", new Proveedor());
        return "pages/formularioProveedor";
    }

    /**
     * Procesa el formulario y guarda el proveedor en la base de datos.
     * Si el formulario contiene errores de validación, regresa al formulario
     * con los mensajes de error correspondientes.
     * @param proveedor Objeto Proveedor poblado con los datos del formulario y validado con @Valid.
     * @param result Resultado de la validación del formulario.
     * @param model Modelo que transfiere los datos a la vista.
     * @return Redirección a la lista de proveedores si es exitoso, o formulario con errores si falla.
     */
    @PostMapping("/guardarProveedor")
    public String guardarProveedor(
            @Valid @ModelAttribute("proveedor") Proveedor proveedor,
            BindingResult result,
            Model model) {
        // Si hay errores de validación, regresa al formulario mostrando los mensajes
        if (result.hasErrors()) {
            return "pages/formularioProveedor";
        }
        Proveedor proveedorGuardado = proveedorService.guardarProveedor(proveedor);
        model.addAttribute("proveedor", proveedorGuardado);
        return "redirect:/proveedor/proveedor";
    }

    /**
     * Carga el formulario de edición con los datos del proveedor seleccionado.
     * Si el código no corresponde a ningún proveedor, carga un formulario vacío.
     * @param codigo Código identificador del proveedor a editar.
     * @param model Modelo que transfiere los datos a la vista.
     * @return Vista del formulario de proveedor con los datos precargados (pages/formularioProveedor).
     */
    @GetMapping("/editarProveedor/{codigo}")
    public String actualizarProveedor(@PathVariable Long codigo, Model model) {
        Optional<Proveedor> proveedor = proveedorService.bucarProveedorPorId(codigo);
        // Si no existe el proveedor, se carga un formulario vacío como fallback
        model.addAttribute("proveedor", proveedor.orElse(new Proveedor()));
        return "pages/formularioProveedor";
    }

    /**
     * Elimina el proveedor con el código especificado de la base de datos
     * y redirige a la lista de proveedores actualizada.
     * @param codigo Código identificador del proveedor a eliminar.
     * @return Redirección a la lista de proveedores (/proveedor/proveedor).
     */
    @GetMapping("/eliminarProveedor/{codigo}")
    public String eliminarProveedor(@PathVariable Long codigo) {
        proveedorService.eliminarProveedor(codigo);
        return "redirect:/proveedor/proveedor";
    }
}