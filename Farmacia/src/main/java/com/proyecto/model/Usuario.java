package com.proyecto.model;

import jakarta.persistence.*;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer idUsuario;

    @Column(nullable = false)
    private String nombre;

    @Column(unique = true, nullable = false)
    private String email;

    private String telefono;

    @Column(nullable = false)
    private String password;

    @Column(name = "id_rol", nullable = false)
    private Integer idRol;  // ← clave foránea a rol

    @Column(name = "id_sede")
    private Integer idSede; // ← clave foránea a sede (puede ser null)

    // Getters y setters
}