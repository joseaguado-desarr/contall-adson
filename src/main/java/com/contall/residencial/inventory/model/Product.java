package com.contall.residencial.inventory.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "product", uniqueConstraints = {@UniqueConstraint(columnNames = "sku")})
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	@NotBlank
	private String sku;

	@Column(nullable = false)
	@NotBlank
	private String name;

	@Column(length = 1000)
	private String description;

	@Column(nullable = false)
	@NotNull
	@Min(0)
	private Integer quantity;

	@Column(nullable = false)
	@NotNull
	@DecimalMin("0.0")
	private BigDecimal price;

	public Product() {
	}

	public Product(String sku, String name, String description, Integer quantity, BigDecimal price) {
		this.sku = sku;
		this.name = name;
		this.description = description;
		this.quantity = quantity;
		this.price = price;
	}

	public Long getId() {
		return id;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
}
