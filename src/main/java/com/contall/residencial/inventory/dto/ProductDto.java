package com.contall.residencial.inventory.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record ProductDto(
	Long id,
	@NotBlank String sku,
	@NotBlank String name,
	String description,
	@NotNull @Min(0) Integer quantity,
	@NotNull @DecimalMin("0.0") BigDecimal price
) {
}
