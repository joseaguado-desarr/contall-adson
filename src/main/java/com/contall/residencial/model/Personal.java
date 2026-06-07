package com.contall.residencial.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "personal")
public class Personal {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	@NotBlank
	private String nombre;

	@Column(nullable = false)
	@NotBlank
	private String apellido;

	@Column(nullable = false, unique = true)
	@NotBlank
	private String dni;

	@Column(nullable = false)
	@NotBlank
	private String cargo;

	@Column
	private String telefono;

	@Column
	@Email
	private String email;

	@Column(name = "fecha_ingreso", nullable = false)
	@NotNull
	private LocalDate fechaIngreso;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "conjunto_residencial_id")
	private ConjuntoResidencial conjuntoResidencial;

	public Personal() {
	}

	public Personal(String nombre, String apellido, String dni, String cargo, String telefono, String email, LocalDate fechaIngreso, ConjuntoResidencial conjuntoResidencial) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.cargo = cargo;
		this.telefono = telefono;
		this.email = email;
		this.fechaIngreso = fechaIngreso;
		this.conjuntoResidencial = conjuntoResidencial;
	}

	public Long getId() {
		return id;
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

	public ConjuntoResidencial getConjuntoResidencial() {
		return conjuntoResidencial;
	}

	public void setConjuntoResidencial(ConjuntoResidencial conjuntoResidencial) {
		this.conjuntoResidencial = conjuntoResidencial;
	}
}
