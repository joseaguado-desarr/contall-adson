package com.contall.residencial.inventory.service;

import com.contall.residencial.inventory.dto.ProductDto;
import com.contall.residencial.inventory.model.Product;
import com.contall.residencial.inventory.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductService {

	private final ProductRepository productRepository;

	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public List<Product> listAll() {
		return productRepository.findAll();
	}

	public Product getById(Long id) {
		return productRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Producto no encontrado con id " + id));
	}

	public Product create(ProductDto dto) {
		if (productRepository.existsBySku(dto.sku())) {
			throw new IllegalArgumentException("El SKU ya existe: " + dto.sku());
		}
		Product product = new Product(dto.sku(), dto.name(), dto.description(), dto.quantity(), dto.price());
		return productRepository.save(product);
	}

	public Product update(Long id, ProductDto dto) {
		Product product = getById(id);
		if (!product.getSku().equals(dto.sku()) && productRepository.existsBySku(dto.sku())) {
			throw new IllegalArgumentException("El SKU ya existe: " + dto.sku());
		}
		product.setSku(dto.sku());
		product.setName(dto.name());
		product.setDescription(dto.description());
		product.setQuantity(dto.quantity());
		product.setPrice(dto.price());
		return productRepository.save(product);
	}

	public void delete(Long id) {
		productRepository.deleteById(id);
	}
}
