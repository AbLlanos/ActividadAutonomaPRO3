package com.itsqmet.actividad_autonoma_pro3.controller;

import com.itsqmet.actividad_autonoma_pro3.entity.Cliente;
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

@Controller
@RequestMapping("/producto")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private ProveedorService proveedorService;


    //Mostrar todos
    @GetMapping("/producto")
    public String listaProducto(Model model) {
        List<Producto> productos = productoService.mostrarProductos();
        model.addAttribute("productos", productos);
        return "pages/tablaProductos";
    }


    //irForm
    @GetMapping("/irFormularioProducto")
    public String mostrarFormularioPorductos(Model model){
        model.addAttribute("producto", new Producto());
        List<Proveedor> proveedores = proveedorService.mostrarProveedores();
        model.addAttribute("proveedores", proveedores);
        return "pages/formularioProducto";
    }

    @PostMapping("/guardarProducto")
    public String guardarProducto(@Valid @ModelAttribute("producto") Producto producto,
                                  BindingResult result,
                                  Model model) {
        if (result.hasErrors()) {
            List<Proveedor> proveedores = proveedorService.mostrarProveedores();
            model.addAttribute("proveedores", proveedores);
            return "pages/formularioProducto";
        }
        productoService.guardarProducto(producto);
        return "redirect:/producto/producto";
    }


    // Actualizar
    @GetMapping("/editarProducto/{id}")
    public String actualizarProducto(@PathVariable Long id, Model model) {
        Optional<Producto> producto = productoService.bucarProductoPorId(id);

        List<Proveedor> proveedores = proveedorService.mostrarProveedores();

        model.addAttribute("producto", producto.orElse(new Producto()));

        model.addAttribute("proveedores",proveedores);

        return "pages/formularioProducto";
    }



    //Eliminar
    @GetMapping("/eliminarProducto/{id}")
    public String eliminarProducto(@PathVariable Long id) {
        productoService.eliminarProducto(id);
        return "redirect:/producto/producto";
    }

}
