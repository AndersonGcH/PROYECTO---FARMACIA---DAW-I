package com.proyecto.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.proyecto.dto.LoginResponseDTO;
import com.proyecto.dto.RegistroUsuarioDTO;
import com.proyecto.dto.UsuarioResponseDTO;
import com.proyecto.service.UsuarioService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    
    ////http://localhost:9090/api/usuarios/registro
    // --------------------------
    // Registrar usuario
    // --------------------------
    @PostMapping("/registro")
    public ResponseEntity<UsuarioResponseDTO> registrarUsuario(@RequestBody RegistroUsuarioDTO dto) {
        UsuarioResponseDTO usuarioResponse = usuarioService.crearUsuario(dto);
        return new ResponseEntity<>(usuarioResponse, HttpStatus.CREATED);
    }

    // --------------------------
    // Listar todos los usuarios
    // --------------------------
    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> listarUsuarios() {
        List<UsuarioResponseDTO> usuarios = usuarioService.listarUsuarios();
        return ResponseEntity.ok(usuarios);
    }

    // --------------------------
    // Obtener usuario por email
    // --------------------------
    @GetMapping("/{email}")
    public ResponseEntity<UsuarioResponseDTO> obtenerUsuario(@PathVariable String email) {
        UsuarioResponseDTO usuario = usuarioService.obtenerPorEmail(email);
        return ResponseEntity.ok(usuario);
    }

    // --------------------------
    // Login
    // --------------------------
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestParam String email, 
                                                  @RequestParam String password) {
        LoginResponseDTO response = usuarioService.login(email, password);
        return ResponseEntity.ok(response);
    }

    // --------------------------
    // Enviar token de recuperación
    // --------------------------
    @PostMapping("/recuperar-password")
    public ResponseEntity<Void> enviarTokenRecuperacion(@RequestParam String email) {
        usuarioService.enviarTokenRecuperacion(email);
        return ResponseEntity.ok().build();
    }

    // --------------------------
    // Resetear contraseña
    // --------------------------
    @PostMapping("/reset-password")
    public ResponseEntity<Void> resetPassword(@RequestParam String token, 
                                              @RequestParam String nuevaPassword) {
        usuarioService.resetPassword(token, nuevaPassword);
        return ResponseEntity.ok().build();
    }
}
