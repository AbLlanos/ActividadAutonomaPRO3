package com.itsqmet.actividad_autonoma_pro3.controller;

import com.itsqmet.actividad_autonoma_pro3.entity.Cliente;
import com.itsqmet.actividad_autonoma_pro3.entity.Producto;
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

@Controller
@RequestMapping("/proveedor")
public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;

    //Mostrar todos
    @GetMapping("/proveedor")
    public String listaProveedor(Model model) {
        List<Proveedor> proveedores = proveedorService.mostrarProveedores();
        model.addAttribute("proveedores", proveedores);
        return "pages/tablaProveedor";
    }



    //ir Form
    @GetMapping("/irFormProveedor")
    public String msostrarFormProveedor(Model model){
        model.addAttribute("proveedor", new Proveedor());
        return "pages/formularioProveedor";
    }


    //Guardar
    @PostMapping("/guardarProveedor")
    public String  guardarProveedor(@Valid @ModelAttribute("proveedor") Proveedor proveedor,
                                 BindingResult result,
                                 Model model) {
        if (result.hasErrors()) {
            return "pages/formularioProveedor";
        }

        Proveedor proveedorGuardado = proveedorService.guardarProveedor(proveedor);;
        model.addAttribute("proveedor", proveedorGuardado);
        return "redirect:/proveedor/proveedor";
    }

    /*
    @PostMapping("/guardarProveedor")
    public String guardarProveedor(@ModelAttribute Proveedor proveedor){
        proveedorService.guardarProveedor(proveedor);
        return "redirect:/----";
    }
    */
    // Actualizar
    @GetMapping("/editarProveedor/{codigo}")
    public String actualizarProveedor(@PathVariable Long codigo, Model model) {
        Optional<Proveedor> proveedor = proveedorService.bucarProveedorPorId(codigo);
        model.addAttribute("proveedor", proveedor.orElse(new Proveedor()));
        return "pages/formularioProveedor";
    }


    //Eliminar
    @GetMapping("/eliminarProveedor/{codigo}")
    public String eliminarProveedor(@PathVariable Long codigo) {
        proveedorService.eliminarProveedor(codigo);
        return "redirect:/proveedor/proveedor";
    }
}
