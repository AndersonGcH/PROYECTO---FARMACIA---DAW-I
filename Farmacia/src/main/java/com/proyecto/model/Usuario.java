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
    private Integer idRol;

    @Column(name = "id_sede")
    private Integer idSede;

    // Getters y setters
    public Usuario() {
		super();
	}

	public Usuario(Integer idUsuario, String nombre, String email, String telefono, String password, Integer idRol,
			Integer idSede) {
		super();
		this.idUsuario = idUsuario;
		this.nombre = nombre;
		this.email = email;
		this.telefono = telefono;
		this.password = password;
		this.idRol = idRol;
		this.idSede = idSede;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getIdRol() {
		return idRol;
	}

	public void setIdRol(Integer idRol) {
		this.idRol = idRol;
	}

	public Integer getIdSede() {
		return idSede;
	}

	public void setIdSede(Integer idSede) {
		this.idSede = idSede;
	}
}