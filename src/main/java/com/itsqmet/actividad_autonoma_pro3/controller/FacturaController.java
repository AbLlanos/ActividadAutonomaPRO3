package com.itsqmet.actividad_autonoma_pro3.controller;


import com.itsqmet.actividad_autonoma_pro3.entity.Cliente;
import com.itsqmet.actividad_autonoma_pro3.entity.Factura;
import com.itsqmet.actividad_autonoma_pro3.entity.Producto;
import com.itsqmet.actividad_autonoma_pro3.entity.Proveedor;
import com.itsqmet.actividad_autonoma_pro3.repository.ProductoRepository;
import com.itsqmet.actividad_autonoma_pro3.services.ClienteService;
import com.itsqmet.actividad_autonoma_pro3.services.FacturaService;
import com.itsqmet.actividad_autonoma_pro3.services.ProductoService;
import com.itsqmet.actividad_autonoma_pro3.services.ProveedorService;
import jakarta.persistence.Entity;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/factura")
public class FacturaController {

    @Autowired
    private FacturaService facturaService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ProductoService productoService;

    @Autowired
    private ProductoRepository productoRepository;


    //Mostrar todos
    @GetMapping("/factura")
    public String listaFactura(Model model) {
        List<Factura> facturas = facturaService.mostrarFactura();
        model.addAttribute("facturas", facturas);
        return "pages/tablaFactura";
    }


    //irForm
    @GetMapping("/irFormularioFactura")
    public String mostrarFormularioFactura(Model model){


       List<Producto>productos= productoService.mostrarProductos();

       model.addAttribute("productos",productos);


        List<Cliente> clientes = clienteService.mostrarClientes();

       model.addAttribute("clientes",clientes);


        model.addAttribute("factura", new Factura());



        return "pages/formularioFactura";
    }



    @PostMapping("/guardarFactura")
    public String guardarFactura(Factura factura) {
        Producto producto = productoRepository.findById(factura.getProducto().getCodigo())
                .orElseThrow();

        factura.setProducto(producto);

        double precio = producto.getPrecioUnitario();
        int cantidad = factura.getCantidad();

        factura.setPrecio(precio);

        double subtotal = precio * cantidad;
        double iva = subtotal * 0.15;
        double total = subtotal + iva;

        factura.setSubtotal(subtotal);
        factura.setIva(iva);
        factura.setTotal(total);

        facturaService.guardarFactura(factura);
        return "redirect:/factura";
    }





    // Actualizar
    @GetMapping("/editarFactura/{id}")
    public String actualizarFactura(@PathVariable Long id, Model model) {

        List<Producto>productos= productoService.mostrarProductos();

        model.addAttribute("productos",productos);


        List<Cliente> clientes = clienteService.mostrarClientes();

        model.addAttribute("clientes",clientes);


        Optional<Factura> factura = facturaService.bucarFacturaPorId(id);
        model.addAttribute("factura", factura.orElse(new Factura()));


        return "pages/formularioFactura";
    }



    //Eliminar
    @GetMapping("/eliminarFactura/{id}")
    public String eliminarFactura(@PathVariable Long id) {
        facturaService.eliminarFactura(id);

        return "redirect:/factura";
    }

}



