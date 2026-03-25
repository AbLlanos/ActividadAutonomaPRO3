package com.itsqmet.actividad_autonoma_pro3.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Entity
@Data
public class Cliente {


    @Id
    @Column(length = 10)
    @Size(min = 10, max = 10, message = "La cédula debe tener exactamente 10 dígitos")
    private String cedula;

    @NotBlank (message = "El nombre es obligatorio")
    @Size(max = 50, message = "El nombre no debe exceder 50 caracteres")
    private String nombre;

    @NotBlank (message = "El email es obligatorio")
    @Email(message = "El email debe tener el formato correcto")
    private String email;

    @NotBlank (message = "El password es obligatorio")
    @Size(min = 6, max = 255, message = "El password no debe tener menos de 6 caracteres")
    private String password;

    @NotBlank(message = "El apellido es obligatorio")
    @Size(max = 50, message = "El apellido no debe exceder 50 caracteres")
    private String apellido;

    @NotBlank(message = "La dirección es obligatoria")
    @Size(max = 100, message = "La dirección no debe exceder 100 caracteres")
    private String direccion;

    @NotNull(message = "Debe ingresar una fecha de nacimiento")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaNacimiento;




    @OneToMany(mappedBy = "cliente")
    private List<Factura> facturas;

}
