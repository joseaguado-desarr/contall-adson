package com.contall.residencial.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public class PersonalDto {

	private Long id;

	@NotBlank
	private String nombre;

	@NotBlank
	private String apellido;

	@NotBlank
	private String dni;

	@NotBlank
	private String cargo;

	private String telefono;

	@Email
	private String email;

	@NotNull
	private LocalDate fechaIngreso;

	@NotNull
	private Long conjuntoResidencialId;

	public PersonalDto() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(LocalDate fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public Long getConjuntoResidencialId() {
		return conjuntoResidencialId;
	}

	public void setConjuntoResidencialId(Long conjuntoResidencialId) {
		this.conjuntoResidencialId = conjuntoResidencialId;
	}
}
