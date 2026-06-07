package com.contall.residencial.inventory.controller;

import com.contall.residencial.inventory.dto.ProductDto;
import com.contall.residencial.inventory.model.Product;
import com.contall.residencial.inventory.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
public class ProductController {

	private final ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping
	public List<ProductDto> listProducts() {
		return productService.listAll().stream()
				.map(this::toDto)
				.collect(Collectors.toList());
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProductDto> getProduct(@PathVariable Long id) {
		Product product = productService.getById(id);
		return ResponseEntity.ok(toDto(product));
	}

	@PostMapping
	public ResponseEntity<ProductDto> createProduct(@Valid @RequestBody ProductDto dto) {
		Product created = productService.create(dto);
		return new ResponseEntity<>(toDto(created), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ProductDto> updateProduct(@PathVariable Long id, @Valid @RequestBody ProductDto dto) {
		Product updated = productService.update(id, dto);
		return ResponseEntity.ok(toDto(updated));
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteProduct(@PathVariable Long id) {
		productService.delete(id);
	}

	private ProductDto toDto(Product product) {
		return new ProductDto(
				product.getId(),
				product.getSku(),
				product.getName(),
				product.getDescription(),
				product.getQuantity(),
				product.getPrice()
		);
	}
}
