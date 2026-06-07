package com.contall.residencial.controller;

import com.contall.residencial.model.ConjuntoResidencial;
import com.contall.residencial.repository.ConjuntoResidencialRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/conjuntos")
public class ConjuntoResidencialController {

	private final ConjuntoResidencialRepository conjuntoRepository;

	public ConjuntoResidencialController(ConjuntoResidencialRepository conjuntoRepository) {
		this.conjuntoRepository = conjuntoRepository;
	}

	@GetMapping
	public List<ConjuntoResidencial> listarConjuntos() {
		return conjuntoRepository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<ConjuntoResidencial> obtenerConjunto(@PathVariable Long id) {
		return conjuntoRepository.findById(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	public ResponseEntity<ConjuntoResidencial> crearConjunto(@Valid @RequestBody ConjuntoResidencial conjunto) {
		ConjuntoResidencial creado = conjuntoRepository.save(conjunto);
		return new ResponseEntity<>(creado, HttpStatus.CREATED);
	}
}
