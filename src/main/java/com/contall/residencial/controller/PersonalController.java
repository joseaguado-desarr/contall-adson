package com.contall.residencial.controller;

import com.contall.residencial.dto.PersonalDto;
import com.contall.residencial.model.Personal;
import com.contall.residencial.service.PersonalService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/personal")
public class PersonalController {

	private final PersonalService personalService;

	public PersonalController(PersonalService personalService) {
		this.personalService = personalService;
	}

	@GetMapping
	public List<PersonalDto> listarPersonal() {
		return personalService.listarPersonal().stream()
				.map(this::convertirADto)
				.collect(Collectors.toList());
	}

	@GetMapping("/{id}")
	public ResponseEntity<PersonalDto> obtenerPersonal(@PathVariable Long id) {
		Personal personal = personalService.buscarPorId(id);
		return ResponseEntity.ok(convertirADto(personal));
	}

	@PostMapping
	public ResponseEntity<PersonalDto> crearPersonal(@Valid @RequestBody PersonalDto dto) {
		Personal personalCreado = personalService.crearPersonal(dto);
		return new ResponseEntity<>(convertirADto(personalCreado), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<PersonalDto> actualizarPersonal(@PathVariable Long id, @Valid @RequestBody PersonalDto dto) {
		Personal actualizada = personalService.actualizarPersonal(id, dto);
		return ResponseEntity.ok(convertirADto(actualizada));
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void eliminarPersonal(@PathVariable Long id) {
		personalService.eliminarPersonal(id);
	}

	private PersonalDto convertirADto(Personal personal) {
		PersonalDto dto = new PersonalDto();
		dto.setId(personal.getId());
		dto.setNombre(personal.getNombre());
		dto.setApellido(personal.getApellido());
		dto.setDni(personal.getDni());
		dto.setCargo(personal.getCargo());
		dto.setTelefono(personal.getTelefono());
		dto.setEmail(personal.getEmail());
		dto.setFechaIngreso(personal.getFechaIngreso());
		if (personal.getConjuntoResidencial() != null) {
			dto.setConjuntoResidencialId(personal.getConjuntoResidencial().getId());
		}
		return dto;
	}
}
