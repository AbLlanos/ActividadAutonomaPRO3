package com.itsqmet.actividad_autonoma_pro3.controller;

import com.itsqmet.actividad_autonoma_pro3.entity.Producto;
import com.itsqmet.actividad_autonoma_pro3.entity.Proveedor;
import com.itsqmet.actividad_autonoma_pro3.services.ProductoService;
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
 * Controlador MVC que gestiona las peticiones HTTP relacionadas con los productos.
 * Todas las rutas de este controlador están prefijadas con /producto.
 */
@Controller
@RequestMapping("/producto")
public class ProductoController {

    /**
     * Servicio de productos para acceder a la lógica de negocio de productos.
     */
    @Autowired
    private ProductoService productoService;

    /**
     * Servicio de proveedores usado para cargar la lista de proveedores
     * disponibles en el formulario de producto.
     */
    @Autowired
    private ProveedorService proveedorService;

    /**
     * Muestra la lista completa de todos los productos registrados en el sistema.
     * @param model Modelo que transfiere los datos a la vista.
     * @return Vista de la tabla de productos (pages/tablaProductos).
     */
    @GetMapping("/producto")
    public String listaProducto(Model model) {
        List<Producto> productos = productoService.mostrarProductos();
        model.addAttribute("productos", productos);
        return "pages/tablaProductos";
    }

    /**
     * Muestra el formulario vacío para registrar un nuevo producto.
     * Carga la lista de proveedores disponibles para el selector del formulario.
     * @param model Modelo que transfiere los datos a la vista.
     * @return Vista del formulario de producto (pages/formularioProducto).
     */
    @GetMapping("/irFormularioProducto")
    public String mostrarFormularioPorductos(Model model) {
        model.addAttribute("producto", new Producto());
        // Carga proveedores para el dropdown del formulario
        model.addAttribute("proveedores", proveedorService.mostrarProveedores());
        return "pages/formularioProducto";
    }

    /**
     * Procesa el formulario y guarda el producto en la base de datos.
     * Si el formulario contiene errores de validación, regresa al formulario
     * recargando la lista de proveedores para mantener el dropdown funcional.
     * @param producto Objeto Producto poblado con los datos del formulario y validado con @Valid.
     * @param result Resultado de la validación del formulario.
     * @param model Modelo que transfiere los datos a la vista.
     * @return Redirección a la lista de productos si es exitoso, o formulario con errores si falla.
     */
    @PostMapping("/guardarProducto")
    public String guardarProducto(
            @Valid @ModelAttribute("producto") Producto producto,
            BindingResult result,
            Model model) {
        // Si hay errores, recarga los proveedores para que el dropdown no quede vacío
        if (result.hasErrors()) {
            model.addAttribute("proveedores", proveedorService.mostrarProveedores());
            return "pages/formularioProducto";
        }
        productoService.guardarProducto(producto);
        return "redirect:/producto/producto";
    }

    /**
     * Carga el formulario de edición con los datos del producto seleccionado.
     * También recarga la lista de proveedores para el selector del formulario.
     * Si el ID no corresponde a ningún producto, carga un formulario vacío.
     * @param id ID del producto a editar.
     * @param model Modelo que transfiere los datos a la vista.
     * @return Vista del formulario de producto con los datos precargados (pages/formularioProducto).
     */
    @GetMapping("/editarProducto/{id}")
    public String actualizarProducto(@PathVariable Long id, Model model) {
        Optional<Producto> producto = productoService.bucarProductoPorId(id);
        // Si no existe el producto, se carga un formulario vacío como fallback
        model.addAttribute("producto", producto.orElse(new Producto()));
        // Recarga proveedores para el dropdown del formulario de edición
        model.addAttribute("proveedores", proveedorService.mostrarProveedores());
        return "pages/formularioProducto";
    }

    /**
     * Elimina el producto con el ID especificado de la base de datos
     * y redirige a la lista de productos actualizada.
     * @param id ID del producto a eliminar.
     * @return Redirección a la lista de productos (/producto/producto).
     */
    @GetMapping("/eliminarProducto/{id}")
    public String eliminarProducto(@PathVariable Long id) {
        productoService.eliminarProducto(id);
        return "redirect:/producto/producto";
    }
}