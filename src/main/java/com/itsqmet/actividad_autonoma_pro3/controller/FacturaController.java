package com.itsqmet.actividad_autonoma_pro3.controller;

import com.itsqmet.actividad_autonoma_pro3.entity.Cliente;
import com.itsqmet.actividad_autonoma_pro3.entity.Factura;
import com.itsqmet.actividad_autonoma_pro3.entity.Producto;
import com.itsqmet.actividad_autonoma_pro3.repository.ProductoRepository;
import com.itsqmet.actividad_autonoma_pro3.services.ClienteService;
import com.itsqmet.actividad_autonoma_pro3.services.FacturaService;
import com.itsqmet.actividad_autonoma_pro3.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controlador MVC que gestiona las peticiones HTTP relacionadas con las facturas.
 * Todas las rutas de este controlador están prefijadas con /factura.
 */
@Controller
@RequestMapping("/factura")
public class FacturaController {

    /**
     * Servicio de facturas para acceder a la lógica de negocio de facturas.
     */
    @Autowired
    private FacturaService facturaService;

    /**
     * Servicio de clientes usado para cargar la lista de clientes en el formulario.
     */
    @Autowired
    private ClienteService clienteService;

    /**
     * Servicio de productos usado para cargar la lista de productos en el formulario.
     */
    @Autowired
    private ProductoService productoService;

    /**
     * Repositorio de productos usado para obtener el producto seleccionado
     * al momento de guardar la factura y calcular precios.
     */
    @Autowired
    private ProductoRepository productoRepository;

    /**
     * Muestra la lista completa de todas las facturas registradas en el sistema.
     * @param model Modelo que transfiere los datos a la vista.
     * @return Vista de la tabla de facturas (pages/tablaFactura).
     */
    @GetMapping("/factura")
    public String listaFactura(Model model) {
        List<Factura> facturas = facturaService.mostrarFactura();
        model.addAttribute("facturas", facturas);
        return "pages/tablaFactura";
    }

    /**
     * Muestra el formulario vacío para crear una nueva factura.
     * Carga la lista de productos y clientes disponibles para los selectores del formulario.
     * @param model Modelo que transfiere los datos a la vista.
     * @return Vista del formulario de factura (pages/formularioFactura).
     */
    @GetMapping("/irFormularioFactura")
    public String mostrarFormularioFactura(Model model) {
        // Carga productos y clientes para los dropdowns del formulario
        model.addAttribute("productos", productoService.mostrarProductos());
        model.addAttribute("clientes", clienteService.mostrarClientes());
        model.addAttribute("factura", new Factura());
        return "pages/formularioFactura";
    }

    /**
     * Procesa el formulario y guarda la factura en la base de datos.
     * Realiza el cálculo automático de subtotal, IVA (15%) y total
     * basándose en el precio unitario del producto y la cantidad ingresada.
     * @param factura Objeto Factura poblado con los datos del formulario.
     * @return Redirección a la lista de facturas tras guardar exitosamente.
     */
    @PostMapping("/guardarFactura")
    public String guardarFactura(Factura factura) {
        // Obtiene el producto completo desde la BD usando el código del producto seleccionado
        Producto producto = productoRepository.findById(factura.getProducto().getCodigo())
                .orElseThrow();
        factura.setProducto(producto);

        // Calcula subtotal, IVA (15%) y total automáticamente
        double precio = producto.getPrecioUnitario();
        int cantidad = factura.getCantidad();
        double subtotal = precio * cantidad;
        double iva = subtotal * 0.15;
        double total = subtotal + iva;

        factura.setPrecio(precio);
        factura.setSubtotal(subtotal);
        factura.setIva(iva);
        factura.setTotal(total);

        facturaService.guardarFactura(factura);
        return "redirect:/factura/factura";
    }

    /**
     * Carga el formulario de edición con los datos de la factura seleccionada.
     * También recarga la lista de productos y clientes para los selectores.
     * Si el ID no corresponde a ninguna factura, carga un formulario vacío.
     * @param id ID de la factura a editar.
     * @param model Modelo que transfiere los datos a la vista.
     * @return Vista del formulario de factura con los datos precargados (pages/formularioFactura).
     */
    @GetMapping("/editarFactura/{id}")
    public String actualizarFactura(@PathVariable Long id, Model model) {
        // Recarga productos y clientes para los dropdowns del formulario de edición
        model.addAttribute("productos", productoService.mostrarProductos());
        model.addAttribute("clientes", clienteService.mostrarClientes());

        // Si no existe la factura, se carga un formulario vacío como fallback
        Optional<Factura> factura = facturaService.bucarFacturaPorId(id);
        model.addAttribute("factura", factura.orElse(new Factura()));

        return "pages/formularioFactura";
    }

    /**
     * Elimina la factura con el ID especificado de la base de datos
     * y redirige a la lista de facturas actualizada.
     * @param id ID de la factura a eliminar.
     * @return Redirección a la lista de facturas (/factura/factura).
     */
    @GetMapping("/eliminarFactura/{id}")
    public String eliminarFactura(@PathVariable Long id) {
        facturaService.eliminarFactura(id);
        return "redirect:/factura/factura";
    }
}