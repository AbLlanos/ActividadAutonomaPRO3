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

@Controller
public class ClienteController {

    @Autowired
    private ClienteService clienteService;


    // Mostrar todos los clientes o filt
    @GetMapping("/clientes")
    public String listaClientes(@RequestParam(name = "buscarCedula", required = false, defaultValue = "") String buscarCedula,
                                Model model) {
        List<Cliente> clientes = clienteService.buscarClientesPorCedula(buscarCedula);
        model.addAttribute("clientes", clientes);
        model.addAttribute("buscarCedula", buscarCedula);
        return "pages/tablaClientes";
    }

    //dirigirse Cliente

    @GetMapping("/irFormCliente")
    public String mostrarFormularioCliente(Model model){
        model.addAttribute("cliente",new Cliente());
        return "pages/formularioCliente";
    }

    //Guardar cliente
    @PostMapping("/guardarCliente")
    public String guardarCliente(@Valid @ModelAttribute("cliente") Cliente cliente,
                                 BindingResult result,
                                 Model model) {
        if (result.hasErrors()) {
            return "pages/formularioCliente";
        }

        Cliente clienteGuardado = clienteService.guardarCliente(cliente);
        model.addAttribute("cliente", clienteGuardado);
        return "redirect:/clientes";
    }


    /* Sin validacion
    @PostMapping("/guardarCliente")
    public String guardarCliente(@ModelAttribute Cliente cliente){
        clienteService.guardarCliente(cliente);
        return "redicrect:/-----";
    }
    */

    // Actualizar
    @GetMapping("/editarCliente/{id}")
    public String actualizarCliente(@PathVariable String id, Model model) {
        Optional<Cliente> cliente = clienteService.bucarClientePorId(id);
        model.addAttribute("cliente", cliente.orElse(new Cliente()));
        return "pages/formularioCliente";
    }

    //Eliminar
    @GetMapping("/eliminarCliente/{id}")
    public String eliminarClliente(@PathVariable String id) {
        clienteService.eliminarCliente(id);
        return "redirect:/clientes";
    }



}
