package com.contall.residencial.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "conjunto_residencial")
public class ConjuntoResidencial {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	@NotBlank
	private String nombre;

	@Column(nullable = false)
	@NotBlank
	private String direccion;

	@Column
	private String telefonoContacto;

	public ConjuntoResidencial() {
	}

	public ConjuntoResidencial(String nombre, String direccion, String telefonoContacto) {
		this.nombre = nombre;
		this.direccion = direccion;
		this.telefonoContacto = telefonoContacto;
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

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefonoContacto() {
		return telefonoContacto;
	}

	public void setTelefonoContacto(String telefonoContacto) {
		this.telefonoContacto = telefonoContacto;
	}
}
