package com.proyecto.service;

import java.util.List;

import com.proyecto.dto.LoginResponseDTO;
import com.proyecto.dto.RegistroUsuarioDTO;
import com.proyecto.dto.UsuarioResponseDTO;

public interface UsuarioService {

    // Crear usuario (registro)
    UsuarioResponseDTO crearUsuario(RegistroUsuarioDTO dto);

    // Obtener todos los usuarios
    List<UsuarioResponseDTO> listarUsuarios();

    // Obtener un usuario por email
    UsuarioResponseDTO obtenerPorEmail(String email);

    // Login
    LoginResponseDTO login(String email, String password);

    // Recuperar contraseña
    void enviarTokenRecuperacion(String email);

    // Resetear contraseña
    void resetPassword(String token, String nuevaPassword);
}