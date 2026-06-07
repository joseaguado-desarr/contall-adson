package com.contall.residencial.inventory.controller;

import com.contall.residencial.inventory.dto.AuthRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	private final AuthenticationManager authenticationManager;

	public AuthController(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(@RequestBody AuthRequest request) {
		try {
			authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(request.username(), request.password())
			);
			return ResponseEntity.ok(Map.of(
				"authenticated", true,
				"username", request.username(),
				"message", "Inicio de sesión exitoso"
			));
		} catch (AuthenticationException ex) {
			return ResponseEntity.status(401).body(Map.of(
				"authenticated", false,
				"message", "Credenciales inválidas"
			));
		}
	}
}
