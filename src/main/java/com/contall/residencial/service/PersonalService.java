package com.contall.residencial.service;

import com.contall.residencial.dto.PersonalDto;
import com.contall.residencial.model.ConjuntoResidencial;
import com.contall.residencial.model.Personal;
import com.contall.residencial.repository.ConjuntoResidencialRepository;
import com.contall.residencial.repository.PersonalRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PersonalService {

	private final PersonalRepository personalRepository;
	private final ConjuntoResidencialRepository conjuntoRepository;

	public PersonalService(PersonalRepository personalRepository, ConjuntoResidencialRepository conjuntoRepository) {
		this.personalRepository = personalRepository;
		this.conjuntoRepository = conjuntoRepository;
	}

	public List<Personal> listarPersonal() {
		return personalRepository.findAll();
	}

	public Personal buscarPorId(Long id) {
		return personalRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Personal no encontrado con id " + id));
	}

	public Personal crearPersonal(PersonalDto dto) {
		ConjuntoResidencial conjunto = conjuntoRepository.findById(dto.getConjuntoResidencialId())
				.orElseThrow(() -> new IllegalArgumentException("Conjunto residencial no encontrado con id " + dto.getConjuntoResidencialId()));
		Personal personal = new Personal(
				dto.getNombre(),
				dto.getApellido(),
				dto.getDni(),
				dto.getCargo(),
				dto.getTelefono(),
				dto.getEmail(),
				dto.getFechaIngreso(),
				conjunto
		);
		return personalRepository.save(personal);
	}

	public Personal actualizarPersonal(Long id, PersonalDto dto) {
		Personal personal = buscarPorId(id);
		ConjuntoResidencial conjunto = conjuntoRepository.findById(dto.getConjuntoResidencialId())
				.orElseThrow(() -> new IllegalArgumentException("Conjunto residencial no encontrado con id " + dto.getConjuntoResidencialId()));
		personal.setNombre(dto.getNombre());
		personal.setApellido(dto.getApellido());
		personal.setDni(dto.getDni());
		personal.setCargo(dto.getCargo());
		personal.setTelefono(dto.getTelefono());
		personal.setEmail(dto.getEmail());
		personal.setFechaIngreso(dto.getFechaIngreso());
		personal.setConjuntoResidencial(conjunto);
		return personalRepository.save(personal);
	}

	public void eliminarPersonal(Long id) {
		personalRepository.deleteById(id);
	}
}
